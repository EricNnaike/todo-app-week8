package com.example.todoapp.service;

import com.example.todoapp.model.Task;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    List<Task> showAllTask();
    Task getTaskById(Long id);
    void deleteById(Long id);
//    Task searchById(Long id);
    List<Task> searchByTaskId(String keyword);

}
