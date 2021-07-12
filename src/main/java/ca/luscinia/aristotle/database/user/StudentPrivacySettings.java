package ca.luscinia.aristotle.database.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("privacySettings")
public class StudentPrivacySettings extends PrivacySettings{
    private boolean learningData = false;
    private boolean engagementData = true;
    private boolean relativeClassroomPerformance = true;
    private boolean analyticsData = false;

    public boolean isLearningData() {
        return learningData;
    }

    public void setLearningData(boolean learningData) {
        this.learningData = learningData;
    }

    public boolean isEngagementData() {
        return engagementData;
    }

    public void setEngagementData(boolean engagementData) {
        this.engagementData = engagementData;
    }

    public boolean isRelativeClassroomPerformance() {
        return relativeClassroomPerformance;
    }

    public void setRelativeClassroomPerformance(boolean relativeClassroomPerformance) {
        this.relativeClassroomPerformance = relativeClassroomPerformance;
    }

    public boolean isAnalyticsData() {
        return analyticsData;
    }

    public void setAnalyticsData(boolean analyticsData) {
        this.analyticsData = analyticsData;
    }
}
