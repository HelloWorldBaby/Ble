package com.dasouche.lib_middle_carkey;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.dasouche.lib_middle_carkey.constants.Constants;
import com.trustkernel.kppsdkv2.digitalkey.KPPManager;
import com.trustkernel.kppsdkv2.digitalkey.callback.BleEventInterface;
import com.trustkernel.kppsdkv2.digitalkey.callback.ConnectCallback;
import com.trustkernel.kppsdkv2.digitalkey.callback.DisconnectCallback;
import com.trustkernel.kppsdkv2.digitalkey.callback.KPPBleInitCallback;
import com.trustkernel.kppsdkv2.digitalkey.callback.KPPSdkInitCallback;
import com.trustkernel.kppsdkv2.digitalkey.callback.ReceiveDataInterface;
import com.trustkernel.kppsdkv2.digitalkey.callback.RootCACallback;
import com.trustkernel.kppsdkv2.digitalkey.callback.WriteDataCallback;
import com.trustkernel.kppsdkv2.digitalkey.exception.KPPException;
import com.trustkernel.tam.agent.app.message.Content;

/**
 * Created by Android Studio.
 * User: dasouche
 * Date: 2021/6/8
 * Time: 10:02 上午 瓶钵蓝牙sdk工具类
 */

public class PingBoSdkUtils {
    private static final String TAG = PingBoSdkUtils.class.getSimpleName();
    private volatile static PingBoSdkUtils instance;
    private Context context;
    public C52StateListener c52XBleStateListener;

    private PingBoSdkUtils() {
    }
    public void setC52XBleStateListener(C52StateListener c52XBleStateListener) {
        this.c52XBleStateListener = c52XBleStateListener;
    }

    public static PingBoSdkUtils getInstance() {
        if (instance == null) {
            synchronized (JiedeSdkUtils.class) {
                if (instance == null) {
                    instance = new PingBoSdkUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化瓶钵蓝牙sdk，初始化后才可以调用初始化ble方法
     * @param context 上下文
     */
    public void initSdk(Context context){
        this.context=context;
        KPPManager.getInstance().initSDK(context, new KPPSdkInitCallback() {
            @Override
            public void onKppSdkInitFailure(KPPException e) {
                Log.e(TAG,"蓝牙sdk初始化失败"+e.code+e.description);
                if(c52XBleStateListener!=null){
                    c52XBleStateListener.onBleSdkInitState(Constants.BLE_SDK_INIT_FAIL);
                }
            }

            @Override
            public void onKppSdkInitSuccess() {
                Log.e(TAG,"蓝牙sdk初始化成功");
                if(c52XBleStateListener!=null) {
                    c52XBleStateListener.onBleSdkInitState(Constants.BLE_SDK_INIT_SUCCESS);
                }
            }

            @Override
            public void onGetRootCA(RootCACallback rootCACallback) {
                //暂时不知道有什么用
            }
        });
        setKPPManagerListener();
    }
    public void initBle(Application application){
        KPPManager.getInstance().initBLE(application, new KPPBleInitCallback() {
            @Override
            public void BleInitSuccess() {
                Log.e(TAG,"蓝牙ble初始化成功");
                if(c52XBleStateListener!=null) {
                    c52XBleStateListener.onBleInitState(Constants.BLE_SDK_INIT_SUCCESS,"蓝牙ble初始化成功");
                }
              //  KPPManager.getInstance().startBle();
            }

            @Override
            public void BleInitFailure(KPPException e) {
                if(c52XBleStateListener!=null) {
                    c52XBleStateListener.onBleInitState(e.code,e.description);
                }
            }
        });

    }

    /**
     * 发送消息给车辆
     */
    public void sendData(byte bytes[]){
        KPPManager.getInstance().writeData(bytes, new WriteDataCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(KPPException e) {

            }
        });
    }
    /**
     * 停止连接车辆
     * @param vin 车辆vin
     */
    public void stopConnectCar(String vin){
        KPPManager.getInstance().disconnectCar(vin, new DisconnectCallback() {
            @Override
            public void success() {

            }

            @Override
            public void Failure(KPPException e) {

            }
        });
    }
    /**
     * 连接车辆方法
     * @param vin 车辆vin
     */
    public void connectCar(String vin){
        KPPManager.getInstance().connectCar(vin, new ConnectCallback() {
            @Override
            public void connecting() {

            }

            @Override
            public void success() {

            }

            @Override
            public void Failure(KPPException e) {

            }
        });
    }

    /**
     * 回调监听
     * BleEventInterface  蓝牙状态
     * ReceiveDataInterface  接收到的控车信息
     */
    public void setKPPManagerListener(){
        KPPManager.getInstance().setBleEventImpl(new BleEventInterface() {
            @Override
            public void onBleEvent(String s, State state, KPPException e) {
                switch (state){
                    case BLE_ON:
                        Log.e(TAG,"蓝牙打开");
                        break;
                    case BLE_OFF:
                        Log.e(TAG,"蓝牙关闭");
                        break;
                    case CONNECTING:
                        Log.e(TAG,"蓝牙正在链接");
                        break;
                    case CONNECTED:
                        Log.e(TAG,"蓝牙已经连接");
                        break;
                    case CONNECT_FAIL:
                        Log.e(TAG,"蓝牙连接失败");
                        break;
                    case NOT_FOUND:
                        Log.e(TAG,"未搜索到蓝牙");
                        break;
                }
            }
        });
        KPPManager.getInstance().setReceiveDataImpl(new ReceiveDataInterface() {
            @Override
            public void onReceiveData(String s, byte[] bytes) {
                c52XBleStateListener.onBleReceivedData(s,bytes);
                Log.e(TAG,"蓝牙接收到的信息"+s);

            }
        });
    }
}
