package com.sleeping.ainocode.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.sleeping.ainocode.annotation.AuthCheck;
import com.sleeping.ainocode.constant.UserConstant;
import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthInterceptor {

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        
        if (mustRole == null || mustRole.isEmpty()) {
            return joinPoint.proceed();
        }
        
        if (UserConstant.ADMIN_ROLE.equals(mustRole)) {
            if (!StpUtil.hasRole(UserConstant.ADMIN_ROLE)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        
        return joinPoint.proceed();
    }
}
