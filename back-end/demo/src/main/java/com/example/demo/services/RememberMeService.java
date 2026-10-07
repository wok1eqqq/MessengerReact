package com.example.demo.services;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Service 
public class RememberMeService implements RememberMeServices {

    @Override
    public @Nullable Authentication autoLogin(HttpServletRequest arg0, HttpServletResponse arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autoLogin'");
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginFail'");
    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication successfulAuthentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginSuccess'");
    }
    
    
}
