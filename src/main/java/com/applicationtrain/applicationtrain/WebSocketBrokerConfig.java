package com.applicationtrain.applicationtrain;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



    @Configuration
    @EnableWebSocketMessageBroker
    public class WebSocketBrokerConfig  implements WebSocketMessageBrokerConfigurer {

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            // ajout de la route à laquelle faire une requete pour se connecter et utiliser les websocket
            registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200").withSockJS();
        }
        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            // ajout des prefixes, lors de l'envoi d'un message on ajoutera au debut de notre route /app
            registry.setApplicationDestinationPrefixes("/app");
            registry.enableSimpleBroker("/topic");
        }


    }

