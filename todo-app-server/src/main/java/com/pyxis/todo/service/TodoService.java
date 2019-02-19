package com.pyxis.todo.service;

import com.pyxis.todo.domain.model.Todo;
import com.pyxis.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService{
    private final TodoRepository todoRepository;

    @ResponseStatus(value = HttpStatus.CREATED)
    public Todo create(Todo todo){
        log.info("Todo: {} created successfully", todo.toString());
        return todoRepository.save(todo);
    }

    @ResponseStatus(value = HttpStatus.OK)
    public List<Todo> findAll(){
        return todoRepository.findAll(new Sort(Direction.DESC, "createAt"));
    }

//    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Todo> findById(String id) {
        return todoRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

//    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Todo> update(String id, Todo todo) {
        log.info("Todo: {} updated successfully", id);
        return todoRepository.findById(id)
                .map(data -> {
                    data.setTitle(todo.getTitle());
                    data.setCompleted(todo.isCompleted());
                    return ResponseEntity.ok().body(todoRepository.save(data));
                }).orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteById(String id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
