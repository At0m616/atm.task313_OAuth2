package com.example.atm.task313.dao;


import com.example.atm.task313.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select distinct u from User u JOIN FETCH u.roles where u.id = :id")
    User findUserById(@Param("id") Long id);

    User findDistinctByUsername(String username);

    void deleteById(long id);
}
