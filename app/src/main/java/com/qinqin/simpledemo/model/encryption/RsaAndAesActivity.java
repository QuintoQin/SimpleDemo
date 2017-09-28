package com.qinqin.simpledemo.model.encryption;

import android.os.Bundle;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.AESUtils;
import com.qinqin.common.utils.RSAUtils;

import butterknife.BindView;

public class RsaAndAesActivity extends BaseActivity {


    @BindView(R.id.rsa_pub1)
    TextView rsaPub1;
    @BindView(R.id.rsa_pub2)
    TextView rsaPub2;
    @BindView(R.id.rsa_pri1)
    TextView rsaPri1;
    @BindView(R.id.rsa_pri2)
    TextView rsaPri2;
    @BindView(R.id.ras_enTv)
    TextView rasEnTv;
    @BindView(R.id.ras_detv)
    TextView rasDetv;
    @BindView(R.id.aes_tv1)
    TextView aesTv1;
    @BindView(R.id.aes_tv2)
    TextView aesTv2;


    private String str = "我现在在深圳";
    private String line;
    private String line_after;
    private String key = "871176327f774d10";
    private String content = "Hs6e2bLwQUDaCgwJ7if5mA==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rsa_and_aes;
    }

    //NULL
    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //获得公钥密钥
//        try {
//            RSAUtils.getKeys();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        initData();
    }

    private void initData() {
        //RSA
        try {
            //公钥加密，得到密文

            line = RSAUtils.encryptByPublicKey(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rasEnTv.setText(line);
        try {
            //私钥解密，得到明文
            line_after = RSAUtils.decryptByPrivateKey(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rasDetv.setText(line_after);
        //AES
        String key = AESUtils.generateKey();
        String content = "I Love You";
        String afterText = AESUtils.encryptData(key, content);
        aesTv1.setText("密钥" + key);
        LogUtils.e("密钥" + key);
        aesTv2.setText("密文" + afterText);
        LogUtils.e("密文" + afterText);
    }
}
