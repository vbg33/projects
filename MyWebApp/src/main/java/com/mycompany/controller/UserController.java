package com.mycompany.controller;


import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public String showUserList(Model model){
        List<User> listUser = service.listAll();
        model.addAttribute("listUsers",listUser);
        return "users";
    }

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Добавить нового пользователя");
        return "new";
    }

    @PostMapping("/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","Пользователь был добавлен");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,
                               Model model,
                               RedirectAttributes ra){
        try {
            User user = service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Изменить пользователя(ID: " + id + ")");
            return "edit";
        } catch (UserPrincipalNotFoundException e) {
           ra.addFlashAttribute("message","The user has been saved successfully");
            return "redirect:/users";
        }
    }

    @PostMapping("/edit")
    public String editUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","Пользователь был изменен");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try {
            service.delete(id);
            return "redirect:/users";
        } catch (UserPrincipalNotFoundException e) {
            ra.addFlashAttribute("message","The user has been saved successfully");
            return "redirect:/users";
        }
    }



}
