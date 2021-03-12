package com.example.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class ToDoController {

    private final ToDoRepository toDoRepository;

    @GetMapping
    public Iterable<ToDo> getAll(){
        return this.toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ToDo getById(@PathVariable String id){
        return this.toDoRepository.findById(id).orElseThrow(() ->new ToDoNotFoundException(id));
    }

    @PostMapping
    public ToDo newToDo(@RequestBody ToDo toDo){
        return this.toDoRepository.save(toDo);
    }

    @DeleteMapping("/{id}")
    public ToDo deleteById(@PathVariable String id){
        ToDo toDo = this.toDoRepository.findById(id).orElseThrow(() ->new ToDoNotFoundException(id));
        this.toDoRepository.deleteById(toDo.getId());
        return toDo;
    }

    @ExceptionHandler({ToDoNotFoundException.class})
    public Map<String, String> handleError(HttpServletRequest req, Exception ex){
        Map<String,String> result = new HashMap<>();
        result.put("message",ex.getMessage());
        return result;
    }

}
