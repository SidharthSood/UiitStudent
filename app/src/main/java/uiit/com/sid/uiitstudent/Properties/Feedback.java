package uiit.com.sid.uiitstudent.Properties;

import java.util.Map;

public class Feedback {
    String feedbackId;
    String feedbackText;
    String senderId;
    Map<String,String> timeStamp;

    public Feedback() {
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setTimeStamp(Map<String, String> timeStamp) {
        this.timeStamp = timeStamp;
    }
}
