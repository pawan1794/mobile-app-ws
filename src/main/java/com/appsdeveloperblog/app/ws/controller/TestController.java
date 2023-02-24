package com.appsdeveloperblog.app.ws.controller;

import com.appsdeveloperblog.app.ws.model.response.UserRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Pawan on 23/02/23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    public String test() {
        return "running";
    }
}
