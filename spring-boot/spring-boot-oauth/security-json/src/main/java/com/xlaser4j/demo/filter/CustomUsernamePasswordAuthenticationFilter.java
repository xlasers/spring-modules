package com.xlaser4j.demo.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @package: com.xlaser4j.demo.filter
 * @author: Elijah.D
 * @time: 2020/2/4 16:56
 * @description:
 * @modified: Elijah.D
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        String postOnly = "POST";
        if (!postOnly.equals(req.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + req.getMethod());
        }

        // 如果是json参数:执行解析逻辑
        if (MediaType.APPLICATION_JSON_VALUE.equals(req.getContentType())) {

            // objectMapper解析body中的stream到map
            Map<String, String> info = new ObjectMapper().readValue(req.getInputStream(), Map.class);
            String username = info.get("username");
            if (username == null) {
                username = "";
            }
            String password = info.get("password");
            if (password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(req, authRequest);
            return getAuthenticationManager().authenticate(authRequest);
        }

        // 如果不是json参数:执行原有逻辑
        return super.attemptAuthentication(req, res);
    }
}
