package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class IndexController {
    @Qualifier("userServiceImplWithRepository")
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String indexPage(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/index";
    }

    @GetMapping("/create")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "users/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return ("redirect:/users");
    }

    @GetMapping("/detailed_show")
    public String getDetailedShowAboutUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/detailed_show";
    }

    @GetMapping("/{id}")
    public String getDetailedShowAboutUserByPathVariable(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/edit")
    public String editUserPage(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/users/detailed_show";
    }
}
