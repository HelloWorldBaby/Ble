package com.dasouche.jiededemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dasouche.jiededemo.adapter.DoCheckListAdapter;
import com.dasouche.jiededemo.adapter.LocalKeyListAdapter;

import java.util.ArrayList;

public class DoCheckListActivity extends AppCompatActivity {

    private RecyclerView rvNotdownload;
    private RecyclerView rvNotallow;
    private RecyclerView rvNodel;
    private DoCheckListAdapter mAdapter;
    private ArrayList<String> list=new ArrayList<>();
    private DoCheckListAdapter mAdapter2;
    private DoCheckListAdapter mAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_check_list);

        for (int i=0;i<3;i++){
            list.add("111222323");
        }
        initView();
    }

    private void initView() {
        rvNotdownload = (RecyclerView) findViewById(R.id.rv_notdownload);
        rvNotallow = (RecyclerView) findViewById(R.id.rv_notallow);
        rvNodel = (RecyclerView) findViewById(R.id.rv_nodel);
        rvNotdownload.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter = new DoCheckListAdapter(0,R.layout.item_keylist,list);
        rvNotdownload.setAdapter(mAdapter);

        rvNotallow.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter2 = new DoCheckListAdapter(1,R.layout.item_keylist,list);
        rvNotallow.setAdapter(mAdapter2);

        rvNodel.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter3 = new DoCheckListAdapter(2,R.layout.item_keylist,list);
        rvNodel.setAdapter(mAdapter3);
    }
}