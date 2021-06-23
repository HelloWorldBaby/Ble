package com.dasouche.lib_middle_carkey;

import android.util.Log;

import com.gieseckedeverient.vkeyapp.vkeytools.interfaces.GdBleCallbackInterface;
import com.gieseckedeverient.vkeyapp.vkeytools.interfaces.GdBleManipulateInterface;

/*
* 蓝牙控制接口实现类
* */
public class GdBleManipulateInterfaceCallBack implements GdBleManipulateInterface {
    @Override
    public int sendData(boolean b, byte[] bytes) {
        return 0;
    }

    @Override
    public void msgFromMcu(byte[] bytes) {

    }

    @Override
    public void msgFromSdk(String s) {
        Log.e("回调", s);
        //返回是否进行双因子认证


    }

    @Override
    public int registerGdBleCallback(GdBleCallbackInterface gdBleCallbackInterface) {
        Log.e("回调", "registerGdBleCallback");
        return 0;
    }
}
