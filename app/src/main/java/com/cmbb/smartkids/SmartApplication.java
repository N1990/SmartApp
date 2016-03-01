package com.cmbb.smartkids;

import android.app.Application;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.framework.utils.log.constant.ZoneOffset;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 上午10:01
 */
public class SmartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClientManager.init(this);
        Log.init(this)
                .writeToFile(false)
                .setLogDir(getString(R.string.app_name))
                .setZoneOffset(ZoneOffset.P0800);
    }
}
