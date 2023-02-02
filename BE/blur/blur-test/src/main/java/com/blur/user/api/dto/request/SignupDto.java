package com.blur.user.api.dto.request;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SignupDto {

//    public static final String STANDARD_IMAGE_ROUTE="https://hosunghan.s3.ap-northeast-2.amazonaws.com/user/basic_profile_img.PNG";

    @NotBlank(message = "아이디는 필수 입력 값입니다")
    @Pattern(regexp = "^[a-zA-Z0-9]+$" ,message = "아이디는 6-12자 영문, 숫자로 작성해야됩니다")
    private String userId;

    @NotBlank(message = "패스워드는 필수 입력 값입니다")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()])[A-Za-z0-9~!@#$%^&*()]{8,20}$", message = "숫자와 문자, 특수문자를 포함한 8-20자리 입력해주세요")
    private String password;
    
    @NotBlank(message = "이메일은 필수 입력 값입니다")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$" ,message = "올바른 이메일 형식이 아닙니다")
    private String email;

}
