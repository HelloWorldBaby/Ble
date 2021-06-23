package com.dasouche.jiededemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dasouche.jiededemo.adapter.FriendsListAdapter;
import com.dasouche.jiededemo.adapter.LocalKeyListAdapter;
import com.dasouche.lib_middle_carkey.C52XManage;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.types.AuthType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.LimitType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.SignerType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.VKeyType;

import java.util.ArrayList;

/**
 * 车主借车页面
 */
public class BorrowCarActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_back;
    private RecyclerView rv_friendlist;
    private FriendsListAdapter mAdapter;
    private ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_car);
        btn_back=findViewById(R.id.btn_back);
        rv_friendlist=findViewById(R.id.rv_friendlist);
        btn_back.setOnClickListener(this);
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        rv_friendlist.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        mAdapter = new FriendsListAdapter(2,R.layout.item_keylist,list);
        rv_friendlist.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VKeyInfo vKeyInfo=new VKeyInfo();
                //                    //申请钥匙
                vKeyInfo.setKeyType(String.valueOf(VKeyType.OWNER));
                vKeyInfo.setTimeStart(System.currentTimeMillis() + "");
                vKeyInfo.setTimeStop((System.currentTimeMillis() + 1576800000000L) + "");//当前时间20年后
                vKeyInfo.setAuthType(AuthType.DOOR + "");
                vKeyInfo.setLimitType(LimitType.INTERVAL + "");
                vKeyInfo.setLimitCounts(100 + "");
                vKeyInfo.setSignerId("userIdHashData");
                vKeyInfo.setUserToken("userToken");
                vKeyInfo.setVin("LNBSCCAK1MD824849");
                //非必
                vKeyInfo.setName("aaa");//用户名
                vKeyInfo.setCertId("");//证书唯一标示值
                vKeyInfo.setCarId("");//车辆唯一标示值
                vKeyInfo.setSerialNumber("");//钥匙唯一序列号
                vKeyInfo.setCarlicense("");//车辆号牌
                vKeyInfo.setUserId("");//userId
                vKeyInfo.setSignerType(SignerType.OWNER + "");//批复者类型
                //申请车钥匙
                C52XManage.getInstance(mContext).applyKey(vKeyInfo);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
            finish();
            break;
        }
    }
}