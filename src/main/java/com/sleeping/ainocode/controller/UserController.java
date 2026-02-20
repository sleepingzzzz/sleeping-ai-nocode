package com.sleeping.ainocode.controller;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.paginate.Page;
import com.sleeping.ainocode.annotation.AuthCheck;
import com.sleeping.ainocode.annotation.RateLimit;
import com.sleeping.ainocode.common.BaseResponse;
import com.sleeping.ainocode.common.DeleteRequest;
import com.sleeping.ainocode.common.ResultUtils;
import com.sleeping.ainocode.common.SanitizeUtils;
import com.sleeping.ainocode.constant.UserConstant;
import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import com.sleeping.ainocode.exception.ThrowUtils;
import com.sleeping.ainocode.model.dto.user.UserAddRequest;
import com.sleeping.ainocode.model.dto.user.UserLoginRequest;
import com.sleeping.ainocode.model.dto.user.UserQueryRequest;
import com.sleeping.ainocode.model.dto.user.UserRegisterRequest;
import com.sleeping.ainocode.model.dto.user.UserUpdateRequest;
import com.sleeping.ainocode.model.entity.User;
import com.sleeping.ainocode.model.vo.LoginUserVO;
import com.sleeping.ainocode.model.vo.UserVO;
import com.sleeping.ainocode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @RateLimit(value = 5, windowSeconds = 60, message = "注册请求过于频繁，请稍后再试")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    @RateLimit(value = 10, windowSeconds = 60, message = "登录请求过于频繁，请稍后再试")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout() {
        boolean result = userService.userLogout();
        return ResultUtils.success(result);
    }

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
        User user = new User();
        BeanUtil.copyProperties(userAddRequest, user);
        user.setUserAccount(SanitizeUtils.sanitize(user.getUserAccount()));
        user.setUserName(SanitizeUtils.sanitizePreserveBasic(user.getUserName()));
        user.setUserProfile(SanitizeUtils.sanitizePreserveBasic(user.getUserProfile()));
        user.setUserAvatar(SanitizeUtils.sanitizePreserveBasic(user.getUserAvatar()));
        String randomPassword = userService.generateRandomPassword();
        String encryptPassword = userService.getEncryptPassword(randomPassword);
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserVO> getUserById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(userService.getUserVO(user));
    }

    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(userService.getUserVO(user));
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = new User();
        BeanUtil.copyProperties(userUpdateRequest, user);
        user.setUserName(SanitizeUtils.sanitizePreserveBasic(user.getUserName()));
        user.setUserProfile(SanitizeUtils.sanitizePreserveBasic(user.getUserProfile()));
        user.setUserAvatar(SanitizeUtils.sanitizePreserveBasic(user.getUserAvatar()));
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
        ThrowUtils.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = userQueryRequest.getPageNum();
        long pageSize = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(Page.of(pageNum, pageSize),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(pageNum, pageSize, userPage.getTotalRow());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }
}
