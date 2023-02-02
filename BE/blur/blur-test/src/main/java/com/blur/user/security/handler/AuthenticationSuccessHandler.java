package com.blur.user.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.blur.user.api.dto.ResponseDto;
import com.blur.user.api.dto.response.LoginDto;
import com.blur.user.security.UserDetailsImpl;
import com.blur.user.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";
    public static final String AUTH_REFRESH_HEADER = "Refresh";

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        final UserDetailsImpl userDetails = ( (UserDetailsImpl) authentication.getPrincipal());
        final String token = JwtTokenUtils.generateJwtToken(userDetails);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // loginInfoDto 객체 생성
        LoginDto loginDto = LoginDto.builder()
                .userId(userDetails.getUsername())
                .build();

        // json 형태로 바꾸기
        String result = mapper.writeValueAsString(ResponseDto.success(loginDto));
        response.getWriter().write(result);
        response.addHeader(AUTH_HEADER,TOKEN_TYPE+" "+token);
    }
}
