package com.sleeping.ainocode.model.dto.user;

import com.sleeping.ainocode.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String userAccount;

    private String userProfile;

    private String userRole;
}
