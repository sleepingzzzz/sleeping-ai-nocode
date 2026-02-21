package com.sleeping.ainocode.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.sleeping.ainocode.model.dto.app.AppQueryRequest;
import com.sleeping.ainocode.model.entity.App;
import com.sleeping.ainocode.model.entity.User;
import com.sleeping.ainocode.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AppService extends IService<App> {

    AppVO getAppVO(App app);

    List<AppVO> getAppVOList(List<App> appList);

    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    Flux<String> chatToGenCode(Long appId, String message, User loginUser);
}
