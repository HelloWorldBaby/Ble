package com.dasouche.jiededemo;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dasouche.jiededemo.adapter.KeyListAdapter;
import com.dasouche.jiededemo.adapter.LocalKeyListAdapter;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;

import java.util.ArrayList;

public class LocalKeyListActivity extends BaseActivity {

    private androidx.recyclerview.widget.RecyclerView rvHasdownload;
    private androidx.recyclerview.widget.RecyclerView rvNotactive;
    private androidx.recyclerview.widget.RecyclerView rvNotdownload;
    private LocalKeyListAdapter mAdapter;
    private ArrayList<String> list=new ArrayList<>();
    private LocalKeyListAdapter mAdapter2;
    private LocalKeyListAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_key_list);
        for (int i=0;i<3;i++){
            list.add("111222323");
        }
        initView();
    }

    private void initView() {
        rvHasdownload = (RecyclerView) findViewById(R.id.rv_hasdownload);
        rvNotactive = (RecyclerView) findViewById(R.id.rv_notactive);
        rvNotdownload = (RecyclerView) findViewById(R.id.rv_notdownload);
        rvHasdownload.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter = new LocalKeyListAdapter(0,R.layout.item_keylist,list);
        rvHasdownload.setAdapter(mAdapter);

        rvNotactive.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter2 = new LocalKeyListAdapter(1,R.layout.item_keylist,list);
        rvNotactive.setAdapter(mAdapter2);

        rvNotdownload.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter3 = new LocalKeyListAdapter(2,R.layout.item_keylist,list);
        rvNotdownload.setAdapter(mAdapter3);
    }
}