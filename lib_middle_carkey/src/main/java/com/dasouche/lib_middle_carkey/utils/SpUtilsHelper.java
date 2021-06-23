package com.dasouche.lib_middle_carkey.utils;

import android.content.Context;

import com.dasouche.lib_middle_carkey.constants.SpConstants;

/**
 * Created by Android Studio.
 * User: dasouche
 * Date: 2021/6/7
 * Time: 4:22 下午
 * Des:常用的sp的帮助类，快速获取某个值，可配置
 */

public class SpUtilsHelper {
    private static volatile SpUtilsHelper instance = null;
    private static Context context;
    private SpUtilsHelper() {

    }

    public static SpUtilsHelper getInstance(Context cont) {
        context=cont;
        if (instance == null) {
            synchronized (SpUtilsHelper.class) {
                if (instance == null) {
                    instance = new SpUtilsHelper();
                }
            }
        }
        return instance;
    }

    public Boolean getIsInitJieDe() {
        SPUtils spUtils = new SPUtils(context, SpConstants.SP_USER_INFO);
        return spUtils.getBoolean(SpConstants.IS_INIT_JIEDE, false);
    }

    public void setIsInitJieDe(boolean isInitJieDe) {
        SPUtils spUtils = new SPUtils(context, SpConstants.SP_USER_INFO);
        spUtils.putBoolean(SpConstants.IS_INIT_JIEDE, isInitJieDe);
    }

}
