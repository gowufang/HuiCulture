package ahu.bigdata.huiculture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.youdu.okhttp.listener.DisposeDataListener;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.manager.UserManager;
import ahu.bigdata.huiculture.module.user.User;
import ahu.bigdata.huiculture.network.http.RequestCenter;

/**
 * Created by YCH on 2017/10/18.
 * Function:登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //自定义登陆广播Action
    public static final String LOGIN_ACTION = "com.imooc.action.LOGIN_ACTION";
    /**
     * UI
     */
    private EditText mUserNameView;
    private EditText mPasswordView;
    private TextView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }


    private void initView() {
        getSupportActionBar().hide();
        mUserNameView = (EditText) findViewById(R.id.associate_email_input);
        mPasswordView = (EditText) findViewById(R.id.login_input_password);
        mLoginView = (TextView) findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.iv_login_logo:
                break;
        }
    }

    /**
     * 用户信息存入数据库，以使让用户一打开应用就是一个登陆过的状态
     */
    private void insertUserInfoIntoDB() {
    }

    //发送登陆请求
    private void login() {

        String userName = mUserNameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            return;
        }

        if (TextUtils.isEmpty(password)) {
            return;
        }


        RequestCenter.login(userName, password, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                /**
                 * 这部分可以封装起来，封装为到一个登陆流程类中
                 */
                User user = (User) responseObj;
                UserManager.getInstance().setUser(user);//保存当前用户单例对象
//                connectToSever();
                sendLoginBroadcast();
                /**
                 * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                 * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                 */
                insertUserInfoIntoDB();

                finish();//销毁当前登陆页面
            }

            @Override
            public void onFailure(Object reasonObj) {
            }
        });
    }


    //向整个应用发送登陆广播事件
    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }
}
