package com.qgstudio.anywork.fexam;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.data.model.Topic;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/4/12.
 */

public class ViewAdapter extends PagerAdapter {

    interface OnTextpaperSubmitListener {
        void onTextpaperSubmit(StudentAnswer studentAnswer);
    }

    private List<Topic> mTopicList;//试题
    private List<View> mViewList;

    private int mTestId;
    private long mStartTime;
    private long mEndTime;

    private OnTextpaperSubmitListener mOnTextpaperSubmitListener;

    public ViewAdapter(@NonNull Textpaper textpaper) {
        mTopicList = textpaper.getTopics();
        mTopicList.add(getLastTopic());
        mTestId = textpaper.getTextpaperId();
        mStartTime = textpaper.getCreateTime();
        mEndTime = textpaper.getEndingTime();
        mViewList = new ArrayList<>(0);
    }

    private Topic getLastTopic() {
        Topic topic = new Topic();
        topic.setType(-1);
        return topic;
    }

//    private void filterTopicType(Textpaper textpaper) {
//        mTopicList = new ArrayList<>(0);
//        List<Topic> topicList = textpaper.getTopics();
//        for (Topic topic : topicList) {
//            int type = topic.getType();
//            if (type == 1 || type == 2 || type == 3) {
//                mTopicList.add(topic);
//            }
//        }
//        Topic topic = new Topic();
//        topic.setType(-1);
//        mTopicList.add(topic);
//    }

    public void setOnTextpaperSubmitListener(OnTextpaperSubmitListener onTextpaperSubmitListener) {
        mOnTextpaperSubmitListener = onTextpaperSubmitListener;
    }

