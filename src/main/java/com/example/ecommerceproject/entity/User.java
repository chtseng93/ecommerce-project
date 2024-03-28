package com.example.ecommerceproject.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@TableName("user_profile")
@Data
public class User {
	@TableId(type = IdType.AUTO)
	@TableField("user_id")
	private Integer userId;
	@TableField("first_name")
    private String firstName;
	@TableField("last_name")
    private String lastName;
    private String email;
    private String password;
	
    
    
  

}
