package com.cmbb.smartkids.framework.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.cmbb.smartkids.framework.R;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.utils.log.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class TDevice {

    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static int _pageSize = -1;
    public static float displayDensity = 0.0F;

    static {
        boolean flag = true;
        boolean flag1;
        boolean flag2;
        if (Build.VERSION.SDK_INT >= 14)
            flag1 = flag;
        else
            flag1 = false;
        GTE_ICS = flag1;
        if (Build.VERSION.SDK_INT >= 11)
            flag2 = flag;
        else
            flag2 = false;
        GTE_HC = flag2;
        if (Build.VERSION.SDK_INT >= 11)
            flag = false;
        PRE_HC = flag;
    }

    public TDevice() {
    }

    /**
     * 将px类型的尺寸转换成dp类型的尺寸
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dp类型的尺寸转换成px类型的尺寸
     *
     * @param size
     * @param context
     * @return
     */
    public static int dip2px(int size, Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        return (int) ((float) size * metrics.density + 0.5);
    }


    public static int px2sp(float pxValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(int spValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    public static int getPageSize() {
        if (_pageSize == -1)
            if (TDevice.isTablet())
                _pageSize = 50;
            else if (TDevice.hasBigScreen())
                _pageSize = 20;
            else
                _pageSize = 10;
        return _pageSize;
    }

    /**
     * 获取屏幕宽度(像素)
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度(像素)
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

    /**
     * @param activity 保存屏幕宽高
     */
    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SPCache.putInt(Constants.SharePreference.SCREEN_WIDTH, displayMetrics.widthPixels);
        SPCache.putInt(Constants.SharePreference.SCREEN_HEIGHT, displayMetrics.heightPixels);
    }

    /**
     * 测量控件的尺寸
     *
     * @param view
     */
    public static void calcViewMeasure(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
    }


    public static boolean hasBigScreen() {
        boolean flag = true;
        if (_hasBigScreen == null) {
            boolean flag1;
            Boolean boolean1;
            if ((0xf & BaseApplication.getContext().getResources().getConfiguration().screenLayout) >= 3)
                flag1 = flag;
            else
                flag1 = false;
            boolean1 = Boolean.valueOf(flag1);
            _hasBigScreen = boolean1;
            if (!boolean1.booleanValue()) {
                if (getDensity() <= 1.5F)
                    flag = false;
                _hasBigScreen = Boolean.valueOf(flag);
            }
        }
        return _hasBigScreen.booleanValue();
    }

    public static final boolean hasCamera() {
        if (_hasCamera == null) {
            PackageManager pckMgr = BaseApplication.getContext().getPackageManager();
            boolean flag = pckMgr.hasSystemFeature("android.hardware.camera.front");
            boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
            boolean flag2;
            if (flag || flag1)
                flag2 = true;
            else
                flag2 = false;
            _hasCamera = Boolean.valueOf(flag2);
        }
        return _hasCamera.booleanValue();
    }

    public static boolean hasHardwareMenuKey(Context context) {
        boolean flag = false;
        if (PRE_HC)
            flag = true;
        else if (GTE_ICS) {
            flag = ViewConfiguration.get(context).hasPermanentMenuKey();
        } else
            flag = false;
        return flag;
    }

    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) BaseApplication.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        ((InputMethodManager) BaseApplication.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }

    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            if ((0xf & BaseApplication.getContext().getResources().getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            _isTablet = Boolean.valueOf(flag);
        }
        return _isTablet.booleanValue();
    }

    public static boolean hasSdCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = BaseApplication.getContext().getPackageManager().getPackageInfo(BaseApplication.getContext().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static String getVersionName() {
        String name = "";
        try {
            name = BaseApplication.getContext().getPackageManager().getPackageInfo(BaseApplication.getContext().getPackageName(), 0).versionName;
        } catch (NameNotFoundException ex) {
            name = "";
        }
        return name;
    }


    public static void installAPK(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void openDial(Context context, String number) {
        Uri uri = Uri.parse("tel:" + number);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(it);
    }

    public static void openSMS(Context context, String smsBody, String tel) {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsBody);
        context.startActivity(it);
    }

    //获取总的接收字节（mobile and wifi）
    public static long getTotalRxBytes() {
        return TrafficStats.getUidRxBytes(android.os.Process.myPid()) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getUidRxBytes(android.os.Process.myPid()) / 1024);
    }

    //获取总的发送字节（mobile and wifi）
    public static long getTotalTxBytes() {
        return TrafficStats.getUidTxBytes(android.os.Process.myPid()) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getUidTxBytes(android.os.Process.myPid()) / 1024);
    }

    /**
     * 获取设备的唯一标识码
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        try {
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            return device_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

    /* 一个获得当前进程的名字的方法 */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 手机号码验证
     *
     * @param phone
     * @return
     */
    public static boolean isMobileNo(String phone) {
        String match = "^((13|15|18|17|14)\\d{9})|147\\d{8}$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * @param time
     * @param format
     * @return
     */
    public static String DateToString(long time, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(time);
        return result;
    }

    /**
     * 身份证正则
     *
     * @param id
     * @return
     */
    public static boolean isID(String id) {
        String match = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(id.trim());
        return matcher.matches();
    }

    /***
     * 读取本地文件中JSON字符串
     *
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * Raw 获取资源文件
     *
     * @param context
     * @param res
     * @return
     */
    public static String getContentFromRaw(Context context, @RawRes int res) {
        InputStream in = null;
        String temp = "";
        try {
            in = context.getResources().openRawResource(R.raw.popman_rule);
            byte[] buff = new byte[1024];// 缓存
            int rd = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((rd = in.read(buff)) != -1) {
                baos.write(buff, 0, rd);
                temp = new String(baos.toByteArray(), "UTF-8");
            }
            baos.close();
        } catch (Exception e) {
            Log.e(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return temp;
        }
    }



    /**
     * 日期精确
     *
     * @param data
     * @param format
     * @return
     * @throws ParseException
     */
    public static String DataToString(String data, String format) throws ParseException {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        Date date = sdf.parse(data);
        result = sdf2.format(date);
        return result;
    }

}
