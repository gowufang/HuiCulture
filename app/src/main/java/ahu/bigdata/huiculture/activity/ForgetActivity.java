package ahu.bigdata.huiculture.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;

/**
 * Created by YCH on 2017/11/7.
 * Function:忘记密码Activity
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;
    private Button btn_update_password;


    private Button btn_forget_password;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {

        btn_forget_password = (Button) findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(this);

        et_now = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_password = (EditText) findViewById(R.id. et_new_password);
        btn_update_password = (Button) findViewById(R.id.btn_update_password);

        btn_update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btn_forget_password:
                //1.获取输入的邮箱
                final String email = et_email.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(email)) {
//                    //3.发送邮件
//                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e==null) {
//
//                                Toast.makeText(ForgetActivity.this, "邮箱已发送至"+email, Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                            else {
//
//                                Toast.makeText(ForgetActivity.this, "邮箱发送失败", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                }else{


                }
                break;
            case R.id.btn_update_password:
                //获取输入框的值
                String now = et_now.getText().toString().trim();
                String news = et_new.getText().toString().trim();
                String new_password = et_new_password.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(now)&&!TextUtils.isEmpty(news)&&!TextUtils.isEmpty(new_password)) {

                    //判断两次输入的密码是否一致
                    if (news.equals(new_password)) {
//                        //重置密码
//                        MyUser.updateCurrentUserPassword(now, news, new UpdateListener() {
//                            @Override
//                            public void done(BmobException e) {
//                                if (e==null) {
//
//                                    Toast.makeText(ForgetActivity.this, "重置密码成功", Toast.LENGTH_SHORT).show();
//                                    finish();//回到LoginActivity
//                                }else {
//
//                                    Toast.makeText(ForgetActivity.this, "重置密码失败", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
                    }else {

                        Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "两次输入的密码不能为空 ", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
