package com.sleeping.ainocode.aop;

import cn.hutool.core.util.StrUtil;
import com.sleeping.ainocode.annotation.RateLimit;
import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class RateLimitAspect {

    private static final Map<String, RateLimitInfo> rateLimitMap = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object doRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = buildKey(joinPoint);
        int maxRequests = rateLimit.value();
        int windowSeconds = rateLimit.windowSeconds();
        
        synchronized (key.intern()) {
            RateLimitInfo info = rateLimitMap.computeIfAbsent(key, k -> new RateLimitInfo());
            long currentTime = System.currentTimeMillis();
            
            if (currentTime - info.windowStart > windowSeconds * 1000L) {
                info.windowStart = currentTime;
                info.count.set(0);
            }
            
            if (info.count.incrementAndGet() > maxRequests) {
                throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, rateLimit.message());
            }
        }
        
        return joinPoint.proceed();
    }

    private String buildKey(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        
        HttpServletRequest request = getRequest();
        String clientIp = getClientIp(request);
        
        return methodName + ":" + clientIp;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    private String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip != null ? ip : "unknown";
    }

    private static class RateLimitInfo {
        long windowStart = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
    }
}
