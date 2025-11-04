package org.example.LLM;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.lang.System;
import java.util.Properties;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ActivityDTO;
import org.example.dto.DailyItineraryDTO;
import org.example.dto.TravelPlanDTO;
import org.example.vo.TravelPlanVO;

public class LLMDemo {
    //  若使用新加坡地域的模型，请释放下列注释
    //  static {Constants.baseHttpApiUrl="https://dashscope-intl.aliyuncs.com/api/v1";}
    private static Properties properties = new Properties();

    static {
        try (InputStream input = LLMDemo.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("无法找到 application.properties 配置文件");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static GenerationResult callWithMessage(String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("你是一个信息提取助手。请从用户输入中提取旅行计划相关信息，并以JSON格式返回。" +
                        "需要提取的字段包括：destination(目的地), duration(持续时间), budget(预算), companions(同行人员), preferences(偏好)。" +
                        "如果某个字段在输入中未提及，请将该字段设为无要求。" +
                        "只返回JSON，不要包含其他文字。例如：{\"destination\":\"日本\",\"duration\":\"5天\",\"budget\":\"1万元\",\"companions\":\"家庭出行（含儿童）\",\"preferences\":\"美食、动漫\"}")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(userInput)
                .build();
        String apiKey = properties.getProperty("dashscope.api-key");
        String model = properties.getProperty("dashscope.model", "qwen-plus");

        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用阿里云百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(apiKey)
                // 模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                .model(model)
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }


    public static TravelPlanVO extractTravelPlanFromText(String userInput) {
        try {
            GenerationResult result = callWithMessage(userInput);
            String jsonResponse = result.getOutput().getChoices().get(0).getMessage().getContent();

            // 解析JSON响应为TravelPlanVO对象
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, TravelPlanVO.class);
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.err.println("调用大模型API时发生错误：" + e.getMessage());
            System.out.println("请参考文档：https://help.aliyun.com/zh/model-studio/developer-reference/error-code");
        } catch (Exception e) {
            System.err.println("解析响应时发生错误：" + e.getMessage());
        }
        return new TravelPlanVO(); // 返回空的TravelPlanVO对象
    }

    public static TravelPlanDTO generateDetailedTravelPlan(TravelPlanVO input) {
        try {
            StringBuilder prompt = new StringBuilder();
            prompt.append("请根据以下旅行需求生成详细的旅行计划：\n");
            if (input.getDestination() != null && !input.getDestination().isEmpty()) {
                prompt.append("目的地：").append(input.getDestination()).append("\n");
            }
            if (input.getDuration() != null && !input.getDuration().isEmpty()) {
                prompt.append("持续时间：").append(input.getDuration()).append("\n");
            }
            if (input.getBudget() != null && !input.getBudget().isEmpty()) {
                prompt.append("预算：").append(input.getBudget()).append("\n");
            }
            if (input.getCompanions() != null && !input.getCompanions().isEmpty()) {
                prompt.append("同行人员：").append(input.getCompanions()).append("\n");
            }
            if (input.getPreferences() != null && !input.getPreferences().isEmpty()) {
                prompt.append("偏好：").append(input.getPreferences()).append("\n");
            }


            GenerationResult result = callWithDetailedPlanMessage(prompt.toString());
            String jsonResponse = result.getOutput().getChoices().get(0).getMessage().getContent();

            // 解析JSON响应为TravelPlanDTO对象
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, TravelPlanDTO.class);
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.err.println("调用大模型API时发生错误：" + e.getMessage());
            System.out.println("请参考文档：https://help.aliyun.com/zh/model-studio/developer-reference/error-code");
        } catch (Exception e) {
            System.err.println("解析响应时发生错误：" + e.getMessage());
        }
        return null; // 返回默认的TravelPlanDTO对象
    }

    public static GenerationResult callWithDetailedPlanMessage(String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("你是一个专业的旅行规划师。请根据用户输入生成详细的旅行计划，严格按照指定的JSON格式返回结果。" +
                        "用户会输入目的地,持续时间,预算,同行人员,偏好" +
                        "输出格式示例：\n" +
                        "{\n" +
                        "  \"destination\": \"北京\",\n" +
                        "  \"duration\": \"3天\",\n" +
                        "  \"budget\": \"2000元\",\n" +
                        "  \"companions\": \"朋友\",\n" +
                        "  \"preferences\": \"历史文化\",\n" +
                        "  \"dailyItineraries\": [\n" +
                        "    {\n" +
                        "      \"dayNumber\": 1,\n" +
                        "      \"accommodationPlace\": \"北京市中心酒店\",\n" +
                        "      \"accommodationBudget\": \"500元/晚\",\n" +
                        "      \"accommodationContent\": \"位于市中心，交通便利\",\n" +
                        "      \"activities\": [\n" +
                        "        {\n" +
                        "          \"activityType\": \"breakfast\",\n" +
                        "          \"place\": \"酒店餐厅\",\n" +
                        "          \"budget\": \"50元\",\n" +
                        "          \"content\": \"享用中式早餐\",\n" +
                        "          \"transport\": \"步行\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}\n" +
                        "注意事项：\n" +
                        "1. activityType只能是以下值之一：breakfast, morning, lunch, afternoon, dinner, evening\n" +
                        "2. 请根据实际天数生成对应的dailyItineraries\n" +
                        "3. 每天至少包含breakfast, morning, lunch, afternoon, dinner等活动\n" +
                        "4. 最后一天可以不包含住宿信息\n" +
                        "5. 只返回JSON格式数据，不要包含其他解释文字")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(userInput)
                .build();
        String apiKey = properties.getProperty("dashscope.api-key");
        String model = properties.getProperty("dashscope.model", "qwen-plus");

        GenerationParam param = GenerationParam.builder()
                .apiKey(apiKey)
                .model(model)
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }

    public static void main(String[] args) {
        // 创建TravelPlanVO输入对象
        TravelPlanVO input = new TravelPlanVO();
        input.setDestination("日本");
        input.setDuration("5天4晚");
        input.setBudget("15000元");
        input.setCompanions("家庭出行（含儿童）");
        input.setPreferences("动漫、美食、温泉");

        // 调用generateDetailedTravelPlan方法生成详细旅行计划
        TravelPlanDTO detailedPlan = LLMDemo.generateDetailedTravelPlan(input);

        // 输出结果
        if (detailedPlan != null) {
            System.out.println("生成的详细旅行计划：");
            System.out.println("目的地: " + detailedPlan.getDestination());
            System.out.println("持续时间: " + detailedPlan.getDuration());
            System.out.println("预算: " + detailedPlan.getBudget());
            System.out.println("同行人员: " + detailedPlan.getCompanions());
            System.out.println("偏好: " + detailedPlan.getPreferences());

            // 输出每日行程
            if (detailedPlan.getDailyItineraries() != null) {
                for (int i = 0; i < detailedPlan.getDailyItineraries().size(); i++) {
                    DailyItineraryDTO day = detailedPlan.getDailyItineraries().get(i);
                    System.out.println("第" + day.getDayNumber() + "天行程：");
                    System.out.println("  住宿: " + day.getAccommodationPlace());
                    System.out.println("  住宿预算: " + day.getAccommodationBudget());

                    // 输出当天活动
                    if (day.getActivities() != null) {
                        for (ActivityDTO activity : day.getActivities()) {
                            System.out.println("  " + activity.getActivityType() + ": " +
                                    activity.getPlace() + " - " + activity.getContent());
                        }
                    }
                }
            }
        } else {
            System.out.println("生成旅行计划失败");
        }
    }


}