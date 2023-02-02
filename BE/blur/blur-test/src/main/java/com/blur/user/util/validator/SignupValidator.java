package com.blur.user.util.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.blur.user.api.dto.request.SignupDto;

@Component
public class SignupValidator {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$";

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()])" +
                    "[A-Za-z0-9~!@#$%^&*()]" +
                    "{8,20}$";

//    private static final String NICKNAME_PATTERN =
//            "^[0-9a-zA-Zㄱ-ㅎ가-힣]{2,8}$" ;
    
    private static final String ID_PATTERN =
    		"^[a-zA-Z0-9]{6,12}$" ;


    public void checkUserInfoValidation(SignupDto signupDto){
        if ( signupDto.getEmail()==null || !Pattern.matches(EMAIL_PATTERN,signupDto.getEmail())  ){
            throw new IllegalArgumentException("Email 정보가 유효하지 않습니다");
        }
        if( signupDto.getPassword()==null || !Pattern.matches(PASSWORD_PATTERN,signupDto.getPassword()) ){
            throw new IllegalArgumentException("Password 정보가 유효하지 않습니다");
        }
        if( signupDto.getUserId()==null || !Pattern.matches(ID_PATTERN,signupDto.getUserId())  ){
            throw new IllegalArgumentException("아이디 정보가 유효하지 않습니다");
        }
    }

}
