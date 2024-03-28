package com.example.ecommerceproject.model.req;
import lombok.Data;

@Data
public class UserReq {

	private Integer userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
//    private String token; 
    
//    public UserReq(String email, String token) {
//        this.email = email;
//        this.token = token;
//    }
    
}
