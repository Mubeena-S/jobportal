package com.example.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Lombok generates getters, setters, toString, equals, hashCode
public class LoginRequest {

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	@Pattern(regexp = ".*[A-Z].*", message = "Password must contain an uppercase letter")
	@Pattern(regexp = ".*\\d.*", message = "Password must contain a number")
	@Pattern(regexp = ".*[@#$%^&+=].*", message = "Password must contain a special character")
	private String password;

	@NotBlank(message = "Captcha is required")
	private String captchaToken;
	private boolean rememberMe;
	private String deviceInfo;
	private String ipAddress;
	private String otp;

	public LoginRequest(String email, String password, String captchaToken, boolean rememberMe, String deviceInfo,
			String ipAddress, String otp) {
		this.email = email;
		this.password = password;
		this.captchaToken = captchaToken;
		this.rememberMe = rememberMe;
		this.deviceInfo = deviceInfo;
		this.ipAddress = ipAddress;
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "LoginRequest{" + "email='" + email + '\'' + ", password='[PROTECTED]'" + ", captchaToken='"
				+ captchaToken + '\'' + ", rememberMe=" + rememberMe + ", deviceInfo='" + deviceInfo + '\''
				+ ", ipAddress='" + ipAddress + '\'' + ", otp='" + otp + '\'' + '}';
	}
}