    @Override
    public int getCount() {
        return mTopicList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mTopicList != null) {
            View view = getView(container, position);
            mViewList.add(position, view);
            container.addView(mViewList.get(position));
        }
        return mViewList.get(position);
    }

    public int getItemViewType(int position) {
        return mTopicList.get(position).getType();
    }

    public int getViewTypeCount() {
        return 3;
    }

    public Topic getItem(int position) {
        return mTopicList.get(position);
    }

    private View getView(ViewGroup container, int position) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        Topic topic = getItem(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 1://选择
                view = inflater.inflate(R.layout.pager_choice, container, false);
                ChoiceHolder choice = new ChoiceHolder(view);
                choice.q.setText("单选题：" + topic.getContent());
                choice.a.setText(topic.getA());
                choice.b.setText(topic.getB());
                choice.c.setText(topic.getC());
                choice.d.setText(topic.getD());
                view.setTag(choice);
                break;
            case 2://判断
                view = inflater.inflate(R.layout.pager_judge, container, false);
                JudgeHolder judge = new JudgeHolder(view);
                judge.q.setText("判断题：" + topic.getContent());
                view.setTag(judge);
                break;
            case 3://填空
                view = inflater.inflate(R.layout.pager_filling, container, false);
                FillingHolder filling = new FillingHolder(view);
                filling.q.setText("填空题：" + topic.getContent());
                view.setTag(filling);
                break;
            case 4://问答
                view = inflater.inflate(R.layout.pager_filling, container, false);
                FillingHolder asking = new FillingHolder(view);
                asking.q.setText("问答题：" + topic.getContent());
                view.setTag(asking);
                break;
            case 5://编程
                view = inflater.inflate(R.layout.pager_filling, container, false);
                FillingHolder coding = new FillingHolder(view);
                coding.q.setText("编程题：" + topic.getContent());
                view.setTag(coding);
                break;
            case 6://综合
                view = inflater.inflate(R.layout.pager_filling, container, false);
                FillingHolder comprehensiving = new FillingHolder(view);
                comprehensiving.q.setText("综合题：" + topic.getContent());
                view.setTag(comprehensiving);
                break;
            case -1://提交
                view = inflater.inflate(R.layout.pager_finish, container, false);
                Button btn = (Button) view.findViewById(R.id.btn_test_submit);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnTextpaperSubmitListener != null) {
                            StudentAnswer studentAnswer = getStudentAnswer();
                            mOnTextpaperSubmitListener.onTextpaperSubmit(studentAnswer);
                        }
                    }
                });
                break;
            default:
                //默认为空 view
                view = new View(container.getContext());
                break;
        }
        return view;
    }

    private StudentAnswer getStudentAnswer() {
        StudentAnswer studentAnswer = new StudentAnswer();
        for (int position = 0; position < mViewList.size() - 1; position++) {
            View view = mViewList.get(position);
            switch (getItemViewType(position)) {
                case 1://选择
                    ChoiceHolder choice = (ChoiceHolder) view.getTag();
                    switch (choice.g.getCheckedRadioButtonId()) {
                        case R.id.rb_choice_a:
                            studentAnswer.setChoiceAnswer(studentAnswer.getChoiceAnswer() + "A※");
                            break;
                        case R.id.rb_choice_b:
                            studentAnswer.setChoiceAnswer(studentAnswer.getChoiceAnswer() + "B※");
                            break;
                        case R.id.rb_choice_c:
                            studentAnswer.setChoiceAnswer(studentAnswer.getChoiceAnswer() + "C※");
                            break;
                        case R.id.rb_choice_d:
                            studentAnswer.setChoiceAnswer(studentAnswer.getChoiceAnswer() + "D※");
                            break;
                        default:
                            studentAnswer.setChoiceAnswer(studentAnswer.getChoiceAnswer() + "Z※");
                    }
                    break;
                case 2://判断
                    JudgeHolder judge = (JudgeHolder) view.getTag();
                    switch (judge.g.getCheckedRadioButtonId()) {
                        case R.id.rb_judge_true:
                            studentAnswer.setJudgeAnswer(studentAnswer.getJudgeAnswer() + "1※");
                            break;
                        case R.id.rb_judge_false:
                            studentAnswer.setJudgeAnswer(studentAnswer.getJudgeAnswer() + "0※");
                            break;
                        default:
                            studentAnswer.setJudgeAnswer(studentAnswer.getJudgeAnswer() + "-1※");
                    }
                    break;
                case 3://填空
                    FillingHolder filling = (FillingHolder) view.getTag();
                    String fill = filling.f.getText().toString() + "";
                    fill = fill == "" ? "null" + "※" : fill + "※";
                    studentAnswer.setFillingAnswer(studentAnswer.getFillingAnswer() + fill);
                    break;
                case 4://问答
                    FillingHolder asking = (FillingHolder) view.getTag();
                    String ask = asking.f.getText().toString() + "";
                    ask = ask == "" ? "null" + "※" : ask + "※";
                    studentAnswer.setAskingAnswer(studentAnswer.getAskingAnswer() + ask);
                    break;
                case 5://编程
                    FillingHolder codeing = (FillingHolder) view.getTag();
                    String code = codeing.f.getText().toString() + "";
                    code = code == "" ? "null" + "※" : code + "※";
                    studentAnswer.setCodeAnswer(studentAnswer.getCodeAnswer() + code);
                case 6://综合
                    FillingHolder comprehensiving = (FillingHolder) view.getTag();
                    String comprehensive = comprehensiving.f.getText().toString() + "";
                    comprehensive = comprehensive == "" ? "null" + "※" : comprehensive + "※";
                    studentAnswer.setComprehensiveAnswer(studentAnswer.getComprehensiveAnswer() + comprehensive);
                    break;
                default:
                    break;
            }
        }

        studentAnswer.flushAnswer();

        studentAnswer.setTestId(mTestId);
        studentAnswer.setStartTime(mStartTime);
        studentAnswer.setEndTime(mEndTime);

        String s = GsonUtil.GsonString(studentAnswer);
//        Log.e("tag", s);
        return studentAnswer;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    class ChoiceHolder {
        @BindView(R.id.tv_choice_question) TextView q;
        @BindView(R.id.rg_choice_group) RadioGroup g;
        @BindView(R.id.rb_choice_a) RadioButton a;
        @BindView(R.id.rb_choice_b) RadioButton b;
        @BindView(R.id.rb_choice_c) RadioButton c;
        @BindView(R.id.rb_choice_d) RadioButton d;
        public ChoiceHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class JudgeHolder {
        @BindView(R.id.tv_judge_question) TextView q;
        @BindView(R.id.rg_judge_group) RadioGroup g;
        public JudgeHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class FillingHolder {
        @BindView(R.id.tv_filling_question) TextView q;
        @BindView(R.id.edi_filling_fill) EditText f;
        public FillingHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
