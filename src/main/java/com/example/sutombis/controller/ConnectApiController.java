package com.example.sutombis.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sutombis.model.CatFact;

@RestController
public class ConnectApiController {
     String url = "https://catfact.ninja/fact";
    RestTemplate restTemplate = new RestTemplate();
    
    CatFact response = restTemplate.getForObject(url, CatFact.class);
}
