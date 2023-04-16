package com.ntnn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntnn.logic.IncredibleLogic;
import com.ntnn.model.GeneralOutlook;
import com.ntnn.model.MethodOfPayment;
import com.ntnn.model.Product;
import com.ntnn.model.User;
import com.ntnn.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherBackendAPI {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherBackendAPI.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/general/outlook", produces = "application/json")
    public @ResponseBody GeneralOutlook generateGeneralOutlook(@RequestBody Weather weather) throws JsonProcessingException {
        LOG.info("Request for /general/outlook with POST");

        // Some incredible Businesslogic...
        LOG.info("Called Backend");

        LOG.info("Object as JSON:" + objectMapper.writeValueAsString(weather));

        GeneralOutlook outlook = IncredibleLogic.generateGeneralOutlook();
        LOG.info("GeneralOutlook as JSON: \n " + objectMapper.writeValueAsString(outlook));

        return outlook;
    }

    @GetMapping(value = "/general/outlook", produces = "application/json")
    public @ResponseBody String infoAboutGeneralOutlook() throws JsonProcessingException {
        LOG.info("Request for /general/outlook with GET");

        Weather weather = new Weather();
        weather.setFlagColor("blue");
        weather.setPostalCode("99425");
        weather.addUser(new User(55, 5634500, MethodOfPayment.Bitcoin));
        weather.setProduct(Product.ForecastBasic);

        String weatherJson = objectMapper.writeValueAsString(weather);

        return "Try a POST also against this URL! Just send some body with it like: '" + weatherJson + "'";
    }

    @GetMapping(value = "/{name}", produces = "text/plain")
    public String whatsTheSenseInThat(@PathVariable("name") String name) {
        LOG.info("Request for /{" + name + "} with GET");
        return "Hello " + name + "! This is a RESTful HttpService written in Spring. Try to use some other HTTP verbs (don´t say 'methods' :P ) :)";
    }
}
