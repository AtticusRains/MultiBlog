package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.UserInfo;
import java.util.List;

public interface UserDAO {
    UserInfo getActiveUser(String username);
    List<UserInfo> findAll();
    void save(UserInfo user);
    boolean userExists(String username);

}
