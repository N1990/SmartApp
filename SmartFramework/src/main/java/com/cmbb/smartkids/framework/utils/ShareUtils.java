package com.cmbb.smartkids.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEvent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.framework.R;
import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.umeng.scrshot.adapter.UMAppAdapter;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sensor.UMSensor;
import com.umeng.socialize.sensor.beans.ShakeMsgType;
import com.umeng.socialize.sensor.controller.UMShakeService;
import com.umeng.socialize.sensor.controller.impl.UMShakeServiceFactory;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javon on 2015/8/3.
 * 使用方法：
 * mController = ShareUtils.instanceOf(this);
 * ShareUtils.configPlatforms();
 * ShareUtils.setShareContent();
 * 摇一摇分享
 * ShareUtils.registerShakeToShare();
 * 授权回调
 * onactivityreslut
 * <p>
 * 按钮点击事件调share面板
 * ShareUtils.showShareView();
 */
public class ShareUtils {
    private static Activity activity;
    public static final UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.Share.DESCRIPTOR);
    private static final UMShakeService mShakeController = UMShakeServiceFactory.getShakeService(Constants.Share.DESCRIPTOR);

    /**
     * @param activity1
     */
    public static UMSocialService instanceOf(Activity activity1) {
        if (activity1 != null)
            activity = activity1;


        return mController;
    }


    //授权
    public static void configPlatforms() {
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        addQQQZonePlatform();
        addWXPlatform();
    }

    /**
     * QQ QQ空间授权
     *
     * @return
     */
    public static void addQQQZonePlatform() {
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,
                Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
        qqSsoHandler.addToSocialSDK();
        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
        qZoneSsoHandler.addToSocialSDK();
    }

    /**
     * 微信 朋友圈授权
     *
     * @return
     */
    public static void addWXPlatform() {
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(activity, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxHandler.addToSocialSDK();
        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(activity, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    public static void setShareContent(String titile, String content, String url, String imgUrl) {
        Log.e("ShareUtils", "title :" + titile + ", content :" + content + ", url :" + url + ", imgUrl: " + imgUrl);
        // 配置SSO
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setTitle(titile);
        weixinContent.setShareContent(content);
        weixinContent.setTargetUrl(url);
        weixinContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setTitle(titile);
        circleMedia.setShareContent(content);
        circleMedia.setTargetUrl(url);
        circleMedia.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(circleMedia);

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setTitle(titile);
        qzone.setShareContent(content);
        qzone.setTargetUrl(url);
        qzone.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qzone);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setTitle(titile);
        qqShareContent.setShareContent(content);
        qqShareContent.setTargetUrl(url);
        qqShareContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qqShareContent);

        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setTitle(titile);
        sinaContent.setShareContent(content);
        sinaContent.setTargetUrl(url);
        sinaContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(sinaContent);

    }

    public static void setShareContent(String titile, String content, String url, int imgUrl) {

        // 配置SSO
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setTitle(titile);
        weixinContent.setShareContent(content);
        weixinContent.setTargetUrl(url);
        weixinContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setTitle(titile);
        circleMedia.setShareContent(content);
        circleMedia.setTargetUrl(url);
        circleMedia.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(circleMedia);

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setTitle(titile);
        qzone.setShareContent(content);
        qzone.setTargetUrl(url);
        qzone.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qzone);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setTitle(titile);
        qqShareContent.setShareContent(content);
        qqShareContent.setTargetUrl(url);
        qqShareContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(qqShareContent);

        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setTitle(titile);
        sinaContent.setShareContent(content);
        sinaContent.setTargetUrl(url);
        sinaContent.setShareImage(new UMImage(activity, imgUrl));
        mController.setShareMedia(sinaContent);

    }


    public static void showShareView() {
        final BottomSheetDialog dialog = new BottomSheetDialog(activity, R.style.BottomSheetDialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.share_bottom_sheet_recycler, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bs_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        shareModels.clear();
        shareModels.add(new ShareModel(R.mipmap.btn_wechat_bg, "微信"));
        shareModels.add(new ShareModel(R.mipmap.btn_friendcircle_bg, "朋友圈"));
        shareModels.add(new ShareModel(R.mipmap.btn_qq_bg, "QQ"));
        shareModels.add(new ShareModel(R.mipmap.btn_qzone_bg, "Qzone"));
        shareModels.add(new ShareModel(R.mipmap.btn_sina_bg, "微博"));
        BottomSheetRecyclerViewAdapter adapter = new BottomSheetRecyclerViewAdapter(activity);
        adapter.setItemClickListener(new BottomSheetRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        actionShare(SHARE_MEDIA.WEIXIN);
                        break;
                    case 1:
                        actionShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                        break;
                    case 2:
                        actionShare(SHARE_MEDIA.QQ);
                        break;
                    case 3:
                        actionShare(SHARE_MEDIA.QZONE);
                        break;
                    case 4:
                        actionShare(SHARE_MEDIA.SINA);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
    }

    private static void actionShare(SHARE_MEDIA media) {
        mController.postShare(activity, media, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, SocializeEntity socializeEntity) {
                if (status == StatusCode.ST_CODE_SUCCESSED) {
                    Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void registerShakeToShare() {
        /**
         * 摇一摇截图,直接分享 参数1: 当前所属的Activity 参数2: 截图适配器 参数3: 要用户可选的平台,最多支持五个平台 参数4:
         * 传感器监听器，包括摇一摇完成后的回调函数onActionComplete, 可在此执行类似于暂停游戏、视频等操作;
         * 还有分享完成、取消的回调函数onOauthComplete、onShareCancel。
         */
        UMAppAdapter appAdapter = new UMAppAdapter(activity);
        // 配置平台
        List<SHARE_MEDIA> platforms = new ArrayList<SHARE_MEDIA>();
        platforms.add(SHARE_MEDIA.SINA);
        platforms.add(SHARE_MEDIA.QQ);
        platforms.add(SHARE_MEDIA.QZONE);
        platforms.add(SHARE_MEDIA.WEIXIN);
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE);
        // 通过摇一摇控制器来设置文本分享内容
        mShakeController.setShareContent("美好瞬间，摇摇分享——来自友盟社会化组件");
        mShakeController.setShakeMsgType(ShakeMsgType.PLATFORM_SCRSHOT);
        mShakeController.registerShakeListender(activity, appAdapter,
                2000, false, platforms, mSensorListener);
        mShakeController.enableShakeSound(true);
    }

    public static void unregisterShakeListener(Activity activity) {
        mShakeController.unregisterShakeListener(activity);
    }


    /**
     * 传感器监听器
     */
    private static UMSensor.OnSensorListener mSensorListener = new UMSensor.OnSensorListener() {

        @Override
        public void onStart() {

        }

        /**
         * 分享完成后回调 (non-Javadoc)
         *
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int eCode,
                               SocializeEntity entity) {
            Toast.makeText(activity, "分享完成 000000", Toast.LENGTH_SHORT)
                    .show();
        }

        /**
         * (非 Javadoc)
         *
         * @Title: onActionComplete
         * @Description: 摇一摇动作完成后回调 (non-Javadoc)
         * @param event
         * @see com.umeng.socialize.sensor.UMSensor.OnSensorListener#onActionComplete(SensorEvent)
         */
        @Override
        public void onActionComplete(SensorEvent event) {
            Toast.makeText(activity, "游戏暂停", Toast.LENGTH_SHORT).show();
        }

        /**
         * (非 Javadoc)
         *
         * @Title: onButtonClick
         * @Description: 用户点击分享窗口的取消和分享按钮触发的回调
         * @param button
         * @see com.umeng.socialize.sensor.UMSensor.OnSensorListener#onButtonClick(com.umeng.socialize.sensor.UMSensor.WhitchButton)
         */
        @Override
        public void onButtonClick(UMSensor.WhitchButton button) {
            if (button == UMSensor.WhitchButton.BUTTON_CANCEL) {
                Toast.makeText(activity, "取消分享,游戏重新开始", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // 分享中
            }
        }
    };


    private static ArrayList<ShareModel> shareModels = new ArrayList<>();

    static class BottomSheetRecyclerViewAdapter extends RecyclerView.Adapter<BottomSheetRecyclerViewAdapter.ViewHolder> {

        public ItemClickListener mItemClickListener;

        public void setItemClickListener(ItemClickListener listener) {
            mItemClickListener = listener;
        }

        public interface ItemClickListener {
            public void onItemClick(int pos);
        }

        private Context mContext;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.iv_icon);
                mTextView = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public BottomSheetRecyclerViewAdapter(Context context) {
            super();
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_bottom_sheet_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.mTextView.setText(shareModels.get(position).getName());
            holder.mImageView.setImageResource(shareModels.get(position).getId());
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return shareModels.size();
        }
    }

    static class ShareModel {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ShareModel(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }


}
