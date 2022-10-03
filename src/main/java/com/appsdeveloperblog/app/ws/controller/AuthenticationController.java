package com.appsdeveloperblog.app.ws.controller;

import com.appsdeveloperblog.app.ws.model.request.LoginRequestModal;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pawan on 01/10/22
 */
@RestController
public class AuthenticationController {

    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "{\n" +
                            "    \"userId\": \"\",\n" +
                            "    \"jwt token\": \"\",\n" +
                            "    \"expireBy\": \"\"\n" +
                            "}")
    })
    @PostMapping("/auth/token")
    public void getAuthToken(@RequestBody LoginRequestModal requestModal) throws IllegalAccessException {

        throw new IllegalAccessException("this method should not be called. This method is implemented by Spring Security.");
    }
}
