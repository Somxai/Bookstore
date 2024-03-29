package com.bookstore.com.authentication.jwt;

import com.bookstore.com.authentication.User.Users;
import com.bookstore.com.authentication.userDetailService.CustomUserDetailService;
import com.bookstore.com.dao.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailService userDetailsService;

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

    try {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request,response);
            return;

        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        userEmail = userRepository.findByUsername(username).
                map(Users::getEmail).orElse("User is null");

        if (SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

    }catch (Exception e){
        logger.error(e.getMessage());
    }
    filterChain.doFilter(request,response);

    }
}
