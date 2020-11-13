package com.restapi.restwithspringbootudemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.restwithspringbootudemy.config.SecurityConfig;
import com.restapi.restwithspringbootudemy.data.vo.ResponseSigninVO;
import com.restapi.restwithspringbootudemy.security.AccountCredentialsVO;
import com.restapi.restwithspringbootudemy.security.JwtTokenProvider;
import com.restapi.restwithspringbootudemy.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AuthenticationEndpoint")
@RestController
@Import(SecurityConfig.class)
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserService userService;

	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@ApiOperation(value = "Authenticate a user by credentials")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseSigninVO signin(@RequestBody AccountCredentialsVO data) {
		String token = userService.signin(data.getUsername(), data.getPassword());
		ResponseSigninVO responseSigninVO = new ResponseSigninVO();
		responseSigninVO.setToken(token);
		responseSigninVO.setUsername(data.getUsername());
		return responseSigninVO;
	}

}
