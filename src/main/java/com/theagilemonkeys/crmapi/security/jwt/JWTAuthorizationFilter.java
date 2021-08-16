package com.theagilemonkeys.crmapi.security.jwt;

import com.theagilemonkeys.crmapi.models.UserEntity;
import com.theagilemonkeys.crmapi.services.CustomUserDetailsService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.getTokenFromRequest(request);

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            String id = tokenProvider.getIdFromToken(token);
            UserEntity user = (UserEntity) this.userDetailsService.loadUserById(id);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getRoles(), user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWTProvider.TOKEN_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWTProvider.TOKEN_PREFIX)) {
            return bearerToken.replace(JWTProvider.TOKEN_PREFIX, "");
        }

        return null;
    }

}
