package com.example.ecommerceproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.entity.UserVo;
import com.example.ecommerceproject.model.req.LoginReq;
import com.example.ecommerceproject.model.req.UserReq;
import com.example.ecommerceproject.model.res.ErrorRes;

import com.example.ecommerceproject.model.res.UserRes;
import com.example.ecommerceproject.security.JwtUtil;
import com.example.ecommerceproject.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController{
	@Autowired
	UserService userService;
	
	@Autowired
	private  AuthenticationManager authenticationManager;

	@Autowired
    private JwtUtil jwtUtil;
	
	
	@PostMapping("/register-new-user")
    public ResponseEntity<?> registerUser(@RequestBody UserReq userReq){
        try{
        	log.info("register-new-user:" + userReq.getEmail());
            userService.registerUser(userReq);
            return ResponseEntity.ok("Congratulation! Your account has been succefully created!");

        }catch (Exception e){
        	log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
  


	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq)  {
		log.info("login:" + loginReq.getEmail());
        try {
            Authentication auth =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = auth.getName();
            UserVo userVo = userService.getUserInfoByEmail(email);
            User user = new User();
            user.setPassword("");
            user.setEmail(email);
            user.setFirstName(userVo.getFirstName());
            user.setLastName(userVo.getLastName());
            String jwtToken = jwtUtil.createToken(user);
            UserRes userRes = new UserRes(userVo.getUserId(), userVo.getFirstName(), userVo.getLastName(), userVo.getEmail(), jwtToken,
            		userVo.getRoleName(userVo.getRoles()));

            return ResponseEntity.ok(userRes);

        }catch (BadCredentialsException e){
        	log.error(e.getMessage());
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
        	log.error(e.getMessage());
            ErrorRes errorRes = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
        }
    }
}
