package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo getActiveUser(String username) {
        UserInfo activeUser = new UserInfo();
        short enabled = 1;

        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=? and enabled=?")
                .setParameter(1, username).setParameter(2, enabled).getResultList();

        if(!list.isEmpty())
            activeUser = (UserInfo)list.get(0);

        return activeUser;
    }

    @Override
    public List<UserInfo> findAll() {
        return entityManager.createQuery("FROM UserInfo").getResultList();
    }

    @Override
    public void save(UserInfo user) {
        user.setPassword(encoder().encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public boolean userExists(String username) {
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=?")
                .setParameter(1, username).getResultList();
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

}
