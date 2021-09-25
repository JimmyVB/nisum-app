package com.exam.nisum.app.dao;


import com.exam.nisum.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserDao extends JpaRepository<User, Long> {

    @Query("select p from User p where p.email=?1")
    public User findByEmail(String email);

}
