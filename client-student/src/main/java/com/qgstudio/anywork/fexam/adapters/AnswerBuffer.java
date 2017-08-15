package com.qgstudio.anywork.fexam.adapters;

import com.qgstudio.anywork.data.model.StudentAnswer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供一个答案的暂存(缓存区)，而不关心数量和来源
 * @author Yason 2017/8/14.
 */

public class AnswerBuffer implements Serializable {

    private List<StudentAnswer> mStudentAnswerBuffer;//学生答案缓存

    private static volatile AnswerBuffer mInstance;

    private AnswerBuffer() {
        mStudentAnswerBuffer = new ArrayList<>(0);
    }

    public static AnswerBuffer getInstance() {
        if (mInstance == null) {
            synchronized (AnswerBuffer.class) {
                if (mInstance == null) {
                    mInstance = new AnswerBuffer();
                }
            }
        }
        return mInstance;
    }

    /**
     * fragment回退时需要调用该方法来
     * 获取原来页面上已填的答案
     */
    public StudentAnswer getStudentAnswer(int position) {
        if (position < mStudentAnswerBuffer.size()) {
            return mStudentAnswerBuffer.get(position);
        }
        return null;
    }

    /**
     * 保存学生的答案，答过的会被覆盖
     */
    public void addStudentAnswer(int position, StudentAnswer studentAnswer) {
        if (position < mStudentAnswerBuffer.size()) {
            mStudentAnswerBuffer.set(position, studentAnswer);
            return;
        }
        mStudentAnswerBuffer.add(studentAnswer);
    }

    /**
     * 获取最终的学生的答案
     */
    public List<StudentAnswer> getResult() {
        List<StudentAnswer> answers = new ArrayList<>();
        for (StudentAnswer answer : mStudentAnswerBuffer) {
            answers.add(answer);
        }
        clear();

        return answers;
    }

    /**
     * 每提交一次数据就清空一次
     * getResult()后调用
     */
    public void clear(){
        mStudentAnswerBuffer.clear();
    }

}
