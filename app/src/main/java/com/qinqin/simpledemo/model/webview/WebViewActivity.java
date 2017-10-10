package com.qinqin.simpledemo.model.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.bean.js.AndroidtoJs;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.btn_js)
    Button btnJs;
    @BindView(R.id.btn_android)
    Button btnAndroid;
    @BindView(R.id.layout)
    FrameLayout layout;

    private WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initToolbar() {
        //null
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initView();
        initWebSetting();
        initWebView();
    }

    @Override
    protected void initData() {

    }

    private void initView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        layout.addView(webView);
    }

    private void initWebView() {
        //JS调用Android代码
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/javascript.html");
        //方式1. 加载一个网页：
        //webView.loadUrl("http://www.baidu.com/");
        //方式2：加载apk包中的html页面
        //webView.loadUrl("file:///android_asset/test.html");
        //方式3：加载手机本地的html页面
        //webView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");
        // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //设定加载开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //设定加载结束的操作
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //设定加载资源的操作
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //步骤1：写一个html文件（error_handle.html），用于出错时展示给用户看的提示页面
                //步骤2：将该html文件放置到代码根目录的assets文件夹下
                //步骤3：复写WebViewClient的onRecievedError方法
                //该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (error.getErrorCode() == 404) {
                        //执行错误代码
                    }
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                //webView默认是不处理https请求的，页面显示空白，需要进行如下设置
                //handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }


        });
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
    }

    private void initWebSetting() {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    //android调用JS代码
    @OnClick(R.id.btn_android)
    void androidToJs() {
        // 必须另开线程进行JS方法调用(否则无法调用)
        webView.post(new Runnable() {
            @Override
            public void run() {
                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                //webView.loadUrl("javascript:callJS()");
                // 只需要将第一种方法的loadUrl()换成下面该方法即可
//                webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//                        //此处为 js 返回的结果
//                    }
//                });
                //推荐使用此方法
                // Android版本变量
                final int version = Build.VERSION.SDK_INT;
                // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                if (version < 18) {
                    webView.loadUrl("javascript:callJS()");
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                //此处为 js 返回的结果
                            }
                        });
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        //通过这种方式解决WebView内存泄漏的问题
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}


//对于Android调用JS代码的方法有2种：
//        通过WebView的loadUrl   （）
//        通过WebView的evaluateJavascript（）
//对于JS调用Android代码的方法有3种：
//
//        通过WebView的addJavascriptInterface（）进行对象映射
//        通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
//        通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息
