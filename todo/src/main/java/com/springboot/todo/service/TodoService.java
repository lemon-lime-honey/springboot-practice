package com.springboot.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todo.mapper.TodoMapper;
import com.springboot.todo.vo.Todo;

@Service
public class TodoService {
    private TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public void addTodo(Todo todo) {
        todo.setIsCompleted("N");
        todoMapper.save(todo);
    }

    public List<Todo> getTodoList(){
        return todoMapper.findAll();
    }

    public void deleteTodo(int id) {
        todoMapper.deleteById(id);
    }
}
