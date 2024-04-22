package com.example.dominantsoftdevelopment.config.jwtFilter;

import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.service.auth.AuthServiceImpl;
import com.example.dominantsoftdevelopment.utils.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final AuthServiceImpl authServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            if (authorization.startsWith(AppConstants.BEARER_TYPE))
                setAuthenticationBearer(request, authorization);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationBearer(HttpServletRequest request, String authorization) {
        String userId = jwtProvider.extractUserId(authorization.substring(AppConstants.BEARER_TYPE.length()).trim());
        if (userId != null) {

            Optional<User> optionalUser = authServiceImpl.findUserById(Long.valueOf(userId));
            optionalUser.ifPresent(user -> {
                if (user.allOk())
                    setAuthentication(request, user);
            });
        }
    }

    private void setAuthentication(HttpServletRequest request, User user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

        WebAuthenticationDetails details = new WebAuthenticationDetails(request);
        authentication.setDetails(details);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
