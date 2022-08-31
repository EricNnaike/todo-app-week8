package com.example.todoapp.service.impl;

import com.example.todoapp.exceptions.IDNotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    Task task;

    @Override
    public Task addTask(Task task) {
      return (Task) taskRepository.save(task);
    }

    @Override
    public List<Task> showAllTask() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
       return taskRepository.findById(id).orElseThrow(() -> new IDNotFoundException("Task id " +id+ " ot found"));
    }

    @Override
    public void deleteById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IDNotFoundException("Task doesnot exist"));
        taskRepository.delete(task);
    }

//    @Override
//    public Task searchById(Long id) {
//        List<Task> taskList = taskService.showAllTask();
//        taskList.forEach(data -> {
//            if (data.getId() == id) {
//               new Task(data.getId(), data.getTitle(), data.getDescription(), data.isCompleted() , data.getCreatedDate(), data.getUpdatedDate());
//            }
////            throw new ResourceNotFoundException("Task", "id", id);
//        });
//        return task;
//    }

    @Override
    public List<Task> searchByTaskId(String keyword) {
        if (keyword != null) {
            return taskRepository.searchById(keyword);
        }
        return (List<Task>) taskRepository.findAll();
    }
    public Optional<Task> searchTaskById(long id){
        return taskRepository.findById(id);
    }


}
