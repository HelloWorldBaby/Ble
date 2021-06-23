package com.dasouche.jiededemo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dasouche.jiededemo.R;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;

import java.util.ArrayList;

/**
 * 创建日期：2021/6/21 14:45
 *
 * @author Caicheng
 * 包名： com.dasouche.jiededemo
 * 类说明：
 */
public class KeyListAdapter extends RecyclerView.Adapter<KeyListAdapter.KeyListViewHolder> {
    private ArrayList<VKeyCertInfo> mData;

    public KeyListAdapter.OnItemClickListener getOnItemClickListener() {
        return OnItemClickListener;
    }

    public void setOnItemClickListener(KeyListAdapter.OnItemClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener OnItemClickListener;

    public KeyListAdapter(ArrayList<VKeyCertInfo> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public KeyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keylist, null);
        return new KeyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyListViewHolder holder, int position) {
        holder.bindData(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClickListener(adapterPosition, mData.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 接口回调
     */
    public interface OnItemClickListener {

        //点击item
        void onItemClickListener(int position, VKeyCertInfo vKeyCertInfo);

    }
    public class KeyListViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_keyinfo;
        private TextView tv_operate;

        public KeyListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_keyinfo = itemView.findViewById(R.id.tv_keyinfo);
            tv_operate = itemView.findViewById(R.id.tv_operate);
        }

        public void bindData(VKeyCertInfo info) {
            if (info == null) return;
            tv_keyinfo.setText("本地可用钥匙：123456778"+info.getReqId());
            tv_operate.setVisibility(View.GONE);
        }

    }

}
