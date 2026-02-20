package com.sleeping.ainocode.model.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户ID不能为空")
    private Long id;

    @Size(min = 1, max = 24, message = "用户昵称长度1-24")
    private String userName;

    @Size(max = 500, message = "头像URL最长500")
    private String userAvatar;

    @Size(max = 200, message = "简介最长200")
    private String userProfile;

    @Pattern(regexp = "^(user|admin)$", message = "角色只能是user或admin")
    private String userRole;
}
