package com.healthme.service.message;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.Message;
import com.healthme.model.entity.Patient;
import com.healthme.repository.DoctorRepository;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Message assignSenderReceiverClasses(Message messageToAdjust){
        Patient senderClass = patientRepository.getOne(messageToAdjust.getSenderId());
        messageToAdjust.setSenderClass(senderClass.getClass().getSimpleName().substring(0,senderClass.getClass().getSimpleName().indexOf("$")));
        Doctor receiverClass = doctorRepository.getOne(messageToAdjust.getReceiverId());
        messageToAdjust.setReceiverClass(receiverClass.getClass().getSimpleName().substring(0,receiverClass.getClass().getSimpleName().indexOf("$")));
        return messageToAdjust;
    }

}
