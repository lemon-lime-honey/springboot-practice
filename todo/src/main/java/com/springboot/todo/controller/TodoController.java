package com.springboot.todo.controller;

import java.util.List;

import com.springboot.todo.vo.ResultDto;
import com.springboot.todo.vo.Todo;
import org.springframework.web.bind.annotation.*;

public class TodoController {
    public List<Todo> getTodoList(){
        List<Todo> list=null;
        return list;
    }

    public ResultDto addTodo(Todo todo) {
        return new ResultDto(200, "Success");
    }

    public ResultDto deleteTodo(@PathVariable("id") Integer id) {
        return new ResultDto(200, "Success");
    }

}