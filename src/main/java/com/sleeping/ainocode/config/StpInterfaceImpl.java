package com.sleeping.ainocode.config;

import cn.dev33.satoken.stp.StpInterface;
import com.sleeping.ainocode.model.entity.User;
import com.sleeping.ainocode.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (loginId == null) {
            return Collections.emptyList();
        }
        try {
            Long userId = Long.parseLong(loginId.toString());
            User user = userService.getById(userId);
            if (user != null && user.getUserRole() != null) {
                return Collections.singletonList(user.getUserRole());
            }
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }
}
