package ahu.bigdata.huiculture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.manager.UserManager;
import ahu.bigdata.huiculture.module.user.User;
import ahu.bigdata.huiculture.utils.ShareUtils;

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
    private Button btn_register;
    private Button btn_login;
    private CheckBox keep_password;
    private TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }


    private void initView() {
        mUserNameView =  findViewById(R.id.associate_email_input);
        mPasswordView =  findViewById(R.id.login_input_password);
        btn_login =  findViewById (R.id.login_button);
        btn_login.setOnClickListener(this);
        btn_register = findViewById(R.id.register_button);
        btn_register.setOnClickListener(this);
        keep_password = findViewById(R.id.keep_password);
        //屏幕外点击无效
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        //设置选中的状态
        Boolean isChecked= ShareUtils.getBoolean(this, "keeppass", false);/*默认不勾选*/
        keep_password.setChecked(isChecked);
        if (isChecked) {
            //设置显示在界面的用户名、密码
            mUserNameView.setText(ShareUtils.getString(this,"name",""));//默认空串
            mPasswordView.setText(ShareUtils.getString(this,"password",""));
        }
        tv_forget = findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.register_button:
                toRegister();
                break;
            case R.id.tv_forget:
                toForget();
                break;
        }
    }

    private void toForget() {
        startActivity(new Intent(this,ForgetActivity.class));
    }

    private void toRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    /**
     * 用户信息存入数据库，以使让用户一打开应用就是一个登陆过的状态
     */
    private void insertUserInfoIntoDB() {

    }

    //发送登陆请求
    private void login() {

        String username = mUserNameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
       //判断是否为空
        if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)) {
            //登录
            AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                            UserManager.getInstance().setUser(avUser);//保存当前用户单例对象
                            sendLoginBroadcast();
                            //关闭软键盘
                            closeKeyboard();
                            finish();
                    } else {
                        //为了提高用户友好性，检查常见错误
                        switch (e.getCode()) {
                            case 216:
                                Toast.makeText(LoginActivity.this, "请其前往邮箱验证哦~", Toast.LENGTH_SHORT).show();
                                break;
                            case 210:
                                Toast.makeText(LoginActivity.this, "用户名和密码不匹配！", Toast.LENGTH_SHORT).show();
                                break;
                            case 211:
                                Toast.makeText(LoginActivity.this, "找不到该用户，请先注册。", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }else {
            Toast.makeText(this," 输入框不能为空!",Toast.LENGTH_SHORT).show();
        }
    }

    //向整个应用发送登陆广播事件
    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }
    //关闭软键盘
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this,"name",mUserNameView.getText().toString().trim());
            ShareUtils.putString(this,"password",mPasswordView.getText().toString().trim());
        }else {

            ShareUtils.delShar(this,"name");
            ShareUtils.delShar(this,"password");

        }
    }
}
