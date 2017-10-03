package ahu.bigdata.huiculture.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import ahu.bigdata.huiculture.R;

/**
 * Created by ych10 on 2017/9/21.
 * Function:为所有的activity提供公共的行为
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 输出日志所需TAG
     */
    public String TAG;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getClassName();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        /**
//         *显示箭头返回
//         */
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //菜单栏

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
