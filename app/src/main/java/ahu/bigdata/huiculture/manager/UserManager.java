package ahu.bigdata.huiculture.manager;

import com.avos.avoscloud.AVUser;

/**
 * Created by YCH on 2017/10/18.
 * Function:
 */
public class UserManager {

    private static UserManager userManager = null;
    private AVUser user = null;

    public static UserManager getInstance() {

        if (userManager == null) {

            synchronized (UserManager.class) {

                if (userManager == null) {

                    userManager = new UserManager();
                }
                return userManager;
            }
        } else {

            return userManager;
        }
    }

    /**
     * init the user
     *
     * @param user
     */
    public void setUser(AVUser user) {

        this.user = user;
    }

    public boolean hasLogined() {

        return user == null ? false : true;
    }

    /**
     * has user info
     *
     * @return
     */
    public AVUser getUser() {

        return this.user;
    }

    /**
     * remove the user info
     */
    public void removeUser() {

        this.user = null;
    }
}
