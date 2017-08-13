package com.qgstudio.anywork.fgrade;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.utils.GsonUtil;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by chenyi on 2017/7/10.
 */

public class GradeAdapter extends Adapter<RecyclerView.ViewHolder> {

    private final int ITEM_TYPE_TITLE = 1;
    private final int ITEM_TYPE_CONTENT = 2;

    private List<StudentAnswerResult> mDatas;
    private Context mContext;

    private ResultListener rListener = new ResultListener() {
        @Override
        public void getTestResult(int id) {
        }
    };

    public void setrListener(ResultListener rListener) {
        this.rListener = rListener;
    }

    interface ResultListener {
        void getTestResult(int id);
    }

    public void setmDatas(List<StudentAnswerResult> datas) {

        int currentType = 0;
        int trueCount = 0;
        int count = 0;
        StudentAnswerResult currentTitle = null;

        for (int i = 0; i < datas.size(); i++) {
            StudentAnswerResult result = datas.get(i);
            if (result.getType() >= 4)
                break;
            if (result.getType() != currentType) {
                if (currentTitle != null) {
                    currentTitle.setContent(currentType, trueCount + "/" + count);
                }
                currentTitle = new StudentAnswerResult(0, null);
                mDatas.add(currentTitle);
                currentType = result.getType();
                trueCount = 0;
                count = 0;
            }
            if (result.getContent().equals("true")) {
                trueCount++;
            }
            count++;
            result.setPosition(i + 1);
            mDatas.add(result);
        }
        if (currentTitle != null) {
            currentTitle.setContent(currentType, trueCount + "/" + count);
            mDatas.add(new StudentAnswerResult(0, "主观题由老师进行评分，暂无答案。"));
        }
        Log.i(TAG, "setmDatas: 转换后的数据源" + GsonUtil.GsonString(mDatas));
    }

    public GradeAdapter(Context context) {
        super();
        mDatas = new ArrayList<>();
        mContext = context;
    }

    public GradeAdapter(List<StudentAnswerResult> datas, Context context) {
        super();
        mDatas = new ArrayList<>();
        setmDatas(datas);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (getDatasType(position) == 0) {
            return ITEM_TYPE_TITLE;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int viewId;
        if (viewType == ITEM_TYPE_TITLE) {
            viewId = R.layout.item_grade_title;
        } else {
            viewId = R.layout.item_grade_content;
        }
        View layout = LayoutInflater.from(mContext).inflate(viewId, parent, false);
        return new ItemHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final StudentAnswerResult gInfo = mDatas.get(position);
        TextView text = ((ItemHolder) holder).text;
        if (getItemViewType(position) == ITEM_TYPE_TITLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                text.setLetterSpacing(0.2f);
            }
            text.setText(gInfo.getContent());
        } else {
            text.setText(gInfo.getPosition()+"");
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("点击");
                    rListener.getTestResult(gInfo.getId());
                }
            });
            if (gInfo.getContent().equals("true")) {
                text.setBackground(ContextCompat.getDrawable(mContext,
                        R.drawable.ic_fiber_green));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    int getDatasType(int position) {
        return mDatas.get(position).getType();
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text) TextView text;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
