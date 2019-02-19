package com.pyxis.todo.controller;

import com.pyxis.todo.domain.model.Todo;
import com.pyxis.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version:v1}")
@RequiredArgsConstructor
@Validated
public class TodoRestController {
    
    private final TodoService todoService;

    @GetMapping("/todo")
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @PostMapping("/todo")
    public Todo create(@Valid @RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping(value="/todo/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {
        return todoService.findById(id);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> update(@PathVariable("id") String id, @Valid @RequestBody Todo todo){
        return todoService.update(id,todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return todoService.deleteById(id);
    }
}
