package com.example.wwk.imooc.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wwk.imooc.R;
import com.example.wwk.imooc.activity.base.BaseActivity;
import com.example.wwk.imooc.view.home.HomeFragment;
import com.example.wwk.imooc.view.home.MessageFragment;
import com.example.wwk.imooc.view.home.UserFragment;

/**
 * Created by wwk on 17/6/9.
 *
 * HomeActivity
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * Fragment相关
     */
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private UserFragment mUserFragment;
    private Fragment mCurrent;

    /**
     * UI相关
     */
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
        // 添加默认要显示的Fragment
        mHomeFragment = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initializeView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mUserLayout = (RelativeLayout) findViewById(R.id.user_layout_view);
        mUserLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.pond_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mUserView = (TextView) findViewById(R.id.user_image_view);
        mHomeView.setBackgroundResource(R.drawable.tab_home_selected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 用来隐藏fragment
     * @param fragment
     * @param ft
     */
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.tab_pond);
                mMessageView.setBackgroundResource(R.drawable.tab_message);
                mUserView.setBackgroundResource(R.drawable.tab_person);

                // 隐藏其他两个fragment
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mUserFragment, fragmentTransaction);
                // 将HomeFragment的内容显示给用户
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment);
                } else {
                    fragmentTransaction.show(mHomeFragment);
                }
                break;

            case R.id.message_layout_view:
                mMessageView.setBackgroundResource(R.drawable.tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.tab_home);
                mPondView.setBackgroundResource(R.drawable.tab_pond);
                mUserView.setBackgroundResource(R.drawable.tab_person);

                // 隐藏其他两个fragment
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mUserFragment, fragmentTransaction);
                // 将HomeFragment的内容显示给用户
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    fragmentTransaction.show(mMessageFragment);
                }
                break;

            case R.id.user_layout_view:
                mUserView.setBackgroundResource(R.drawable.tab_person_selected);
                mMessageView.setBackgroundResource(R.drawable.tab_message);
                mHomeView.setBackgroundResource(R.drawable.tab_home);
                mPondView.setBackgroundResource(R.drawable.tab_pond);

                // 隐藏其他两个fragment
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                // 将HomeFragment的内容显示给用户
                if (mUserFragment == null) {
                    mUserFragment = new UserFragment();
                    fragmentTransaction.add(R.id.content_layout, mUserFragment);
                } else {
                    fragmentTransaction.show(mUserFragment);
                }
                break;
        }

        fragmentTransaction.commit();
    }
}
