package com.factory.mapping;

import com.factory.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepostory extends JpaRepository<User,Long> {
    
    public List<User> findByUsername(@Param(value="username")String username);
}
