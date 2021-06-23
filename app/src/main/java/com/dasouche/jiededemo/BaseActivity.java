package com.dasouche.jiededemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dasouche.lib_middle_carkey.C52XManage;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;

import java.util.List;

/**
 * 创建日期：2021/6/13 23:41
 *
 * @author Caicheng
 * 包名： com.dasouche.jiededemo
 * 类说明：baseActivity 权限回调
 */
public class BaseActivity extends AppCompatActivity implements ExplainReasonCallback, ExplainReasonCallbackWithBeforeParam, RequestCallback, ForwardToSettingsCallback {
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;

    }
    /**
     * 在权限需要说明请求原因时调用
     * 通常权限被拒绝以后都会调用这个方法
     * ExplainReasonCallback
     *
     * @param scope
     * @param deniedList
     */
    @Override
    public void onExplainReason(ExplainScope scope, List<String> deniedList) {

    }
    /**
     * 在权限需要说明请求原因时调用
     * 通常权限被拒绝以后都会调用这个方法
     * ExplainReasonCallbackWithBeforeParam
     *
     * @param scope
     * @param deniedList    拒绝的权限都记录
     * @param beforeRequest false 表示所有需要申请的权限，true 表示必须要申请的权限
     */
    @Override
    public void onExplainReason(ExplainScope scope, List<String> deniedList, boolean beforeRequest) {

    }
    /**
     * 用户去设置手动调用权限
     * Called when you should tell user to allow these permissions in settings.
     *
     * @param scope
     * @param deniedList
     */
    @Override
    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {

    }
    /**
     * 权限的回调
     *
     * @param allGranted  是否同意了所有权限
     * @param grantedList 用户同意的所有权限
     * @param deniedList  用户拒绝的所有权限
     */
    @Override
    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {

    }
}
