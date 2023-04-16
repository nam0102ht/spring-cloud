package com.ntnn.logic;

import com.ntnn.model.GeneralOutlook;
import java.time.Instant;
import java.util.Date;

public class IncredibleLogic {
    public static GeneralOutlook generateGeneralOutlook() {
        GeneralOutlook generalOutlook = new GeneralOutlook();
        generalOutlook.setCity("Weimar");
        generalOutlook.setDate(Date.from(Instant.now()));
        generalOutlook.setState("Germany");
        generalOutlook.setWeatherStation("BestStationInTown");
        return generalOutlook;
    }
}
