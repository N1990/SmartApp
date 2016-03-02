package com.cmbb.smartkids.framework.widget.wheelview;

import android.os.Parcelable;
import android.view.View;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 15:32
 */
public class WheelCustomListener {
    public interface DialogListener {
        void onClick(View v);
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position, Object object);
    }

    public interface NetWorkListener<T extends Parcelable> {

        /**
         * 网络请求成功
         *
         * @param object
         */
        void onSuccessListener(T object, String msg);


        /**
         * 网络请求成功，但是返回验证码错误
         *
         * @param message
         */
        void onErrorListener(String message);

    }


}
