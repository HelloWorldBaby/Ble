package com.dasouche.jiededemo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dasouche.jiededemo.R;

import java.util.List;

/**
 * 创建日期：2021/6/21 14:45
 *
 * @author Caicheng
 * 包名： com.dasouche.jiededemo
 * 类说明：
 */
public class FriendsListAdapter extends BaseQuickAdapter<String, FriendsListAdapter.MBaseViewHolder> {
    private final int type;
    private OnItemClickListener onItemClickListener;
//    public OnItemClickListener getOnItemClickListener() {
//        return onItemClickListener;
//    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public FriendsListAdapter(int type, int layoutResId, @Nullable  List<String> data) {
        super(layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(MBaseViewHolder helper, String item) {
        TextView tv_keyinfo = helper.getView(R.id.tv_keyinfo);
        TextView tv_operate = helper.getView(R.id.tv_operate);
        tv_keyinfo.setText(item);
        tv_operate.setText("分享");
        tv_operate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    public static class MBaseViewHolder extends BaseViewHolder {
        public MBaseViewHolder(View view) {
            super(view);
            //为了设配adapter 兼容autoutils

        }

    }
}

