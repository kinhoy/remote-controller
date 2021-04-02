package io.kinhoy.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description
 * @Date 2020-11-29 01:54
 * @Author kinhoy
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    /**
     * 首先在该类中注入一个ServerEndpointExporter的bean,
     * ServerEndpointExporter这个bean会自动注册使用了@ServerEndpoint这个注解的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}