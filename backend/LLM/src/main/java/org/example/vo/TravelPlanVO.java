// src/main/java/org/example/vo/TravelPlanVO.java
package org.example.vo;

public class TravelPlanVO {
    private String destination;
    private String duration;
    private String budget;
    private String companions;
    private String preferences;
    
    // 无参构造函数
    public TravelPlanVO() {}
    
    // 全参构造函数
    public TravelPlanVO(String destination, String duration, String budget, String companions, String preferences) {
        this.destination = destination;
        this.duration = duration;
        this.budget = budget;
        this.companions = companions;
        this.preferences = preferences;
    }
    
    // Getter 和 Setter 方法
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getBudget() {
        return budget;
    }
    
    public void setBudget(String budget) {
        this.budget = budget;
    }
    
    public String getCompanions() {
        return companions;
    }
    
    public void setCompanions(String companions) {
        this.companions = companions;
    }
    
    public String getPreferences() {
        return preferences;
    }
    
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
