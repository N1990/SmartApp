package com.cmbb.smartkids.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.SecurityCodeModel;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.Constants;
import com.squareup.okhttp.Request;

import java.util.HashMap;

public class SuggestActivity extends BaseActivity implements TextWatcher{

    private EditText etCotent;
    private TextView tvLimit;
    private View customProgressBar;
    private RelativeLayout rlContent;
    private int realLen = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggest;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText(R.string.title_activity_suggest);
        etCotent = (EditText) findViewById(R.id.et_cotent_suggest);
        tvLimit = (TextView) findViewById(R.id.tv_limit_suggest);
        customProgressBar = findViewById(R.id.suggest_progress);
        rlContent = (RelativeLayout) findViewById(R.id.suggest_content);
        etCotent.addTextChangedListener(this);
        customProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        realLen = s.length();
        if(realLen <= 450){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText(realLen + "/500");
        }else if(realLen < 500 && realLen > 450){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("还剩余" + (500 - realLen) + "个");
        }else if(realLen == 500){
            tvLimit.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvLimit.setText("文字已输满");
        }else{
            tvLimit.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvLimit.setText("超过规定字数"+(realLen - 500) + "个");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_suggest) {
            if(realLen > 500 || realLen == 0){
                showShortToast("输入的字数不符合要求");
                return true;
            }
           handleSuggestRequest(etCotent.getText().toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void handleSuggestRequest(String contents){
        customProgressBar.setVisibility(View.VISIBLE);
        rlContent.setEnabled(false);
        HashMap<String, String> params = new HashMap<>();
        params.put("contents", contents);
        SecurityCodeModel.sendSuggestFeekRequest(contents, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                customProgressBar.setVisibility(View.GONE);
                rlContent.setEnabled(true);
                showShortToast(getString(R.string.requestailure));
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                customProgressBar.setVisibility(View.GONE);
                rlContent.setEnabled(true);
                showShortToast(response.getMsg());
                finish();
            }
        });

    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SuggestActivity.class);
        context.startActivity(intent);
    }
}
