package com.example.ToDoApp.Repository;

import com.example.ToDoApp.Models.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AdminToDoRepository extends JpaRepository<ToDoModel, Integer> {

    @Query(value = "SELECT * FROM todo_model", nativeQuery = true)
    List<ToDoModel> viewAllToDos();
}
