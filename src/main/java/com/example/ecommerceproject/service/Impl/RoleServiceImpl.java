package com.example.ecommerceproject.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ecommerceproject.entity.Role;
import com.example.ecommerceproject.entity.RoleVo;
import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.entity.UserVo;
import com.example.ecommerceproject.exception.InternalServerErrorException;
import com.example.ecommerceproject.mapper.primary.RoleMapper;
import com.example.ecommerceproject.mapper.primary.UserMapper;
import com.example.ecommerceproject.service.RoleService;
import com.example.ecommerceproject.service.UserService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	RoleMapper roleMapper;


	@Override
	public List<Role> selectRolesWithUsers(Integer userId) {
		return roleMapper.getRoleByUserId(userId);

	}



	@Override
	public RoleVo getRoleInfo(Integer roleId) {
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
		wrapper.eq("role_id", roleId);
		return roleMapper.getRoleList(wrapper);
	}

	@Override
	public Role getRoleByRoleName(String roleName) throws Exception {
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
		wrapper.eq("name", roleName);
		List<Role> roles = roleMapper.selectList(wrapper);
		if (CollectionUtils.isNotEmpty(roles)) {
			return roles.get(0);
		}
		throw new Exception("Role not found");

	}

	@Override
	public void insertOrUpdateUserRole(Integer userId, Integer roleId) {
		
		List<Role> roles =this.selectRolesWithUsers(userId);
		try {
			boolean roleExists = roles.stream()
					.map(Role::getRoleId)
					.anyMatch(id -> id.equals(roleId));
			if (!roleExists) {
				roleMapper.insertUserRole(userId, roleId);
			}
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}

	}

}
