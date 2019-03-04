package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.UserInfo;
import java.util.List;

public interface UserDAO {
    UserInfo getActiveUser(String username);
    List<UserInfo> findAll();
    void save();
    boolean userExists(String username);

}
