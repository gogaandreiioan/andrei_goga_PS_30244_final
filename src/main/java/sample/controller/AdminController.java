package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sample.dto.UserDto;
import sample.service.user.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/goAdminHome")
    public String goHome(Model model) {
        return "administratorHome";
    }

    @GetMapping("/administratorHome")
    public String administratorIndex(Model model){
        return "administratorHome";
    }

    @GetMapping("/createUser")
    public String goToCreateForm(Model model){
        return "redirect:/userCreateForm";
    }

    @GetMapping("/findUser")
    public String goToFindForm(Model model){
        return "redirect:/userFindForm";
    }

    @GetMapping("/updateUser")
    public String goToUpdateForm(Model model){
        return "redirect:/userUpdateForm";
    }

    @GetMapping("/deleteUser")
    public String goToDeleteForm(Model model){
        return "redirect:/userDeleteForm";
    }

}
