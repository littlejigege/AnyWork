package com.qgstudio.anywork.data.model;

import java.util.Date;

/**
 * Created by logan on 2016/11/24.
 */
public class StudentAnswer {
    private String choiceAnswer;//选择题答案
    private String judgeAnswer;//判断题答案
    private String fillingAnswer;//填空题答案
    private String askingAnswer;//问答题答案
    private String codeAnswer;//编程题答案
    private String comprehensiveAnswer;//综合题答案
    private String userName;//答题者名字
    private int testId;//试卷id
    private double source;//答题分数
    private long startTime;//开始答题的时间
    private long endTime;//答题结束的时间
    private String rightAnswer;//正确答案
    private String judege;//答题情况

    public void flushAnswer() {
        choiceAnswer = choiceAnswer.substring(0, choiceAnswer.length() - 1);
        judgeAnswer = judgeAnswer.substring(0, judgeAnswer.length() - 1);
        fillingAnswer = fillingAnswer.substring(0, fillingAnswer.length() - 1);
        askingAnswer = askingAnswer.substring(0, askingAnswer.length() - 1);
        codeAnswer = codeAnswer.substring(0, codeAnswer.length() - 1);
        comprehensiveAnswer = comprehensiveAnswer.substring(0, comprehensiveAnswer.length() - 1);
    }

    public StudentAnswer() {
        this.choiceAnswer = "";
        this.judgeAnswer = "";
        this.fillingAnswer = "";
        this.askingAnswer = "";
        this.codeAnswer = "";
        this.comprehensiveAnswer = "";
    }

    public StudentAnswer(String choiceAnswer, String judgeAnswer, String fillingAnswer, String askingAnswer, String codeAnswer, String comprehensiveAnswer,int testId, double source, long startTime, long endTime) {
        this.choiceAnswer = choiceAnswer;
        this.judgeAnswer = judgeAnswer;
        this.fillingAnswer = fillingAnswer;
        this.askingAnswer = askingAnswer;
        this.testId = testId;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.codeAnswer = codeAnswer;
        this.comprehensiveAnswer = comprehensiveAnswer;
    }

    public StudentAnswer(String choiceAnswer, String judgeAnswer, String fillingAnswer, String askingAnswer, int testId, double source, long startTime, long endTime) {
        this.choiceAnswer = choiceAnswer;
        this.judgeAnswer = judgeAnswer;
        this.fillingAnswer = fillingAnswer;
        this.askingAnswer = askingAnswer;
        this.testId = testId;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "choiceAnswer='" + choiceAnswer + '\'' +
                ", judgeAnswer='" + judgeAnswer + '\'' +
                ", fillingAnswer='" + fillingAnswer + '\'' +
                ", askingAnswer='" + askingAnswer + '\'' +
                ", userName='" + userName + '\'' +
                ", testId=" + testId +
                ", source=" + source +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", judege='" + judege + '\'' +
                '}';
    }

    public String getCodeAnswer() {
        return codeAnswer;
    }

    public void setCodeAnswer(String codeAnswer) {
        this.codeAnswer = codeAnswer;
    }

    public String getComprehensiveAnswer() {
        return comprehensiveAnswer;
    }

    public void setComprehensiveAnswer(String comprehensiveAnswer) {
        this.comprehensiveAnswer = comprehensiveAnswer;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getJudege() {
        return judege;
    }

    public void setJudege(String judege) {
        this.judege = judege;
    }

    public String getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(String choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
    }

    public String getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setJudgeAnswer(String judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public String getFillingAnswer() {
        return fillingAnswer;
    }

    public void setFillingAnswer(String fillingAnswer) {
        this.fillingAnswer = fillingAnswer;
    }

    public String getAskingAnswer() {
        return askingAnswer;
    }

    public void setAskingAnswer(String askingAnswer) {
        this.askingAnswer = askingAnswer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
