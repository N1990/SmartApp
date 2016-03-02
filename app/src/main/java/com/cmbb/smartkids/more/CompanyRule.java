package com.cmbb.smartkids.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.utils.TDevice;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/12/3 下午4:57
 */
public class CompanyRule extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        ((TextView)findViewById(R.id.tv_title)).setText(R.string.service_and_protocol);
        String result = TDevice.getContentFromRaw(this, R.raw.popman_rule);
        ((TextView) findViewById(R.id.tv_content)).setText(result);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_rule;
    }

    /**
     * Intent 进入
     *
     * @param context Context
     */
    public static void newInstance(Context context) {
        Intent intent = new Intent(context, CompanyRule.class);
        context.startActivity(intent);
    }
}
