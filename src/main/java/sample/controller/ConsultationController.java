package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;
import sample.service.consultation.ConsultationService;
import sample.service.patient.PatientService;
import sample.service.user.UserService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ConsultationController {

    ConsultationService consultationService;
    UserService userService;
    PatientService patientService;

    @Autowired
    public ConsultationController(ConsultationService consultationService, UserService userService, PatientService patientService) {
        this.consultationService = consultationService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/consultationCreateForm")
    public String createForm(ConsultationDto consultationDto, PatientDto patientDto, UserDto userDto){
        return "/consultationCreateForm";
    }

    @PostMapping("/consultationCreateForm")
    public String createConsultation(@Valid ConsultationDto consultationDto, BindingResult bindingResult){
                UserDto userDto1 = userService.findByUsername(consultationDto.getDoctor().getUsername());
                if (userDto1 != null) {
                    if (!userDto1.getRole().equals("Doctor")) {
                        bindingResult.rejectValue("doctor", "error.consultationDto", "Please input a valid Doctor!");
                    }
                } else {
                    bindingResult.rejectValue("doctor", "error.consultationDto", "Please input a valid Doctor!");
                    return "/consultationCreateForm";
                }

                PatientDto patientDto1 = patientService.findByPersonalNumber(consultationDto.getPatient().getPersonalNumber());
                if (patientDto1 == null){
                    bindingResult.rejectValue("patient", "error.consultationDto", "Please input a valid Patient Personal Code!");
                    return "/consultationCreateForm";
                }

                if (consultationService.findByDoctorAndPatientAndDateOfConsultation(userDto1, patientDto1, consultationDto.getDateOfConsultation()) != null){
                    bindingResult.rejectValue("doctor", "error.consultationDto", "A consultation exists on that date!\nPlease Pick another date!");
                    return "/consultationCreateForm";
                }

                if (bindingResult.hasFieldErrors("dateOfConsultation")){
                    return "/consultationCreateForm";
        }


        consultationDto.setDoctor(userDto1);
        consultationDto.setPatient(patientDto1);
        consultationService.create(consultationDto);

        return "/secretarySuccess";
    }


    @GetMapping("/findConsultations")
    public String findConsultationsForm(PatientDto patientDto){
        return "/findConsultations";
    }

    @PostMapping("/findConsultations")
    public ModelAndView getAllConsultationsByPatient(PatientDto patientDto){
        List<ConsultationDto> consultationDtos = consultationService.findByPatient(patientDto);
        ModelAndView mav = new ModelAndView("foundConsultations");
        mav.addObject("consultationDtoList", consultationDtos);
        return mav;
    }

    @GetMapping("/consultationUpdateForm")
    public String addDescriptionForm(ConsultationDto consultationDto){
        return "/consultationUpdateForm";
    }

    @PostMapping("/consultationUpdateForm")
    public String addDescription(@Valid ConsultationDto consultationDto, BindingResult bindingResult){
        UserDto userDto1 = userService.findByUsername(consultationDto.getDoctor().getUsername());
        if (userDto1 != null) {
            if (!userDto1.getRole().equals("Doctor")) {
                bindingResult.rejectValue("doctor", "error.consultationDto", "Please input a valid Doctor!");
            }
        } else {
            bindingResult.rejectValue("doctor", "error.consultationDto", "Please input a valid Doctor!");
            return "/consultationUpdateForm";
        }

        PatientDto patientDto1 = patientService.findByPersonalNumber(consultationDto.getPatient().getPersonalNumber());
        if (patientDto1 == null){
            bindingResult.rejectValue("patient", "error.consultationDto", "Please input a valid Patient Personal Code!");
            return "/consultationUpdateForm";
        }

        ConsultationDto consultationDto1 = consultationService.findByDoctorAndPatientAndDateOfConsultation(userDto1, patientDto1, consultationDto.getDateOfConsultation());
        if (consultationDto1 != null) {
            if (consultationDto1.getDescription() == null) {
                consultationService.update(consultationDto1, consultationDto.getDescription());
            } else
                consultationService.update(consultationDto1, consultationDto1.getDescription().concat(" ").concat(consultationDto.getDescription()));
        }else {
            bindingResult.rejectValue("doctor", "error.consultationDto", "The consultation you want to update does not exist");
            return "/consultationCreateForm";
        }
        return "/doctorSuccess";
    }
}
