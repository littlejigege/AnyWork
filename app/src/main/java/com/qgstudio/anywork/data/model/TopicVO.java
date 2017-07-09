package com.qgstudio.anywork.data.model;

/**
 * Created by hunger on 2017/4/10.
 */
public class TopicVO {

    private Topic topic;
    private  String answer;

    public  TopicVO(){}

    public TopicVO(Topic topic, String answer) {
        this.topic = topic;
        this.answer = answer;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
