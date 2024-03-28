package com.example.ecommerceproject.service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ecommerceproject.entity.Role;
import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.entity.UserVo;
import com.example.ecommerceproject.exception.UserAlreadyExistsException;
import com.example.ecommerceproject.mapper.primary.UserMapper;
import com.example.ecommerceproject.model.req.UserReq;
import com.example.ecommerceproject.service.RoleService;
import com.example.ecommerceproject.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RoleService roleService;

	@Override
	public UserVo getUserInfo(Integer userId) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("user_id", userId);
		return userMapper.getUserList(wrapper);
	}
	
	@Override
	public UserVo getUserInfoByEmail(String email) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("email", email);
		return userMapper.getUserList(wrapper);
	}

	@Override
	public UserVo registerUser(UserReq userReq) throws Exception {
		
		if (this.getUserInfoByEmail(userReq.getEmail())!=null) {
			throw new UserAlreadyExistsException(userReq.getEmail() + " already exists");
		}
		User user =new User();
		user.setPassword(BCrypt.hashpw(userReq.getPassword(), BCrypt.gensalt()));
		user.setEmail(userReq.getEmail());
		user.setFirstName(userReq.getFirstName());
		user.setLastName(userReq.getLastName());
		userMapper.insert(user);
		Integer userId= user.getUserId();
		UserVo theNewUser = this.getUserInfo(userId);
		Role role = roleService.getRoleByRoleName("USER");
		roleService.insertOrUpdateUserRole(userId, role.getRoleId());
		return theNewUser;
		
	}


	
	

}
