package com.blur.api.controller;

import com.blur.api.dto.UserDto;
import com.blur.service.EmailService;
import com.blur.service.PasswordService;
import com.blur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordService passwordService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/register")
    public String register(UserDto userDto) {

        userService.register(userDto);
        return "redirect:/testLogin";
    }

    @PostMapping("/checkId") //아이디 중복체크
    public void checkId(@RequestParam("userId")String userId) {

        userService.checkId(userId);
        System.out.println(userService.checkId(userId));
    }

    @PostMapping("/sendAuthEmail") //이메일 인증메일 발송
    public String sendAuthEmail(@RequestParam("email") String email) throws Exception {

        String confirm = emailService.sendAuthMessage(email);

        return confirm;
    }

    @PutMapping("/findPassword") //비밀번호 찾기
    public void findPassword(@RequestParam("userId") String userId) throws Exception {

        passwordService.sendTempPassword(userId);
    }



    @GetMapping("/testLogin")
    public String testLogin() {
        return "testLogin";
    }

    @GetMapping("/testRegister")
    public String testRegister() {
        return "testRegister";
    }
}
