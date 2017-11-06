package ahu.bigdata.huiculture.module.user;

import com.avos.avoscloud.AVUser;

import ahu.bigdata.huiculture.module.BaseModel;

/**
 * Created by renzhiqiang on 15/11/20.
 */
public class User{
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
