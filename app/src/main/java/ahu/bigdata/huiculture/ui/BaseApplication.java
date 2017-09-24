package ahu.bigdata.huiculture.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ahu.bigdata.huiculture.R;

/**
 * Created by ych10 on 2017/9/21.
 * Function:1.统一的属性 2.统一接口 3.统一的方法
 */
public class BaseApplication extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
        //显示箭头返回
        /**
         *
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
