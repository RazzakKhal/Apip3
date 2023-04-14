package com.applicationtrain.applicationtrain;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }


    //#augmentation de la mémoire tampon pour l'envoi des message via websocket
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(1024 * 1024); // default : 64 * 1024
        registration.setSendTimeLimit(200 * 10000); // default : 10 * 10000
        registration.setSendBufferSizeLimit(30* 512 * 1024); // default : 512 * 1024

    }
}