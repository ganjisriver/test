package com.blur.api.dto;

import com.blur.entity.EmailAuth;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class EmailAuthDto {

    private Long tempNo;

    private String authKey;


    public EmailAuth toEntity(String authKey) {
        this.authKey = authKey;
        EmailAuth emailAuth = EmailAuth.builder()
                .authKey(authKey)
                .build();
        return emailAuth;
    }
}
