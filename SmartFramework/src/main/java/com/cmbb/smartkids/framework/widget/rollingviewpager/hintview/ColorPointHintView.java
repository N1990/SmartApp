package com.cmbb.smartkids.framework.widget.rollingviewpager.hintview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.cmbb.smartkids.framework.utils.TDevice;


public class ColorPointHintView extends ShapeHintView {
    private int focusColor;
    private int normalColor;

    public ColorPointHintView(Context context,int focusColor,int normalColor) {
        super(context);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
    }

    @Override
    public Drawable makeFocusDrawable() {
        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(focusColor);
        dot_focus.setCornerRadius(TDevice.dip2px(4,getContext()));
        dot_focus.setSize(TDevice.dip2px(8,getContext()), TDevice.dip2px(8,getContext()));
        return dot_focus;
    }

    @Override
    public Drawable makeNormalDrawable() {
        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setColor(normalColor);
        dot_normal.setCornerRadius(TDevice.dip2px(4,getContext()));
        dot_normal.setSize(TDevice.dip2px(8,getContext()), TDevice.dip2px(8,getContext()));
        return dot_normal;
    }
}
