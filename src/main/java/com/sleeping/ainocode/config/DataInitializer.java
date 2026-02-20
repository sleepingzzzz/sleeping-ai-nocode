package com.sleeping.ainocode.config;

import com.mybatisflex.core.query.QueryWrapper;
import com.sleeping.ainocode.mapper.UserMapper;
import com.sleeping.ainocode.model.entity.User;
import com.sleeping.ainocode.model.enums.UserRoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final String ADMIN_ACCOUNT = "admin";
    private static final String ADMIN_PASSWORD = "Admin@123456";

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initAdminUser();
    }

    private void initAdminUser() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", ADMIN_ACCOUNT);
        long count = userMapper.selectCountByQuery(queryWrapper);
        
        if (count == 0) {
            User admin = new User();
            admin.setUserAccount(ADMIN_ACCOUNT);
            admin.setUserPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            admin.setUserName("系统管理员");
            admin.setUserRole(UserRoleEnum.ADMIN.getValue());
            LocalDateTime now = LocalDateTime.now();
            admin.setCreateTime(now);
            admin.setUpdateTime(now);
            admin.setEditTime(now);
            userMapper.insert(admin);
            System.out.println("===========================================");
            System.out.println("管理员账户初始化成功！");
            System.out.println("账号: " + ADMIN_ACCOUNT);
            System.out.println("密码: " + ADMIN_PASSWORD);
            System.out.println("===========================================");
        }
    }
}
