package com.springboot.tasksystem.repository;

import com.springboot.tasksystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("""
select u from User u
where u.username=?1
and u.isActive=?2
""")
    UserDetails loadUserByUsername(String username, boolean b);
}
