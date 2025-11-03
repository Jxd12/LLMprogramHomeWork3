
// src/main/java/org/example/service/LLMService.java
package org.example.service;

import org.example.vo.TravelPlanVO;
import java.util.Map;

public interface LLMService {
    String processRequest(String input);

    // 更新方法返回类型为 TravelPlanVO
    TravelPlanVO processTravelPlan(String input);
}