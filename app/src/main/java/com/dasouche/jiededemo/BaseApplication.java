package com.dasouche.jiededemo;

import android.app.Application;

import com.dasouche.lib_middle_carkey.C52XManage;


/**
 * Created by Android Studio.
 * User: dasouche
 * Date: 2021/6/9
 * Time: 5:01 下午
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
    }

    private void initSDK() {
        C52XManage.getInstance(this).initManager();
        //直播初始化
//        String LICENCE_KEY="b10e30239b98c7dab6da7a4820ce6b36";
//        String LICENCE_URL="http://license.vod2.myqcloud.com/license/v1/4d30a25602f00b22de52972243ee167b/TXLiveSDK.licence";
//        TXLiveBase.getInstance().setLicence(this, LICENCE_URL, LICENCE_KEY);
    }
}
