package com.example.ToDoApp.Controller;

import com.example.ToDoApp.Models.ToDoModel;
import com.example.ToDoApp.Repository.AdminToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminToDoController {

    @Autowired
    private AdminToDoRepository adminToDoRepository;

    @GetMapping("/viewAllTodos")
    public ResponseEntity<List<ToDoModel>> viewAllToDos() {
        List<ToDoModel> todos = adminToDoRepository.viewAllToDos();
        return ResponseEntity.ok(todos);
    }
}
