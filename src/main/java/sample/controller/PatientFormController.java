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
import org.springframework.web.servlet.ModelAndView;
import sample.dto.PatientDto;
import sample.report.CSVStrategy;
import sample.report.PDFStrategy;
import sample.service.patient.PatientService;
import sample.service.report.ReportService;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PatientFormController {

    PatientService patientService;
    ReportService reportService;

    @Autowired
    public PatientFormController(PatientService patientService, ReportService reportService) {
        this.patientService = patientService;
        this.reportService = reportService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/patientCreateForm")
    public String createForm(PatientDto patientDto){
        return "/patientCreateForm";
    }

    @PostMapping("/patientCreateForm")
    public String createPatient(@Valid PatientDto patientDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/patientCreateForm";
        }
        patientService.create(patientDto);
        return "/secretarySuccess";
    }


    @GetMapping("/patientUpdateForm")
    public String updateForm(Model model){
        return "/patientUpdateForm";
    }

    @PostMapping("/patientUpdateForm")
    public String updatePatient (@RequestParam Map<String, String> field){
        PatientDto patientDto = patientService.findByPersonalNumber(Long.parseLong(field.get("personalCode")));
        patientService.update(patientDto, field.get("newAddress"));
        return "/secretarySuccess";
    }

    @GetMapping("/patientFindForm")
    public String findForm(Model model){
        return "/patientFindForm";
    }

    @PostMapping("/patientFindForm")
    public ModelAndView findPatient(@RequestParam Map<String, String> field){
        PatientDto patientDto = patientService.findByPersonalNumber(Long.parseLong(field.get("personalNumber")));
        ModelAndView mav = new ModelAndView("patientFound");
        if (patientDto != null){
            mav.addObject("patient", patientDto);
        }
        return mav;
    }

    @GetMapping("/getAllPatients")
    public ModelAndView findAllPatients(){
        List<PatientDto> patientDto = patientService.findAll();
        ModelAndView mav = new ModelAndView("foundPatients");
        if (patientDto != null){
            mav.addObject("patientDtoList", patientDto);
        }
        return mav;
    }

    @GetMapping("/printCSVReport")
    public String printCSVReport() {
        List<PatientDto> patientDtos = patientService.findAll();
        reportService.setReportStrategy(new CSVStrategy());
        try {
            reportService.generateReport(patientDtos);
            return "/secretarySuccess";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/secretaryHome";
    }

    @GetMapping("/printPDFReport")
    public String printPDFReport() {
        List<PatientDto> patientDtos = patientService.findAll();
        reportService.setReportStrategy(new PDFStrategy());
        try {
            reportService.generateReport(patientDtos);
            return "/secretarySuccess";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/secretaryHome";
    }
}

