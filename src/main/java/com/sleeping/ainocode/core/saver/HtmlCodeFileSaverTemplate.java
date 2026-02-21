package com.sleeping.ainocode.core.saver;

import cn.hutool.core.util.StrUtil;
import com.sleeping.ainocode.ai.model.HtmlCodeResult;
import com.sleeping.ainocode.exception.BusinessException;
import com.sleeping.ainocode.exception.ErrorCode;
import com.sleeping.ainocode.model.enums.CodeGenTypeEnum;

public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
