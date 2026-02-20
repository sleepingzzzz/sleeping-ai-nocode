package com.sleeping.ainocode.model.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 24, message = "用户昵称长度1-24")
    private String userName;

    @Size(min = 5, max = 16, message = "账号长度5-16")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]*$", message = "账号需以字母开头，只能包含字母、数字、下划线和减号")
    private String userAccount;

    @Size(max = 500, message = "头像URL最长500")
    private String userAvatar;

    @Size(max = 200, message = "简介最长200")
    private String userProfile;

    @Pattern(regexp = "^(user|admin)$", message = "角色只能是user或admin")
    private String userRole;
}
