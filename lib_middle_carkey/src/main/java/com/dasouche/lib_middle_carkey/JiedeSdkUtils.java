package com.dasouche.lib_middle_carkey;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseAckGetCert;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseCheckInfos;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseHeader;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseReqValidCert;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseReqVkey;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseSvrVkeyInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.impl.GdVkeySdkInst;
import com.gieseckedeverient.vkeyapp.vkeytools.interfaces.GdBleCallbackInterface;
import com.gieseckedeverient.vkeyapp.vkeytools.interfaces.RemoteCallback;
import com.gieseckedeverient.vkeyapp.vkeytools.types.AuthType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.LimitType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.LocalVkeyLifeCycle;
import com.gieseckedeverient.vkeyapp.vkeytools.types.OpMcuVkType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.SignerType;
import com.gieseckedeverient.vkeyapp.vkeytools.types.VKeyType;

import java.util.List;

/**
 * Created by Android Studio.
 * User: dasouche
 * Date: 2021/6/7
 * Time: 4:22 下午
 * Des:判断是否初始化过：1，没有初始化过，初始化并且申请钥匙，申请回调拿到唯一id，直接将钥匙下载到本地
 *                    2，初始化过，获取本地车钥匙
 */
public class JiedeSdkUtils {
    private static final String TAG = JiedeSdkUtils.class.getSimpleName();
    private volatile static JiedeSdkUtils instance;
    private Context context;
    private GdVkeySdkInst gdVkeySdkInst;
    private GdBleManipulateInterfaceCallBack gdBleManipulateInterface;

    public C52XCheckPublicKeyListener getC52XCheckPublicKeyListener() {
        return c52XCheckPublicKeyListener;
    }

    public void setC52XCheckPublicKeyListener(C52XCheckPublicKeyListener c52XCheckPublicKeyListener) {
        this.c52XCheckPublicKeyListener = c52XCheckPublicKeyListener;
    }

    private C52XCheckPublicKeyListener c52XCheckPublicKeyListener;
    private String userIdHashData;
    private String userToken;
    private String reqId;//申请唯一标示值，申请车钥匙后会返回

    private JiedeSdkUtils() {
    }

