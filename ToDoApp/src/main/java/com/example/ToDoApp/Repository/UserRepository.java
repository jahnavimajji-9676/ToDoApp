package com.example.ToDoApp.Repository;

import com.example.ToDoApp.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT * FROM user_model WHERE email = ?1 and password = ?2", nativeQuery = true)
    List<UserModel> validateUser(String email, String password);


    @Query(value = "SELECT * FROM user_model WHERE email = ?1", nativeQuery = true)
    List<UserModel> findByEmail(String email);
}
