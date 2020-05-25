package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.PatientDto;
import sample.dto.SaloonDto;
import sample.dto.builder.HospitalizationDtoBuilder;
import sample.entity.Hospitalization;
import sample.entity.Saloon;
import sample.service.bed.BedService;
import sample.service.hospitalization.HospitalizationService;
import sample.service.patient.PatientService;
import sample.service.saloon.SaloonService;
import sample.utils.DateUtils;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class HospitalizationController {

    HospitalizationService hospitalizationService;
    PatientService patientService;
    BedService bedService;
    SaloonService saloonService;

    @Autowired
    public HospitalizationController(HospitalizationService hospitalizationService, PatientService patientService, BedService bedService, SaloonService saloonService) {
        this.hospitalizationService = hospitalizationService;
        this.patientService = patientService;
        this.bedService = bedService;
        this.saloonService = saloonService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/createHospitalization")
    public String goToCreateForm(Model model) { return "redirect:/hospitalizationCreateForm"; }

    @GetMapping("/hospitalizationCreateForm")
    public String displayCreateForm(Model model) { return "/hospitalizationCreateForm"; }

    @PostMapping("/hospitalizationCreateForm")
    public String createHospitalization(@RequestParam Map<String, String> field) {
        SaloonDto saloonDto = saloonService.findByNumber(Integer.parseInt(field.get("saloonNumber")));
        BedDto bedDto = bedService.findByNumberAndSaloon(Integer.parseInt(field.get("bedNumber")), saloonDto);
        PatientDto patientDto = patientService.findByPersonalNumber(Long.parseLong(field.get("patientPersonalNumber")));
        HospitalizationDto hospitalizationDto = new HospitalizationDtoBuilder().setBed(bedDto).setPatient(patientDto).build();
        hospitalizationService.create(hospitalizationDto);
        return "/secretarySuccess";
    }

    @GetMapping("/releaseHospitalization")
    public String goToReleaseForm(Model model) { return "redirect:/hospitalizationReleaseForm"; }

    @GetMapping("/hospitalizationReleaseForm")
    public String displayReleaseForm(HospitalizationDto hospitalizationDto) { return "/hospitalizationReleaseForm"; }

    @PostMapping("/hospitalizationReleaseForm")
    public String releaseHospitalization(HospitalizationDto hospitalizationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/hospitalizationReleaseForm";
        }
        hospitalizationDto.setReleaseDate(DateUtils.trim(new Date()));
        hospitalizationService.release(hospitalizationDto);
        return "/secretarySuccess";
    }

}
