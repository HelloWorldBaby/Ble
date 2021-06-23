package com.dasouche.lib_middle_carkey;

/**
 * Created by Android Studio.
 * User: caicheng
 * Date: 2021/6/7
 * Time: 11:54 上午
 * Des：数字车钥匙工具类回调
 */

public interface C52XCheckPublicKeyListener {

    /**
     * 数字车钥匙sdk初始化成功
     */
    void initSuccess();
    /**
     * 数字车钥匙sdk初始化失败
     */
    void initFail();
    /**
     * 公钥匹配成功，可以直接去扫车
     */
    void onMatchSuccess();

    /**
     * 需要初始化操作
     */
    void onInitialize();

    /**
     * 失败的处理方式
     */
    void onFailure(String errorMsg);
}
