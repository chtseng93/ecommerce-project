package com.example.ecommerceproject.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.entity.UserVo;
import com.example.ecommerceproject.model.req.UserReq;

public interface UserService extends IService<User> {

	public UserVo getUserInfo(Integer userId);

	public UserVo getUserInfoByEmail(String email);

	public UserVo registerUser(UserReq userReq) throws Exception;


}
