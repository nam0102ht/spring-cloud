package controller;

import com.ntnn.controller.LeaderController;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.leader.Context;
import org.springframework.integration.leader.event.OnGrantedEvent;
import org.springframework.integration.leader.event.OnRevokedEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LeaderControllerTest {
    @Mock
    private OnGrantedEvent mockOnGrantedEvent;

    @Mock
    private OnRevokedEvent mockOnRevokedEvent;

    @Mock
    private Context mockContext;

    private String host;

    private LeaderController leaderController;

    @BeforeEach
    public void before() throws UnknownHostException {
        this.host = InetAddress.getLocalHost().getHostName();
        this.leaderController = new LeaderController();
    }

    @Test
    public void shouldGetNonLeaderInfo() {
        String message = String.format("I am '%s' but I am not a leader of the 'null'", this.host);
        assertThat(this.leaderController.getInfo()).isEqualTo(message);
    }

    @Test
    public void shouldHandleGrantedEvent() {
        given(this.mockOnGrantedEvent.getContext()).willReturn(this.mockContext);

        this.leaderController.handleEvent(this.mockOnGrantedEvent);

        String message = String.format("I am '%s' and I am the leader of the 'null'", this.host);
        assertThat(this.leaderController.getInfo()).isEqualTo(message);
    }

    @Test
    public void shouldHandleRevokedEvent() {
        given(this.mockOnGrantedEvent.getContext()).willReturn(this.mockContext);

        this.leaderController.handleEvent(this.mockOnGrantedEvent);
        this.leaderController.handleEvent(this.mockOnRevokedEvent);

        String message = String.format("I am '%s' but I am not a leader of the 'null'", this.host);
        assertThat(this.leaderController.getInfo()).isEqualTo(message);
    }

    @Test
    public void shouldRevokeLeadership() {
        given(this.mockOnGrantedEvent.getContext()).willReturn(this.mockContext);

        this.leaderController.handleEvent(this.mockOnGrantedEvent);
        ResponseEntity<String> responseEntity = this.leaderController.revokeLeadership();

        String message = String.format("Leadership revoked for '%s'", this.host);
        assertThat(responseEntity.getBody()).isEqualTo(message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(this.mockContext).yield();
    }

    @Test
    public void shouldNotRevokeLeadershipIfNotLeader() {
        ResponseEntity<String> responseEntity = this.leaderController.revokeLeadership();

        String message = String.format("Cannot revoke leadership because '%s' is not a leader", this.host);
        assertThat(responseEntity.getBody()).isEqualTo(message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verify(this.mockContext, times(0)).yield();
    }
}
