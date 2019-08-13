package com.healthme.service.calendar;

import com.healthme.model.entity.Patient;
import com.healthme.model.entity.doctorsCalendar.WorkHour;
import com.healthme.repository.PatientRepository;
import com.healthme.repository.WorkHourRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class WorkCalendarService {

    @Autowired
    private WorkHourRepository workHourRepository;

    @Autowired
    private PatientRepository patientRepository;

    public JSONObject getAvailableTermsByDoctorIdAndDate(Long doctorId, String date) {

        JSONObject availableHours = new JSONObject();

        List<WorkHour> workHours = workHourRepository.getAvailableTermsByDoctorIdAndDate(doctorId, Date.valueOf(date));

        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");

        for (WorkHour workHour : workHours) {
            if (workHour.getPatient() == null) {
                JSONObject singleHour = new JSONObject()
                        .put("hour", sdf.format(workHour.getHour()));
                availableHours.put(String.valueOf(workHour.getId()), singleHour);
            }
        }

        JSONObject timeTable = new JSONObject().put("hours", availableHours);

        return timeTable;
    }

    public void cancelVisitByHourId(Long id, Principal principal) {
        WorkHour workHour = workHourRepository.getOne(id);
        Patient patient = patientRepository.findByEmail(principal.getName());

        if (workHour.getPatient().getId() == patient.getId()) {
            workHour.setPatient(null);
            workHourRepository.save(workHour);
        }
    }
}
