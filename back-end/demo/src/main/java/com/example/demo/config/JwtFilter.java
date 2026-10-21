package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Регистрируем фильтр в Spring
public class JwtFilter extends OncePerRequestFilter {

        @Autowired
        private JwtService jwtService;
        // Сервис для чтения JWT

        @Autowired
        private UserDetailsService userDetailsService;
        // Сервис для получения пользователя из БД

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {

                // Получаем заголовок Authorization
                String authHeader = request.getHeader("Authorization");

                // Если JWT отсутствует, пропускаем запрос дальше
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        filterChain.doFilter(request, response);
                        return;
                }

                // Убираем "Bearer " и оставляем только токен
                String jwt = authHeader.substring(7);

                // Получаем username из JWT
                String username = jwtService.extractUsername(jwt);

                // Загружаем пользователя из БД по username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Создаём объект авторизации для Spring Security
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                // Авторизуем пользователя в текущем запросе
                SecurityContextHolder.getContext()
                                .setAuthentication(authentication);

                // Передаём запрос дальше
                filterChain.doFilter(request, response);
        }
}