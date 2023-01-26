package com.blur.service;

import org.springframework.stereotype.Service;

import com.blur.api.dto.TokenDto;
import com.blur.entity.Token;
import com.blur.entity.User;
import com.blur.repository.TokenRepository;
import com.blur.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService{
	private final TokenRepository tokenRepository;
//	private final UserRepository userRepository;
	
	public void saveRefreshToken(Token token) throws Exception {
		tokenRepository.save(token);
	}

	public String getRefreshToken(String userId) throws Exception {
		return tokenRepository.findByUserId(userId).getRefreshToken();
	}

	public void deleRefreshToken(String userId) throws Exception {
		tokenRepository.deleteByuserId(userId);
	}
	
//	public User getUserId(Long userNo) throws Exception {
//		User user = userRepository.findByUserNo(userNo);
//		return user;
//	}
}
