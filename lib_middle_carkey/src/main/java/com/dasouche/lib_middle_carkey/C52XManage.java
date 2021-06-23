package com.dasouche.lib_middle_carkey;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyCertInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.beans.VKeyInfo;
import com.gieseckedeverient.vkeyapp.vkeytools.types.LocalVkeyLifeCycle;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2021/6/14 21:52
 *
 * @author Caicheng
 * 包名： com.dasouche.lib_middle_carkey
 * 类说明：车控工具类，集合了蓝牙和车钥匙
 */
public class C52XManage {
    private static final String TAG = C52XManage.class.getSimpleName();
    private static volatile C52XManage instance;
    private Context mContext;
    private C52StateListener c52StateListener;
    private List<VKeyCertInfo> vKeyCertInfoList_Nor=new ArrayList<>();//本地正常钥匙列表
    private List<VKeyCertInfo> vKeyCertInfoList_NotActive=new ArrayList<>();//本地未激活钥匙列表
    private List<VKeyCertInfo> vKeyCertInfoList_NotDownload=new ArrayList<>();//本地未下载钥匙列表
    private static int localId=11223344;
    public static C52XManage getInstance(Context context) {
        if (instance == null) {
            synchronized (C52XManage.class) {
                if (instance == null) {
                    instance = new C52XManage(context);
                }
            }
        }
        return instance;
    }
    private C52XManage(Context context) {
        mContext = context;
    }

    /**
     * 初始化两个sdk
     */
    public void initManager(){
       // 瓶钵sdk初始化
        PingBoSdkUtils.getInstance().initSdk(mContext);
      //  初始化车钥匙下载到本地
        JiedeSdkUtils.getInstance().init("",mContext,"","");
    }


    /**
     * 根据生命周期获取本地车钥匙
     */
    public List<VKeyCertInfo> getLocalKeyList(LocalVkeyLifeCycle localVkeyLifeCycle){
        return  JiedeSdkUtils.getInstance().getLocalKeys(localVkeyLifeCycle);
    }
    public List<VKeyCertInfo> getLocalKeyList_Normal(){
        return  JiedeSdkUtils.getInstance().getLocalKeys(LocalVkeyLifeCycle.AVAIL);
    }
    public List<VKeyCertInfo> getLocalKeyList_Notactivate(){
        return  JiedeSdkUtils.getInstance().getLocalKeys(LocalVkeyLifeCycle.U2F);
    }
    public List<VKeyCertInfo> getLocalKeyList_NotDownLoad(){
        return  JiedeSdkUtils.getInstance().getLocalKeys(LocalVkeyLifeCycle.NOTDOWNLOAD);
    }

    /**
     * 获取可使用车钥匙
     */
    public void getCanUseKey(String vin){
        if(C52XManage.getInstance(mContext).getLocalKeyList_Normal()!=null&&C52XManage.getInstance(mContext).getLocalKeyList_Normal().size()>0){
            vKeyCertInfoList_Nor.clear();
            vKeyCertInfoList_Nor.addAll(C52XManage.getInstance(mContext).getLocalKeyList_Normal());
            for (int i=0;i<vKeyCertInfoList_Nor.size();i++){
                //循环本地正常钥匙，如果有
                if(vKeyCertInfoList_Nor.get(i).getVin().equals(vin)){

                }
            }
        }else if(C52XManage.getInstance(mContext).getLocalKeyList_Notactivate()!=null&&C52XManage.getInstance(mContext).getLocalKeyList_Notactivate().size()>0){
            //获取已下载未激活钥匙，如果列表不为空，弹窗提示用户
            vKeyCertInfoList_NotActive.clear();
            for (int i=0;i<vKeyCertInfoList_NotActive.size();i++) {
                if (vKeyCertInfoList_NotActive.get(i).getVin().equals(vin)) {

                }
            }
        }else if(C52XManage.getInstance(mContext).getLocalKeyList_NotDownLoad()!=null&&C52XManage.getInstance(mContext).getLocalKeyList_NotDownLoad().size()>0){
            vKeyCertInfoList_NotDownload.clear();
            vKeyCertInfoList_NotDownload.addAll(C52XManage.getInstance(mContext).getLocalKeyList_Normal());
            for (int i=0;i<vKeyCertInfoList_NotDownload.size();i++){
                //循环本地有未下载钥匙，进行下载操作
                if(vKeyCertInfoList_NotDownload.get(i).getVin().equals(vin)){
                    JiedeSdkUtils.getInstance().doAckGetCert(vKeyCertInfoList_NotDownload.get(i).getReqId());
                }
            }
        }else{
            //三种钥匙都没有，进行申请钥匙操作,参数暂时写死
            JiedeSdkUtils.getInstance().applykey(new VKeyInfo());
        }
    }

    /**
     * @param vKeyInfo 申请车钥匙参数
     */
    public void applyKey(VKeyInfo vKeyInfo){
        JiedeSdkUtils.getInstance().applykey(vKeyInfo);
    }

    /**
     * 开始双因子认证
     */
    public void startUf2(){
        JiedeSdkUtils.getInstance().startUf2(localId);
    }

    /**
     * 连接蓝牙
     */
    public void connectBle(Application application){
        PingBoSdkUtils.getInstance().initBle(application);
    }

    public void connectCar(String vin){
        PingBoSdkUtils.getInstance().connectCar(vin);
    }

    public void openTheDoor(byte[] Byte){
        PingBoSdkUtils.getInstance().sendData(Byte);
    }
}
