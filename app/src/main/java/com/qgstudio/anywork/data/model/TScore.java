package com.qgstudio.anywork.data.model;

public class TScore {

    private Integer studentId;
    private Integer textId;
    private Double sscore;
    private Double tscore;
    private java.sql.Date createTime;
	
    public TScore() {
        super();
    }

    public TScore(Integer studentId,Integer textId,Double sscore,Double tscore,java.sql.Date createTime) {
        super();
        this.studentId = studentId;
        this.textId = textId;
        this.sscore = sscore;
        this.tscore = tscore;
        this.createTime = createTime;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTextId() {
        return this.textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Double getSscore() {
        return this.sscore;
    }

    public void setSscore(Double sscore) {
        this.sscore = sscore;
    }

    public Double getTscore() {
        return this.tscore;
    }

    public void setTscore(Double tscore) {
        this.tscore = tscore;
    }

    public java.sql.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.sql.Date createTime) {
        this.createTime = createTime;
    }


}
