package com.config;

import com.planroom.app.impl.CreateGuestSession;
import com.planroom.app.service.DisplayNameService;
import com.planroom.app.service.IdService;
import com.planroom.spi.SessionStore;
import com.planroom.spi.Token;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionConfiguration {

    @Bean
    public IdService idService(){
        return IdService.of();
    }

    @Bean
    public DisplayNameService displayNameService(){
        return DisplayNameService.of();
    }

    @Bean
    public SessionStore sessionStore(){
        return new SessionStore();
    }

    @Bean
    public Token token(){
        return new Token();
    }

    @Bean
    public CreateGuestSession createGuestSession(SessionStore sessionStore, Token token,
                                                 IdService idService, DisplayNameService displayNameService){
        return new CreateGuestSession(sessionStore, token, idService, displayNameService);
    }
}
