package com.qinqin.simpledemo.module.design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DesignActivity extends BaseActivity {
    @BindView(R.id.snack_btn)
    Button snackBar;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.design_et)
    EditText designEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_design;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        tilPwd.setHint("Password");
        designEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 4) {
                    tilPwd.setError("Password error");
                    tilPwd.setErrorEnabled(true);
                } else {
                    tilPwd.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.snack_btn)
    void snackBar(View view) {
        Snackbar.make(view, "Snackbar comes out", Snackbar.LENGTH_LONG).setAction("Action",
                v -> Toast.makeText(DesignActivity.this, "Toast comes out", Toast.LENGTH_SHORT).show()).show();
    }

    public static void startToDesign(Context context) {
        Intent intent = new Intent(context, DesignActivity.class);
        context.startActivity(intent);
    }
}
