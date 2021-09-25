package com.exam.nisum.app.services;

import com.exam.nisum.app.dao.IUserDao;
import com.exam.nisum.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean findEmail(User user) {
        return userDao.findByEmail(user.getEmail()) == null ? false : true;
    }
}
