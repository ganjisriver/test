package com.blur.user.api.service;

//import static com.hanghae.final_project.api.user.dto.request.SignupDto.STANDARD_IMAGE_ROUTE;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blur.user.api.dto.ResponseDto;
import com.blur.user.api.dto.request.SignupDto;
import com.blur.user.api.dto.request.UserProfileDto;
import com.blur.user.api.dto.response.LoginDto;
import com.blur.user.api.entity.ProviderType;
import com.blur.user.api.entity.User;
import com.blur.user.api.repository.UserRepository;
import com.blur.user.exception.ErrorCode;
import com.blur.user.exception.RequestException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

	private final UserRepository userRepository;

//    private final S3UploaderService uploaderService;
//    private final ChatRedisCacheService chatRedisCacheService;
	private final KakaoUserService kakaoUserService;
	private final BCryptPasswordEncoder passwordEncoder;

	// 일반회원 (not social) 회원가입
	public ResponseDto<LoginDto> standardSignup(SignupDto signupDto) {

		checkDuplicate(signupDto.getUserId());

		User userInfo = User.of(signupDto, passwordEncoder);

		userRepository.save(userInfo);

		return ResponseDto.success(LoginDto.builder().userId(signupDto.getUserId()).build());
	}

	// 이메일 중복체크
	public ResponseDto<Boolean> checkEmail(SignupDto signupDto) {
		Optional<User> found = userRepository.findByUserId(signupDto.getUserId());

		if (found.isPresent()) {
			return ResponseDto.fail(ErrorCode.USER_LOGINID_NOT_FOUND_404.name(),
					ErrorCode.USER_LOGINID_NOT_FOUND_404.getMessage());
		}

		return ResponseDto.success(true);
	}

	/*
	 * 유저 정보 값이 모두 있을 경우 -> profileImage & 닉네임 변경 유저 nickname의 값이 없을 경우. -> 프로필 이미지만
	 * 변경 유저 이미지 string 값이 없을 경우. -> 유저 닉네임만 변경 유저 이미지(string),이미지 모두 없을 경우 ->
	 * BadRequest
	 */

	// 회원탈퇴
//    public ResponseDto<Boolean> signOut(User user) {
//
//        if (!user.getProfileImage().contains(STANDARD_IMAGE_ROUTE)) {
//            uploaderService.deleteFiles(user.getProfileImage(), "user");
//        }
//
//        //kakao 유저 끊기
//        if (user.getSocial().equals(ProviderType.KAKAO))
//            kakaoUserService.signOutKakaoUser(user);
//
//        //Redis 닉네임 정보 삭제
//        chatRedisCacheService.deleteUserCahchingNickname(user.getUsername());
//
//        // DB에서 USER 제거
//        userRepository.delete(user);
//
//        return ResponseDto.success(true);
//    }

	// 게스트로그인
//    public ResponseDto<LoginDto> guestSignup() {
//
//        Long id;
//        User user = userRepository.findTopByOrderByIdDesc().orElse(null);
//
//        if (user == null) {
//            id = 1L;
//        } else id = user.getId() + 1;
//        SignupDto signupDto = SignupDto.builder()
//                .username("Guest" + id + "@dvelkit.com")
//                .nickname("Guest" + id)
//                .password("Guest" + id + "!")
//                .build();
//
//        return standardSignup(signupDto);
//    }

	// 이메일 중복 체크 로직 (회원가입 및 Front 중복체크 버튼)
	private void checkDuplicate(String username) {

		Optional<User> found = userRepository.findByUserId(username);

		if (found.isPresent()) {
			throw new RequestException(ErrorCode.USER_LOGINID_DUPLICATION_409);
		}
	}
}
