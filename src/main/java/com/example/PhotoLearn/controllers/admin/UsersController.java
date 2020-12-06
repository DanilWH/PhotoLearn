package com.example.PhotoLearn.controllers.admin;

import com.example.PhotoLearn.dto.UserDto;
import com.example.PhotoLearn.models.UserRoles;
import com.example.PhotoLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserDto> usersDto = this.userService.getAllAsDto();

        model.addAttribute("usersDto", usersDto);

        return "admin_templates/users";
    }

    @GetMapping("/user/{userId}/edit")
    public String editUser(
            @PathVariable Long userId,
            Model model
    ) {
        UserDto userDto = this.userService.getDtoById(userId);

        model.addAttribute("userDto", userDto);
        model.addAttribute("roles", UserRoles.values());

        return "admin_templates/edit_user";
    }

    @PostMapping("/user/{userId}/edit")
    public String updateUser(
            @PathVariable Long userId,
            @RequestParam Set<UserRoles> userRoles,
            Model model
    ) {
        this.userService.updateUserRoles(userId, userRoles);

        return "redirect:/admin/users";
    }

}
