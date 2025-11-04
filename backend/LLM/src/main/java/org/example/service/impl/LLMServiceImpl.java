
// src/main/java/org/example/service/impl/LLMServiceImpl.java
package org.example.service.impl;

import org.example.LLM.LLMDemo;
import org.example.dto.ActivityDTO;
import org.example.dto.DailyItineraryDTO;
import org.example.dto.TravelPlanDTO;
import org.example.exception.BusinessException;
import org.example.exception.SystemException;
import org.example.service.LLMService;
import org.example.vo.TravelPlanVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            return LLMDemo.extractTravelPlanFromText(input);
        } catch (Exception e) {
            throw new SystemException("生成旅行计划时发生错误");
        }
    }

    @Override
    public TravelPlanDTO generateFinalTravelPlan(TravelPlanVO input) {
        if (input == null) {
            throw new BusinessException("旅行计划输入不能为空");
        }

        try {
            // 调用大模型API生成详细旅行计划
            return LLMDemo.generateDetailedTravelPlan(input);
        } catch (Exception e) {
            throw new SystemException("生成最终旅行计划时发生错误");
        }
    }

}