package com.springboot.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todo.vo.Todo;

@Service
public class TodoService {
    private List<Todo> todoList;

    public TodoService(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void addTodo(Todo todo) {
        todo.setId(todoList.size());
        todoList.add(todo);
    }

    public List<Todo> getTodoList(){
        return todoList;
    }

    public void deleteTodo(int index) {
        todoList.remove(index);
    }
}
