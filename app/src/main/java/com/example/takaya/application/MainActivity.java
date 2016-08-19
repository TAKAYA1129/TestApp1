package com.example.takaya.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    private class MyPagerAdapter extends PagerAdapter {
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

                //jacascriptを許可する
                myWebView.getSettings().setJavaScriptEnabled(true);

            }

            return layout;
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