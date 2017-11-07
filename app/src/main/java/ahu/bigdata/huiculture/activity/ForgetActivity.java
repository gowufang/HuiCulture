package ahu.bigdata.huiculture.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;

/**
 * Created by YCH on 2017/11/7.
 * Function:忘记密码Activity
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener {

    /**
     * UI
     */
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
        et_email = findViewById(R.id.et_email);
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
                    AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                Toast.makeText(ForgetActivity.this, "发送邮件成功！请注意查收~", Toast.LENGTH_SHORT).show();
                            } else {
                                e.printStackTrace();
                                Toast.makeText(ForgetActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{

                    Toast.makeText(this, "输入的邮箱不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
