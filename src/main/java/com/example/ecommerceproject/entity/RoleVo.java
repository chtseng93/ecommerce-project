package com.example.ecommerceproject.entity;

import java.util.Collection;
import java.util.HashSet;

import com.baomidou.mybatisplus.annotation.TableField;


import lombok.Data;
@Data
public class RoleVo extends Role{
	
	@TableField(exist = false)
	private Collection<User> users = new HashSet<>();
	
	
	 public RoleVo(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	 public void removeUserFromRole(UserVo userVo){
	        userVo.getRoles().remove(this);
	        this.getUsers().remove(userVo);

	 }

}
