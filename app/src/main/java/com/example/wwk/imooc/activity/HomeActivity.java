package com.example.wwk.imooc.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wwk.imooc.R;
import com.example.wwk.imooc.activity.base.BaseActivity;

/**
 * Created by wwk on 17/6/9.
 */

public class HomeActivity extends BaseActivity {

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mUserLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        initializeView();
    }

    private void initializeView() {
        
    }
}
