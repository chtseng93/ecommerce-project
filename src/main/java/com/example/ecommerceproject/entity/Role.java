package com.example.ecommerceproject.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("role")
@Data
public class Role {
	
    @TableId(type = IdType.AUTO)
    @TableField("role_id")
    private Integer roleId;
    private String name;
   
    
    public Role(String name) {
        this.name = name;
    }
    
    
    
    
    


}
