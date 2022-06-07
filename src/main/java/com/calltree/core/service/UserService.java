package com.calltree.core.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.calltree.api.dto.UserDTO;
import com.calltree.core.entity.OtpEntity;
import com.calltree.core.entity.UserEntity;
import com.calltree.core.repository.OtpRepository;
import com.calltree.core.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService { 

	private final UserRepository userRepository;
	private final OtpRepository otpRepository;

	public UserEntity retrieveUserByMobileNumber(String mobileNumber) {
		return userRepository.findByMobileNumber(mobileNumber);
	}
	
	
	public List<UserEntity> retrieveUsers() {
		return userRepository.findAll();
	}
	
	public OtpEntity generateOTP(String mobileNumber) {
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

		String otp = String.format("%06d", number);
		
		log.info("OTP : {}", otp);
		
		OtpEntity otpEntity = new OtpEntity();
		otpEntity.setOtp(otp);
		otpEntity.setReferenceKey(UUID.randomUUID().toString());
		
		
		otpRepository.save(otpEntity);
		
		Twilio.init("ACd070168f8246a20ebdf30a1e261ef847", "3abc8a231dda31d3a761946a2b051537");
		Message.creator(
		    new PhoneNumber("+" + mobileNumber),
		    new PhoneNumber("+19302064111"),
		    "Your Call Tree OTP : " + otp)
		.create();

		return otpEntity;
	}
	
	public boolean validateOTP(String referenceKey, String otp) {
		OtpEntity otpEntity = otpRepository.findByReferenceKey(referenceKey);
		long duration = Duration.between(otpEntity.getCreatedDate(), LocalDateTime.now()).toMillis();
		if(duration > 60000) {
			return false;
		}
		return otpEntity.getOtp().equals(otp);
	}
	
	public UserEntity createUser(UserDTO user) {
		UserEntity newUser = new UserEntity();
		newUser.setAddress(user.getAddress());
		newUser.setMobileNumber(user.getMobileNumber());
		newUser.setName(user.getName());
		newUser.setGeolocationX(user.getGeolocationX());
		newUser.setGeolocationY(user.getGeolocationY());
		userRepository.save(newUser);
		
		return newUser;
	}
}
