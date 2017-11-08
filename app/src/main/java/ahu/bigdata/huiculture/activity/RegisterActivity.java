package ahu.bigdata.huiculture.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.module.user.User;


/**
 * Created by YCH on 2017/11/2.
 * Function:注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    /**
     * UI
     */
    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;
    private boolean isGender=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        initView();

    }

    private void initView() {

        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistered:
                //获取输入框的值
                String name=et_user.getText().toString().trim();//Trim去空格
                String age=et_age.getText().toString().trim();
                String desc=et_desc.getText().toString().trim();
                String pass= et_pass.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                String email=et_email.getText().toString().trim();

                if (!TextUtils.isEmpty(name)
                        &&!TextUtils.isEmpty(age)
                        &&!TextUtils.isEmpty(pass)
                        &&!TextUtils.isEmpty(password)
                        &&!TextUtils.isEmpty(email)) {
                    //判断两次输入的密码是否一致
                    if (pass.equals(password)) {
                        //先把性别判断一下
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                                if (i==R.id.rb_boy) {
                                    isGender = true;
                                } else if (i==R.id.rb_girl) {
                                    isGender = false;
                                }
                            }
                        });
                        if (Integer.parseInt(age) < 0 || Integer.parseInt(age) > 130) {
                            Toast.makeText(RegisterActivity.this, "请输入正确的年龄！", Toast.LENGTH_SHORT).show();
                        } else {

                            //判断简介是否为空
                            if (TextUtils.isEmpty(desc)) {

                                desc = "这个人很懒，什么都没留下";
                            }
                            //判断邮箱格式是否正确
                            if (!checkEmail(email)) {
                                Toast.makeText(RegisterActivity.this, "请输入正确的邮箱格式！", Toast.LENGTH_SHORT).show();
                            } else {
                                //注册
                                AVUser User = new AVUser();
                                User.setUsername(name);
                                User.setPassword(pass);
                                User.setEmail(email);
                                //为当前用户添加属性
                                User.put("age", age);
                                User.put("sex",isGender);
                                User.put("desc",desc);
                                User.signUpInBackground(new SignUpCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        if (e == null) {
                                            //注册成功
                                            Toast.makeText(RegisterActivity.this, "注册成功,请前往邮箱验证！", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this,"注册失败,可能原因："+e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }else{
                        Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //用正则表达式检查邮箱合法性
    private static boolean checkEmail(String email)
    {
        // 验证邮箱的正则表达式
        String format = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

        if (email.matches(format))
        {
            return true;// 邮箱名合法，返回true
        }
        else
        {
            return false;// 邮箱名不合法，返回false
        }
    }
}


