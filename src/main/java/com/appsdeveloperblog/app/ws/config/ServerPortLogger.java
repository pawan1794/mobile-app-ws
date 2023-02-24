package com.appsdeveloperblog.app.ws.config;

import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Pawan on 23/02/23
 */
@Component
public class ServerPortLogger implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServerApplicationContext context = event.getApplicationContext();
        int port = context.getWebServer().getPort();
        System.out.println("Application is running on port " + port);
    }
}
