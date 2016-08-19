package com.example.takaya.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager)findViewById(R.id.viewpager);
        PagerAdapter mPagerAdapter = new MyPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        //ViewPagerが保持するページ数を変更
        mViewPager.setOffscreenPageLimit(2);
    }

    private class MyPagerAdapter extends PagerAdapter implements View.OnClickListener {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // レイアウトファイル名を配列で指定します。
            int[] pages = {R.layout.page1, R.layout.page2, R.layout.page3};

            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout ;
            layout = inflater.inflate(pages[position], null);
            ((ViewPager) container).addView(layout);

            //Page2のViewの処理が走ったらWeb設定する
            if(1 == position) {
                WebView myWebView = (WebView) findViewById(R.id.webView1);

                //リンクをタップしたときに標準ブラウザを起動させない
                myWebView.setWebViewClient(new WebViewClient());

                //最初にgoogleのページを表示する。
                myWebView.loadUrl("http://www.google.co.jp");

                //jacascriptの有効／無効
                myWebView.getSettings().setJavaScriptEnabled(true);
                //ズーム機能の有効／無効
                myWebView.getSettings().setBuiltInZoomControls(true);
                //フォームデータの保存 有効／無効
                myWebView.getSettings().setSaveFormData(true);
                //パスワードの記憶 有効／無効
                myWebView.getSettings().setSavePassword(true);
                //プラグインの ON／OFF
                myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

                //URL検索
                Button bt_search = (Button) findViewById(R.id.search);
                bt_search.setOnClickListener(this);
            }

            return layout;
        }

        public void onClick(View view){
            EditText editText = (EditText) findViewById(R.id.addressbar);
            String urlString = editText.getText().toString();
            WebView myWebView = (WebView) findViewById(R.id.webView1);
            myWebView.loadUrl(urlString);

        }

    @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }

        @Override
        public int getCount() {
            // ページ数を返します。
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}