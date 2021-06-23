package com.dasouche.jiededemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dasouche.jiededemo.adapter.KeyListAdapter;
import com.dasouche.lib_middle_carkey.C52StateListener;
import com.dasouche.lib_middle_carkey.C52XManage;
import com.dasouche.lib_middle_carkey.PingBoSdkUtils;
import com.dasouche.lib_middle_carkey.constants.Constants;
import com.dasouche.lib_middle_carkey.constants.PermissionConstants;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.request.ForwardScope;
import com.trustkernel.dkfjava.utils.KppException;
import com.trustkernel.kppsdkv2.digitalkey.exception.KPPException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, C52StateListener {
    private TextView tvBleInit,tvVinCar;
    private TextView tvHaslock;
    private Button tvBle;
    private Button tvOpenthedoor;
    private TextView tvState;
    private RecyclerView rvKeyList;
    private ArrayList<VKeyCertInfo> list=new ArrayList<>();
    private KeyListAdapter mAdapter;
    private Button tvDocheck;
    private String vin="1234567890";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getPermission();
        PingBoSdkUtils.getInstance().setC52XBleStateListener(this);
    }

    private void getPermission() {
        //权限请求
        List<String> permissionList = new ArrayList<>();
        permissionList.add(PermissionConstants.READ_PHONE_STATE);//获取手机状态
        permissionList.add(PermissionConstants.BLUETOOTH_ADMIN);//蓝牙
        permissionList.add(PermissionConstants.READ_EXTERNAL_STORAGE);//蓝牙
        permissionList.add(PermissionConstants.WRITE_EXTERNAL_STORAGE);//蓝牙
        permissionList.add(PermissionConstants.ACCESS_FINE_LOCATION);
        permissionList.add(PermissionConstants.BLUETOOTH);//蓝牙
        PermissionX.init(MainActivity.this).permissions(permissionList)
                .onExplainRequestReason((ExplainReasonCallbackWithBeforeParam) this)
                .setDialogTintColor(mContext.getResources().getColor(R.color.color_FFC910),
                        mContext.getResources().getColor(R.color.white)).onForwardToSettings(this)
                .setDialogTintColor(mContext.getResources().getColor(R.color.color_FFC910),
                        mContext.getResources().getColor(R.color.white)).request(this);
    }

    private void initView() {
        tvVinCar = (TextView) findViewById(R.id.tv_vin_car);
        tvBleInit = (TextView) findViewById(R.id.tv_ble_init);
        tvHaslock = (TextView) findViewById(R.id.tv_haslock);
        tvBle = (Button) findViewById(R.id.tv_ble);
        tvOpenthedoor = (Button) findViewById(R.id.tv_openthedoor);
        tvState = (TextView) findViewById(R.id.tv_state);
        rvKeyList=findViewById(R.id.rv_keyList);
        tvDocheck=findViewById(R.id.tv_docheck);
        tvOpenthedoor.setOnClickListener(this);
        tvDocheck.setOnClickListener(this);
        tvHaslock.setOnClickListener(this);
        tvBle.setOnClickListener(this);
        tvOpenthedoor.setOnClickListener(this);
        tvVinCar.setText("当前车辆vin号："+vin);
        for (int i=0;i<3;i++){
            list.add(new VKeyCertInfo());
        }
        rvKeyList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mAdapter = new KeyListAdapter(list);
        rvKeyList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new KeyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, VKeyCertInfo vKeyCertInfo) {
                //点击时进行双因子认证
              //  C52XManage.getInstance(getApplicationContext()).startUf2(Integer.parseInt(vKeyCertInfo.getReqId()));
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_openthedoor:
                tvState.setText("开启失败");
                C52XManage.getInstance(MainActivity.this).openTheDoor(new byte[]{0});
                break;
            case R.id.tv_haslock:
                startActivity(new Intent(MainActivity.this, LocalKeyListActivity.class));
                break;
            case R.id.tv_docheck:
                startActivity(new Intent(MainActivity.this, DoCheckListActivity.class));
                break;
            case R.id.tv_ble:
                C52XManage.getInstance(MainActivity.this).connectBle(getApplication());
                break;
        }
    }

    @Override
    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
        scope.showForwardToSettingsDialog(deniedList,
                mContext.getResources().getString(R.string.you_need_to_setting_open_permission_text),
                mContext.getResources().getString(R.string.to_setting_text),
                mContext.getResources().getString(R.string.cancel));
    }

    @Override
    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
        if (allGranted) {//所有权限同意后
            Log.e(TAG, "permission:所有权限都同意了" + grantedList.toString());
        } else {
            Log.e(TAG, "permission:有部分权限没同意" + deniedList.toString());

        }
    }

    @Override
    public void onBleSdkInitState(int state) {
        if(state== Constants.BLE_SDK_INIT_SUCCESS){
            C52XManage.getInstance(this).connectBle(getApplication());
        }else {
        }
    }

    @Override
    public void onKeySdkInitState(int state) {

    }

    @Override
    public void onBleInitState(int code, String msg) {
        if(code==Constants.BLE_INIT_SUCCESS){
        }else if(code== KPPException.NEED_PERMISSION_LOCATION){
            Toast.makeText(mContext,"请打开权限",Toast.LENGTH_SHORT).show();
            getPermission();
        }
    }


    @Override
    public void onBleConnectionState(int state) {

    }

    @Override
    public void onBleReceivedData(String s, byte[] bytes) {
        if(s.equals(Constants.START_U2F)){
            //可以进行双因子认证
            C52XManage.getInstance(this).startUf2();
        }
    }
}