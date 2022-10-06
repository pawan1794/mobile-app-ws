package com.appsdeveloperblog.app.ws.controller;

import com.appsdeveloperblog.app.ws.model.request.LoginRequestModal;
import com.appsdeveloperblog.app.ws.model.response.LoginRest;
import com.appsdeveloperblog.app.ws.security.SecurityConstants;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Pawan on 06/10/22
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/jwt_token", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LoginRest getAuthToken(@RequestBody LoginRequestModal credentials) throws Exception {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        } catch (UsernameNotFoundException e) {
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(credentials.getEmail());
        UserDto userDto = userService.getUser(userDetails.getUsername());

        LoginRest loginRest = new LoginRest(userDto.getEmail(), userDto.getUserId());

        String token = Jwts.builder()
                .setSubject(userDto.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();

        loginRest.setToken("Bearer " + token);
        loginRest.setExpireBy(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME).toString());
        loginRest.setRoles(userDto.getRoles());

        return loginRest;
    }
}
