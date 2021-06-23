package com.dasouche.lib_middle_carkey;

/**
 * 创建日期：2021/6/13 23:27
 *
 * @author Caicheng
 * 包名： com.dasouche.lib_middle_carkey
 * 类说明：蓝牙连接工具类回调
 */
public interface C52StateListener {
    /**
     * @param state 蓝牙sdk初始化结果
     */
    void onBleSdkInitState(int state);

    void onKeySdkInitState(int state);
//    /**
//     * 蓝牙blesdk初始化失败
//     */
//    void onSdkInitialize(int code,String msg);
//    /**
//     * 蓝牙blesdk初始化成功
//     */
    void onBleInitState(int code,String msg);

    /**
     * @param state 蓝牙连接状态
     */
    void onBleConnectionState(int state);

    void onBleReceivedData(String s, byte[] bytes);
}
