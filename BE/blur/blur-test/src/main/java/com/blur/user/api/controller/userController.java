package com.blur.user.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blur.user.api.dto.ResponseDto;
import com.blur.user.api.dto.request.SignupDto;
import com.blur.user.api.dto.request.UserProfileDto;
import com.blur.user.api.dto.response.LoginDto;
import com.blur.user.api.service.UserService;
import com.blur.user.exception.ErrorCode;
import com.blur.user.exception.RequestException;
import com.blur.user.security.UserDetailsImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Login")
@Slf4j
@RequiredArgsConstructor
@RestController
public class userController {

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "일반 회원가입")
    @PostMapping("/api/members/signup")
    public ResponseDto<LoginDto> registerStandardUser(@Valid @RequestBody SignupDto signupDto, Errors errors){

        if (errors.hasErrors())
            throw new RequestException(ErrorCode.USER_INFO_NOT_FORMATTED, errors.getAllErrors().get(0).getDefaultMessage());

        ResponseDto<LoginDto> loginDto=userService.standardSignup(signupDto);

        return loginDto;
    }

    @ApiOperation(value = "이메일 중복체크", notes = "닉네임은 중복 허용")
    @PostMapping("/api/members/email")
    public ResponseDto<Boolean>  checkEmailDuplicate(@RequestBody SignupDto signupDto){
        return userService.checkEmail(signupDto);
    }

//    @ApiOperation(value = "마이페이지 조회", notes = "마이페이지 조회")
//    @GetMapping("/api/members/profile")
//    public ResponseEntity<?> getUserProfile( @AuthenticationPrincipal UserDetailsImpl userDetails){
//
//       return userService.getProfile(userDetails.getUser());
//    }
//
//    @ApiOperation(value = "마이페이지 수정", notes = "마이페이지 수정(닉네임, 프로필 사진)")
//    @PutMapping("/api/members/profile")
//    public ResponseDto<Boolean> changeUserProfile(@RequestBody UserProfileDto userprofileDto,
//                                  @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
//
//        return userService.changeProfile(userDetails.getUser(),userprofileDto);
//    }

//    @ApiOperation(value = "회원탈퇴 ", notes ="회원탈퇴")
//    @DeleteMapping("/api/members/signout")
//    public ResponseDto<Boolean> signOutMember(@AuthenticationPrincipal UserDetailsImpl userDetails){
//
//        return  userService.signOut(userDetails.getUser());
//
//    }

//    @ApiOperation(value="게스트 자동회원가입",notes="회원가입 및 로그인 처리")
//    @GetMapping("/api/members/guest")
//    public ResponseDto<LoginDto> guestSignupAndSignIn(){
//
//        ResponseDto<LoginDto> loginDto = userService.guestSignup();
//        userService.setGuestWorkspace(loginDto.getData().getUsername());
//        return loginDto;
//
//    }


}
