package com.healthme.service.calendar;

import com.healthme.model.entity.doctorsCalendar.WorkHour;
import com.healthme.repository.WorkHourRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkCalendarService {
    
    @Autowired
    private WorkHourRepository workHourRepository;

    public JSONObject getAvailableTermsByDoctorIdAndDate(Long doctorId, String date) {

        JSONObject availableHours = new JSONObject().put("date",date);

        List<WorkHour> workHours = workHourRepository.getAvailableTermsByDoctorIdAndDate(doctorId, date);
        
        for(WorkHour workHour:workHours){
            if(workHour.getPatient()==null){
                JSONObject singleHour = new JSONObject()
                        .put("id",workHour.getId())
                        .put("hour",workHour.getHour());
                availableHours.put(String.valueOf(workHour.getId()),singleHour);
            }
        }

        return availableHours;
    }
}
