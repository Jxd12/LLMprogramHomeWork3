
// src/main/java/org/example/controller/LLMController.java
package org.example.controller;

import org.example.dto.TravelPlanDTO;
import org.example.service.LLMService;
import org.example.vo.TravelPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/llm")
public class LLMController {

    private final LLMService llmService;

    @Autowired
    public LLMController(LLMService llmService) {
        this.llmService = llmService;
    }

    @PostMapping("/process")
    public String processInput(@RequestBody String input) {
        return llmService.processRequest(input);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "LLM Service is running";
    }

    // 更新返回类型为 TravelPlanVO
    @PostMapping("/travel-plan")
    public TravelPlanVO generateTravelPlan(@RequestBody String input) {
        return llmService.processTravelPlan(input);
    }

    @PostMapping("/travel-plan-final")
    public TravelPlanDTO generateFinalTravelPlan(@RequestBody TravelPlanVO input) {
        return llmService.generateFinalTravelPlan(input);
    }
}