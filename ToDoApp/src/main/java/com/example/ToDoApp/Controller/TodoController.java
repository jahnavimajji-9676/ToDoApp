package com.example.ToDoApp.Controller;

import com.example.ToDoApp.Models.ToDoModel;
import com.example.ToDoApp.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping("/addTodo")
    public ResponseEntity<Map<String, String>> addToDo(@RequestBody ToDoModel toDoModel) {
        ToDoModel savedTodo = toDoRepository.save(toDoModel);
        Map<String, String> response = new HashMap<>();
        if (savedTodo.getId() != 0) {
            response.put("Status", "ToDo added successfully!");
        } else {
            response.put("Status", "Failed to add ToDo");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewMyTodos/{userId}")
    public ResponseEntity<List<ToDoModel>> viewMyToDos(@PathVariable int userId) {
        List<ToDoModel> todos = toDoRepository.viewMyToDos(userId);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/deleteTodo/{userId}/{todoId}")
    public ResponseEntity<Map<String, String>> deleteToDo(@PathVariable int userId, @PathVariable int todoId) {
        toDoRepository.deleteToDoByIdAndUserId(todoId, userId);
        Map<String, String> response = new HashMap<>();
        response.put("Status", "ToDo deleted successfully!");
        return ResponseEntity.ok(response);
    }
}
