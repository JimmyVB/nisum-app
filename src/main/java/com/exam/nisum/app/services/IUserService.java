package com.exam.nisum.app.services;

import com.exam.nisum.app.entity.User;

public interface IUserService {

    public User save(User user);
    public boolean findEmail(User user);
}
