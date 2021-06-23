package com.car.liteavlibrary;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePlayer;
import com.tencent.live2.V2TXLivePlayerObserver;

/**
 * Created by Android Studio.
 * User: dasouche
 * Date: 2021/6/9
 * Time: 1:49 下午
 */

public class MyPlayerObserver extends V2TXLivePlayerObserver {
    private static final String TAG = MyPlayerObserver.class.getSimpleName();
    @Override
    public void onWarning(V2TXLivePlayer v2TXLivePlayer, int i, String s, Bundle bundle) {
        super.onWarning(v2TXLivePlayer, i, s, bundle);
        Log.e(TAG,"onWarning"+v2TXLivePlayer.isPlaying()+i+s);
    }

    @Override
    public void onVideoPlayStatusUpdate(V2TXLivePlayer v2TXLivePlayer, V2TXLiveDef.V2TXLivePlayStatus v2TXLivePlayStatus, V2TXLiveDef.V2TXLiveStatusChangeReason v2TXLiveStatusChangeReason, Bundle bundle) {
        super.onVideoPlayStatusUpdate(v2TXLivePlayer, v2TXLivePlayStatus, v2TXLiveStatusChangeReason, bundle);
        Log.e(TAG,"onVideoPlayStatusUpdate"+v2TXLivePlayStatus.name()+v2TXLiveStatusChangeReason.name());
    }

    @Override
    public void onPlayoutVolumeUpdate(V2TXLivePlayer v2TXLivePlayer, int i) {
        super.onPlayoutVolumeUpdate(v2TXLivePlayer, i);
        Log.e(TAG,"onPlayoutVolumeUpdate"+"音量"+i);
    }

    @Override
    public void onRenderVideoFrame(V2TXLivePlayer v2TXLivePlayer, V2TXLiveDef.V2TXLiveVideoFrame v2TXLiveVideoFrame) {
        super.onRenderVideoFrame(v2TXLivePlayer, v2TXLiveVideoFrame);
        Log.e(TAG,"onRenderVideoFrame自定义视频渲染回调");
    }

    @Override
    public void onSnapshotComplete(V2TXLivePlayer v2TXLivePlayer, Bitmap bitmap) {
        super.onSnapshotComplete(v2TXLivePlayer, bitmap);
        Log.e(TAG,"onSnapshotComplete"+"截图");

    }

    @Override
    public void onError(V2TXLivePlayer v2TXLivePlayer, int i, String s, Bundle bundle) {
        super.onError(v2TXLivePlayer, i, s, bundle);
        Log.e(TAG,"onError"+v2TXLivePlayer.isPlaying()+i+s);
    }
}
