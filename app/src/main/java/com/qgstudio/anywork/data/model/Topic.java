package com.qgstudio.anywork.data.model;

/**
 * Created by hunger on 2016/11/24.
 */
public class Topic {
    private int topicId;
    private  int type;//试卷类型 1.选择 2.判断 3.填空
    private  String a;
    private  String b;
    private  String c;
    private  String d;
    private  int isTrue;
    private String content;//题目
    private String key;//答案
    private int score;//得分
    private int textpaperId;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTextpaperId() {
        return textpaperId;
    }

    public void setTextpaperId(int textpaperId) {
        this.textpaperId = textpaperId;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", type=" + type +
                ", A='" + a + '\'' +
                ", B='" + b + '\'' +
                ", C='" + c + '\'' +
                ", D='" + d + '\'' +
                ", isTrue=" + isTrue +
                ", content='" + content + '\'' +
                ", key='" + key + '\'' +
                ", score=" + score +
                ", textpaperId=" + textpaperId +
                '}';
    }
}
