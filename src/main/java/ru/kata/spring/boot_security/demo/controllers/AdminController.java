package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("rolesList", roleService.getAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("authorisedUser", (User) SecurityContextHolder.getContext()
                  .getAuthentication().getPrincipal());
        return "authenticationPage";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User userToEdit = userService.findById(id);
        String roleUser = (userToEdit.getStringRoles().contains("ROLE_USER") ? "on" : null);
        String roleAdmin = (userToEdit.getStringRoles().contains("ROLE_ADMIN") ? "on" : null);

        model.addAttribute("userToEdit", userService.findById(id));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("roleAdmin", roleAdmin);
        return "adminController/edit";
    }

    @PutMapping("/{id}")  // edit user
    public String update(@ModelAttribute("user") User user) {
        System.out.println("....ОТЛАДКА! " + user.toString());
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}
