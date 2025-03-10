package com.example.ToDoApp.Repository;

import com.example.ToDoApp.Models.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoModel, Integer> {

    @Query(value = "SELECT * FROM todo_model WHERE user_id = ?1", nativeQuery = true)
    List<ToDoModel> viewMyToDos(int userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todo_model WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    void deleteToDoByIdAndUserId(int id, int userId);
}
