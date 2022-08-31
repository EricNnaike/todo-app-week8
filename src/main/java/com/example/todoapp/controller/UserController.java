package com.example.todoapp.controller;

import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.exceptions.UserNotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.service.impl.TaskServiceImpl;
import com.example.todoapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Controller
//@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, UserDTO userDTO, Model model) {
        model.addAttribute("message", user.getFirstName() + " "
                + user.getLastName() +" has been registered successfully");
        userService.register(userDTO);
        return "register";
    }

    @GetMapping("/login")
    public String getlogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loginProcess")
    public ModelAndView login(@ModelAttribute("userdto") UserDTO userDTO, @RequestParam String email, @RequestParam String password, Model model) throws UserNotFoundException {
        User user1 = userService.login(userDTO.getEmail(), userDTO.getPassword());
        if (user1 != null) {
           if (user1.getEmail().equals(email) && user1.getPassword().equals(password)){
               ModelAndView mav = new ModelAndView("home");
               mav.addObject("user", user1);
               mav.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
               List<Task> taskList = taskService.showAllTask();
               model.addAttribute("listTask", taskList);
               return mav;
           }
        }
        return new ModelAndView("register");
    }

}