    public static JiedeSdkUtils getInstance() {
        if (instance == null) {
            synchronized (JiedeSdkUtils.class) {
                if (instance == null) {
                    instance = new JiedeSdkUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化捷德sdk
     *
     * @param baseUrl
     * @param context        上下文
     * @param userToken      用户token
     * @param userIdHashData 用户唯一id
     */
    public void init(String baseUrl, Context context, String userToken, String
            userIdHashData) {
        this.context = context;
        this.userIdHashData = userIdHashData;
        this.userToken = userToken;
        gdBleManipulateInterface = new GdBleManipulateInterfaceCallBack();
        //文档传6个参数，实际传7个
        gdVkeySdkInst = new GdVkeySdkInst(baseUrl, context, "userToken", "111111", "com.dasouche.jiededemo", gdBleManipulateInterface, new RemoteCallback<ResponseHeader>() {
            @Override
            public void onResponse(ResponseHeader responseHeader) {
                // callback.onResponse(responseHeader);
                if (responseHeader.getCode().equals("0")) {
                    if (c52XCheckPublicKeyListener != null) {
                        c52XCheckPublicKeyListener.initSuccess();
                    }
                } else {
                    if (c52XCheckPublicKeyListener != null) {
                        c52XCheckPublicKeyListener.initFail();
                    }
                }
            }
        });
    }

    /**
     * 根据生命周期获取本地钥匙
     *
     * @param localVkeyLifeCycle
     * @return
     */
    public List<VKeyCertInfo> getLocalKeys(LocalVkeyLifeCycle localVkeyLifeCycle) {
        return gdVkeySdkInst.getLocalKeyMngInst().getFilterKeys(localVkeyLifeCycle);
    }

    /**
     * @param vKeyInfo 申请车钥匙传入参数
     */
    public void applykey(VKeyInfo vKeyInfo) {
        //实例化成功
        gdVkeySdkInst.getRemoteKeyMngInst().reqVkey(vKeyInfo, new RemoteCallback<ResponseReqVkey>() {
            @Override
            public void onResponse(ResponseReqVkey responseReqVkey) {
                reqId = responseReqVkey.getReqId();//申请唯一标示值
                //获取到车钥匙后将它下载到本地
                if (!TextUtils.isEmpty(reqId)) {
                    Log.e(TAG, "申请车钥匙后获取到了申请唯一标示值"+reqId);
                    doAckGetCert(reqId);
                } else {
                    Log.e(TAG, "申请车钥匙获取的唯一标示为空，停止操作");
                }
            }
        });
    }

    /**
     * 钥匙对账，获取本地钥匙后进行对账，保证钥匙的正确性
     */
    public void doCheckKey() {
        gdVkeySdkInst.getRemoteKeyMngInst().doCheck(new RemoteCallback<ResponseCheckInfos>() {
            @Override
            public void onResponse(ResponseCheckInfos responseCheckInfos) {
                //需要车主审批的数字钥匙业务 id 列表
                Log.e(TAG, responseCheckInfos.getApproveIds().toString());
                //需要下载的数字钥匙业务 id 列表
                Log.e(TAG, responseCheckInfos.getDownloadIds().toString());
                //需要本地删除的数字钥匙业务 id 列表
                Log.e(TAG, responseCheckInfos.getRevokeIds().toString());
                //执行下载钥匙全部下载到本地到本地方法
                getNotDownloadVkeyInfo(responseCheckInfos.getDownloadIds());
                for(int i=0;i<responseCheckInfos.getDownloadIds().length;i++){
                    doAckGetCert(responseCheckInfos.getDownloadIds()[i]);
                }

            }
        });
    }

    /**
     * @param strings 获取未下载要是数组
     */
public void getNotDownloadVkeyInfo(String[] strings){
    gdVkeySdkInst.getRemoteKeyMngInst().getNotDownloadVkeyInfo(strings, new RemoteCallback<ResponseSvrVkeyInfo>() {
        @Override
        public void onResponse(ResponseSvrVkeyInfo responseSvrVkeyInfo) {
            //获取到未下载数组
            for(int i=0;i<responseSvrVkeyInfo.getvKeyInfos().length;i++){
                doAckGetCert(reqId);
            }
        }
    });
}

    /**
     * @param reqId 下载钥匙到本地
     */
    public void doAckGetCert(String reqId) {
        gdVkeySdkInst.getRemoteKeyMngInst().doAckGetCert(reqId, new RemoteCallback<ResponseAckGetCert>() {
            @Override
            public void onResponse(ResponseAckGetCert responseAckGetCert) {
                //下载钥匙到本地后获取vin
                Log.e(TAG, "下载钥匙到本地后获取vin："+responseAckGetCert.getAttachData().getVin());
                //下载钥匙到本地后获取证书
                Log.e(TAG, "下载钥匙到本地后获取vin："+responseAckGetCert.getKcom());
            }
        });
    }

    /**
     * 双因子认证
     * @param localId 本地id，根据生命周期获取的本地车钥匙可以获得
     */
    public void startUf2(int localId){
        gdVkeySdkInst.getLocalKeyMngInst().startU2f(localId, new MyRemoteCallback() {
            @Override
            public void onResponse(ResponseHeader responseHeader) {

            }
        });
    }


    /**
     * 申请操作车辆
     * @param localKeyId
     * @param opMcuVkType
     * @param hasnet
     */
    public void applyOpenCar(int localKeyId,OpMcuVkType opMcuVkType,boolean hasnet){
        gdVkeySdkInst.getRemoteCarMngInst().reqOperate(localKeyId, opMcuVkType, hasnet, new RemoteCallback<ResponseReqValidCert>() {
            @Override
            public void onResponse(ResponseReqValidCert responseReqValidCert) {

            }
        });
    }

    /**
     * 检查安全通道状态
     */
    public void checkScStat(int localKeyId,boolean hasnet){
        gdVkeySdkInst.getRemoteCarMngInst().checkScStat(new RemoteCallback<ResponseHeader>() {
            @Override
            public void onResponse(ResponseHeader responseHeader) {
                if(responseHeader.getCode().equals("0")){
                    //安全通道已建立,申请操作车辆
                    applyOpenCar(localKeyId,OpMcuVkType.DOOR_OPEN,hasnet);
                }else{
                    //建立安全通道
                    gdVkeySdkInst.getRemoteCarMngInst().reqInit(localKeyId, new RemoteCallback<ResponseHeader>() {
                        @Override
                        public void onResponse(ResponseHeader responseHeader) {
                            if(responseHeader.isValid()){
                                //安全通道已建立,申请操作车辆
                                applyOpenCar(localKeyId,OpMcuVkType.DOOR_OPEN,hasnet);
                            }else{
                                //安全通道建立失败
                            }
                        }
                    });
                }
            }
        });
    }
    /**
     *传输数据
     * @param bytes 控车数据
     */
    public void sendData(byte[] bytes){
        gdVkeySdkInst.getGdBleManipulateInterface().sendData(false,bytes);
        gdVkeySdkInst.getGdBleManipulateInterface().registerGdBleCallback(new GdBleCallbackInterface() {
            @Override
            public void onConnectionStateChange(int i, int i1) {

            }

            @Override
            public void onDataReceived(boolean b, byte[] bytes) {
                //得到加密数据，传给瓶钵
                PingBoSdkUtils.getInstance().sendData(bytes);
            }
        });
    }
}
