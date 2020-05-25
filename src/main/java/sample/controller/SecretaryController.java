package sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecretaryController {

    @GetMapping("/goSecretaryHome")
    public String goHome(Model model) {
        return "secretaryHome";
    }

    @GetMapping("/createPatient")
    public String goToCreateForm(Model model){
        return "redirect:/patientCreateForm";
    }

    @GetMapping("/updatePatient")
    public String goToUpdateForm(Model model){
        return "redirect:/patientUpdateForm";
    }

    @GetMapping("/findPatient")
    public String goToFindForm(Model model){
        return "redirect:/patientFindForm";
    }

    @GetMapping("/secretaryHome")
    public String secretaryIndex(Model model){
        return "secretaryHome";
    }

    @GetMapping("/makeAppointment")
    public String makeAppointment(Model model){
        return "redirect:/consultationCreateForm";
    }

}
