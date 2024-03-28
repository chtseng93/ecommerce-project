package com.example.ecommerceproject.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;


@Data
public class UserVo extends User{
	
	@TableField(exist = false)
//    @JoinTable(type = JoinType.LEFT_JOIN, tableName = "user_role", targetEntity = Role.class, sourceEntity = User.class, sourceJoinColumn = "id", targetJoinColumn = "user_id")
    private Collection<Role> roles = new HashSet<>();
	
	
	public List<String> getRoleName(Collection<Role> roles) {
		List<String> roleNames = roles.stream().map(role -> {
			return role.getName();
		}).collect(Collectors.toList());
		return roleNames;

	}

	
	

}
