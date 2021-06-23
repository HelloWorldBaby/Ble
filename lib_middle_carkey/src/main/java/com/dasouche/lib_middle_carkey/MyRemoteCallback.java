package com.dasouche.lib_middle_carkey;

import com.gieseckedeverient.vkeyapp.vkeytools.beans.ResponseHeader;
import com.gieseckedeverient.vkeyapp.vkeytools.interfaces.RemoteCallback;

public abstract class MyRemoteCallback implements RemoteCallback<ResponseHeader> {
    public abstract void onResponse(ResponseHeader responseHeader);
}
