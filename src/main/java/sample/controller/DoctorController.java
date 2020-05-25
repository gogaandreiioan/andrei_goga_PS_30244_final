package sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class DoctorController {


    @RequestMapping(value = "/goDoctorHome",  method = {RequestMethod.GET, RequestMethod.POST})
    public String goHome(Model model) {
        return "doctorHome";
    }

    @GetMapping("/seeHistory")
    public String seePastConsultations(Model model){
        return "redirect:/findConsultations";
    }

    @GetMapping("/addConsultationDescription")
    public String addDetailsToConsultations(Model model){
        return "redirect:/consultationUpdateForm";
    }

}
