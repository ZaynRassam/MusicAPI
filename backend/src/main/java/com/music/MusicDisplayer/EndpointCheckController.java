package com.music.MusicDisplayer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpointcheck")
public class EndpointCheckController {
    @RequestMapping("/")
    public String endpointCheck(){
        return "Endpoint Check Successful";
    }
}
