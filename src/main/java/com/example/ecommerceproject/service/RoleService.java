package com.example.ecommerceproject.service;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ecommerceproject.entity.Role;
import com.example.ecommerceproject.entity.RoleVo;
import com.example.ecommerceproject.entity.User;



public interface RoleService extends IService<Role> {
	
	
	List<Role> selectRolesWithUsers(Integer roleId);
	

	public RoleVo getRoleInfo(Integer roleId);
	
	public Role getRoleByRoleName(String roleName) throws Exception;
	
	public void insertOrUpdateUserRole(Integer userId, Integer roleId) throws Exception;;
	


}
