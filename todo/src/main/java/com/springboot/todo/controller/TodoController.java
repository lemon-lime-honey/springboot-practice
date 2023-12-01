package com.springboot.todo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import com.springboot.todo.service.TodoService;
import com.springboot.todo.vo.ResultDto;
import com.springboot.todo.vo.Todo;

import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    @ResponseBody
    public List<Todo> getTodoList(){
        List<Todo> list = todoService.getTodoList();
        return list;
    }

    @PostMapping("/todo")
    @ResponseBody
    public ResultDto addTodo(HttpServletResponse response, @RequestBody Todo todo) {
        Todo target = new Todo(todo.getContent());
        todoService.addTodo(target);
        response.setStatus(HttpServletResponse.SC_OK);
        return new ResultDto(200, "Success");
    }

    @DeleteMapping("/todo/{id}")
    public ResultDto deleteTodo(HttpServletResponse response, @PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
        response.setStatus(HttpServletResponse.SC_OK);
        return new ResultDto(200, "Success");
    }
}