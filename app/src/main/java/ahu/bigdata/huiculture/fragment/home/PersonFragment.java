package ahu.bigdata.huiculture.fragment.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youdu.adutil.ImageLoaderUtil;
import com.youdu.okhttp.listener.DisposeDataListener;

import java.io.File;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.LoginActivity;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.manager.UserManager;
import ahu.bigdata.huiculture.utils.L;
import ahu.bigdata.huiculture.utils.Util;
import ahu.bigdata.huiculture.view.dialog.CustomDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ych10 on 2017/9/21.
 * Function:个人中心界面
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {

    /**
     * UI
     */
    private View mContentView;
    private RelativeLayout mLoginLayout;
    private CircleImageView mPhotoView;
    private TextView mLoginInfoView;
    private TextView mLoginView;
    private RelativeLayout mLoginedLayout;
    private TextView mUserNameView;
    private TextView mTickView;
    private TextView mVideoPlayerView;
    private TextView mShareView;
    private TextView mQrCodeView;
    private TextView mUpdateView;
    private CustomDialog dialog;
    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;
    private CircleImageView mUserPhotoView;

    /**
     * data
     */
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private File tempFile = null;

    //自定义了一个广播接收器
    private LoginBroadcastReceiver receiver = new LoginBroadcastReceiver();

    public PersonFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        registerLoginBroadcast();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContentView= inflater.inflate(R.layout.fragment_person_layout,null);
        initView();
        return mContentView;
    }
    private void initView() {

        mLoginLayout = (RelativeLayout) mContentView.findViewById(R.id.login_layout);
        mLoginLayout.setOnClickListener(this);
        mLoginedLayout = (RelativeLayout) mContentView.findViewById(R.id.logined_layout);
        mLoginedLayout.setOnClickListener(this);

        mPhotoView = (CircleImageView) mContentView.findViewById(R.id.photo_view);
        mPhotoView.setOnClickListener(this);
        mLoginView = (TextView) mContentView.findViewById(R.id.login_view);
        mLoginView.setOnClickListener(this);
        mVideoPlayerView = (TextView) mContentView.findViewById(R.id.video_setting_view);
        mVideoPlayerView.setOnClickListener(this);
        mShareView = (TextView) mContentView.findViewById(R.id.share_imooc_view);
        mShareView.setOnClickListener(this);
        mQrCodeView = (TextView) mContentView.findViewById(R.id.my_qrcode_view);
        mQrCodeView.setOnClickListener(this);
        mLoginInfoView = (TextView) mContentView.findViewById(R.id.login_info_view);
        mUserNameView = (TextView) mContentView.findViewById(R.id.username_view);
        mTickView = (TextView) mContentView.findViewById(R.id.tick_view);
        mUpdateView = (TextView) mContentView.findViewById(R.id.update_view);
        mUpdateView.setOnClickListener(this);

        mUserPhotoView = (CircleImageView) mContentView.findViewById(R.id.user_photo_view);
        mUserPhotoView.setOnClickListener(this);

        //初始化dialog
        dialog = new CustomDialog(getActivity(), 0, 0,
                R.layout.dialog_photo, R.style.pop_anim_style, Gravity.BOTTOM, 0);
        //提示框以外点击无效
        dialog.setCancelable(false);
        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);


    }


    @Override
    public void onResume() {
        super.onResume();
        //根据用户信息更新我们的fragment
        if (UserManager.getInstance().hasLogined()) {
            if (mLoginedLayout.getVisibility() == View.GONE) {
                mLoginLayout.setVisibility(View.GONE);
                mLoginedLayout.setVisibility(View.VISIBLE);
                mUserNameView.setText(UserManager.getInstance().getUser().getUsername());
                mTickView.setText(UserManager.getInstance().getUser().getEmail());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterLoginBroadcast();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_imooc_view:
                break;
            case R.id.login_layout:
            case R.id.login_view:
                //未登陆，则跳轉到登陸页面
                if (!UserManager.getInstance().hasLogined()) {
                    toLogin();
                }
                break;
            case R.id.my_qrcode_view:
                if (!UserManager.getInstance().hasLogined()) {
                    //未登陆，去登陆。
                    toLogin();
                } else {
                    //已登陆根据用户ID生成二维码显示

                }
                break;
            case R.id.video_setting_view:

                break;
            case R.id.update_view:
                if (hasPermission(Constant.WRITE_READ_EXTERNAL_PERMISSION)) {

                } else {
                    requestPermission(Constant.WRITE_READ_EXTERNAL_CODE, Constant.WRITE_READ_EXTERNAL_PERMISSION);
                }
                break;

            case R.id.user_photo_view:
                dialog.show();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_picture:
                toPicture();
                break;   
        }
    }

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机数据
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }
    //裁剪
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            L.e("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 去登陆页面
     */
    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }


    //注册广播
    private void registerLoginBroadcast() {

        IntentFilter filter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, filter);
    }

    private void unregisterLoginBroadcast() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            mUserPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //保存
        Util.putImageToShare(getActivity(),mUserPhotoView);
    }

    /**
     * 接收mina发送来的消息，并更新UI
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (UserManager.getInstance().hasLogined()) {
                //更新我们的fragment,表明已经登录
                if (mLoginedLayout.getVisibility() == View.GONE) {
                    mLoginLayout.setVisibility(View.GONE);
                    mLoginedLayout.setVisibility(View.VISIBLE);
                    mUserNameView.setText(UserManager.getInstance().getUser().getUsername());
                    mTickView.setText(UserManager.getInstance().getUser().getEmail());
//                    mPhotoView.setImageResource(R.drawable.profile);
//                    ImageLoaderUtil.getInstance(mContext).displayImage(mPhotoView,R.drawable.profile);
                }
            }
        }
    }

}
