
// src/main/java/org/example/service/impl/LLMServiceImpl.java
package org.example.service.impl;

import org.example.exception.BusinessException;
import org.example.exception.SystemException;
import org.example.service.LLMService;
import org.example.vo.TravelPlanVO;
import org.springframework.stereotype.Service;

@Service
public class LLMServiceImpl implements LLMService {

    @Override
    public String processRequest(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new BusinessException("输入内容不能为空");
        }

        try {
            // 模拟处理逻辑
            return "Processed: " + input;
        } catch (Exception e) {
            throw new SystemException("处理请求时发生错误");
        }
    }

    @Override
    public TravelPlanVO processTravelPlan(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new BusinessException("旅行计划输入不能为空");
        }

        try {
            // 模拟处理逻辑
            return new TravelPlanVO(
                    "日本",
                    "5天",
                    "1万元",
                    "家庭出行（含儿童）",
                    "美食、动漫"
            );
        } catch (Exception e) {
            throw new SystemException("生成旅行计划时发生错误");
        }
    }
}