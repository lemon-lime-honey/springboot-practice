package com.springboot.todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.todo.vo.Todo;

@Mapper
public interface TodoMapper {
    void save(@Param("todo") Todo todo);
    List<Todo> findAll();
    void deleteById(@Param("id") Integer id);
}
