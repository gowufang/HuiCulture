package ahu.bigdata.huiculture.module.user;

import ahu.bigdata.huiculture.module.BaseModel;
import cn.bmob.v3.BmobUser;

/**
 * Created by renzhiqiang on 15/11/20.
 */
public class User extends BmobUser {
    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
