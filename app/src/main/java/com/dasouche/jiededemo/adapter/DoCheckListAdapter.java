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
public class DoCheckListAdapter extends BaseQuickAdapter<String, DoCheckListAdapter.MBaseViewHolder> {
    private final int type;

    public DoCheckListAdapter(int type, int layoutResId, @Nullable  List<String> data) {
        super(layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(MBaseViewHolder helper, String item) {
        TextView tv_keyinfo = helper.getView(R.id.tv_keyinfo);
        TextView tv_operate = helper.getView(R.id.tv_operate);
        tv_keyinfo.setText(item);
        if(type==0){
            tv_operate.setText("下载");
        }else if(type==1){
            tv_operate.setText("审批");
        }else if(type==2){
            tv_operate.setText("删除");
        }
    }


    public static class MBaseViewHolder extends BaseViewHolder {
        public MBaseViewHolder(View view) {
            super(view);
            //为了设配adapter 兼容autoutils

        }

    }
}

