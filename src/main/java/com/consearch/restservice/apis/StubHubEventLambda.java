package com.consearch.restservice.apis;

import com.amazonaws.services.transcribe.model.BadRequestException;
import com.consearch.restservice.models.stubhub.StubhubEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import com.consearch.restservice.models.stubhub.StubhubEventResponse;

public class StubHubEventLambda {
    public List<StubhubEvent> getEvents(String artist_Name){
        Regions region = Regions.fromName("us-east-1");

        AWSLambda awsLambda = AWSLambdaClient.builder()
                .withRegion(region)
                .build();

        String payload = "{\"artist_Name\": \"" + artist_Name + "\"}";

        InvokeRequest req = new InvokeRequest()
                .withFunctionName("TestCallPage")
                .withPayload(payload);

        InvokeResult invokeResult = awsLambda.invoke(req);
        String json = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        StubhubEventResponse response;
        try {
            response = mapper.readValue(json, StubhubEventResponse.class);
        } catch(IOException e) {
            return new ArrayList<>();
        }
        return response.getEvents();
    }
}