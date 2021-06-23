package com.car.liteavlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;

import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePlayer;
import com.tencent.live2.impl.V2TXLivePlayerImpl;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LiteAvActivity extends AppCompatActivity implements ITXLivePlayListener {
    private static final String TAG = LiteAvActivity.class.getSimpleName();
    private TXCloudVideoView txCVodVideoView;
    private V2TXLivePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_av);
        txCVodVideoView = findViewById(R.id.txCVodVideoView);
        // 创建⼀个 V2TXLivePlayer 对象；
        player = new V2TXLivePlayerImpl(LiteAvActivity.this);
        player.setObserver(new MyPlayerObserver());
        player.setRenderView(txCVodVideoView);
        // 传⼊低延时协议播放地址，即可开始播放；
        player.startPlay("http://3891.liveplay.myqcloud.com/live/3891_user_c63b4864_e159.flv");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stopPlay();
        txCVodVideoView.removeVideoView();
    }

    @Override
    public void onPlayEvent(int i, Bundle bundle) {
        Log.e(TAG,"onPlayEvent:"+i);
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }
}