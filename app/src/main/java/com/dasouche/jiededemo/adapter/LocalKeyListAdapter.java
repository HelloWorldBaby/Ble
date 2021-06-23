package com.dasouche.jiededemo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dasouche.jiededemo.R;
import com.dasouche.lib_middle_carkey.C52XManage;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2021/6/21 14:45
 *
 * @author Caicheng
 * 包名： com.dasouche.jiededemo
 * 类说明：
 */
public class LocalKeyListAdapter extends BaseQuickAdapter<String,LocalKeyListAdapter.MBaseViewHolder> {
    private final int type;
    private OnItemClickListener onItemClickListener;
//    public OnItemClickListener getOnItemClickListener() {
//        return onItemClickListener;
//    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public LocalKeyListAdapter(int type, int layoutResId, @Nullable  List<String> data) {
        super(layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(MBaseViewHolder helper, String item) {
        TextView tv_keyinfo = helper.getView(R.id.tv_keyinfo);
        TextView tv_operate = helper.getView(R.id.tv_operate);
        tv_keyinfo.setText(item);
        if(type==0){
            tv_operate.setText("可使用");
        }else if(type==1){
            tv_operate.setText("激活");
        }else if(type==2){
            tv_operate.setText("下载");
        }
        tv_operate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){

                }
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

