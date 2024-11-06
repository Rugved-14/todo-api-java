package com.example.todo.controller;

import com.example.todo.model.TodoItem;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItemById(@PathVariable Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(@PathVariable Long id, @RequestBody TodoItem todoDetails) {
        TodoItem todoItem = todoRepository.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setTitle(todoDetails.getTitle());
            todoItem.setCompleted(todoDetails.isCompleted());
            return todoRepository.save(todoItem);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
