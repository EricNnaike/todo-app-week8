package com.example.todoapp.controller;

import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.service.TaskService;
import com.example.todoapp.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/getTask")
    public String taskForm() {
        return "addForm2";
    }

    @PostMapping("/addtaskprocess")
    public String addTask(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("message", "Added successfully");
        model.addAttribute("titlePage", "ADD TASK");
        model.addAttribute("submit", "Add Task");
        taskService.addTask(task);
        return "addForm2";
    }

    @GetMapping("/viewAllTasks")
    public String showAllTasks(Model model) {
        List<Task> taskList = taskService.showAllTask();
        model.addAttribute("taskList", taskList);
        model.addAttribute("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return "showTask";
    }


    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
       try {
           Task task = taskService.getTaskById(id);
           model.addAttribute("task", task);
           model.addAttribute("titlePage", "Edit Task");
           model.addAttribute("title", "Edit Task Id " + id);
           model.addAttribute("submit", "Save Task");

           return "editTask";
       }catch (Exception e) {
           System.out.println(e.getMessage());
           return "showTask";
       }
    }

    @PostMapping("/editTaskProcess/{id}")
    public String editTaskProcess(@PathVariable("id") Long id, Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            task.setId(id);
            return "showTask";
        }
        model.addAttribute("message", "Task updated successfully");
        task.setUpdatedDate(LocalDateTime.now());
        taskService.addTask(task);
        return "home";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model) {
        model.addAttribute("message", "Task deleted successfully");
        taskService.deleteById(id);
        return "showTask";
    }

//    @GetMapping("/searchTask")
//    public String searchById(@RequestParam("keyword") String keyword, Model model) {
//        List<Task> task = taskService.searchByTaskId(keyword);
//        model.addAttribute("task", task);
//        model.addAttribute("keyword", keyword);
//        return "showTask";
//    }

    @PostMapping("/searchTask")
    public String findById(@RequestParam("keyword") String keyword, Model model){
        Long id = Long.parseLong(keyword);
        Optional<Task> opt = taskService.searchTaskById(id);
        if (opt.isPresent()){
            model.addAttribute("taskList", opt.get());
            return "showTask";
        }
        else{
            return "404";
        }
    }

}
