package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.notification.Message;
import sample.notification.OutputMessage;
import sample.service.patient.PatientService;
import sample.service.user.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController
{
    @Autowired
    PatientService patientService;

    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/messages")
    public OutputMessage send(@DestinationVariable("topic") String topic, Message message) throws Exception {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(message.getDateOfConsultation());
        String patientName = patientService.findByPersonalNumber(Long.parseLong(message.getPatientPersonalNumber())).getName();
        return new OutputMessage(patientName, message.getDoctorUsername(), date);
    }

    @RequestMapping("/doctorHome")
    public String goDoctor(){
        return "doctorHome";
    }

}
