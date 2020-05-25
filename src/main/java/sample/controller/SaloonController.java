package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.dto.SaloonDto;
import sample.dto.UserDto;
import sample.entity.Saloon;
import sample.service.saloon.SaloonService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SaloonController {

    SaloonService saloonService;

    @Autowired
    public SaloonController(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @GetMapping("/createSaloon")
    public String goToCreateForm(Model model) { return "redirect:/saloonCreateForm"; }

    @GetMapping("/saloonCreateForm")
    public String displayCreateForm(SaloonDto saloonDto) { return "/saloonCreateForm"; }

    @PostMapping("/saloonCreateForm")
    public String createSaloon(@Valid SaloonDto saloonDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/saloonCreateForm";
        }
        saloonService.create(saloonDto);
        return "/adminSuccess";
    }

    @GetMapping("/deleteSaloon")
    public String goToDeleteForm(Model model) { return "redirect:/saloonDeleteForm"; }

    @GetMapping("/saloonDeleteForm")
    public String displayDeleteForm(SaloonDto saloonDto) {
        return "/saloonDeleteForm";
    }

    @PostMapping("/saloonDeleteForm")
    public String deleteSaloon(@RequestParam Map<String, String> field){
        saloonService.deleteByNumber(Integer.parseInt(field.get("number")));
        return "/adminSuccess";
    }
}
