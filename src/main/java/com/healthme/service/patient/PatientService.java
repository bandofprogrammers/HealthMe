package com.healthme.service.patient;

import com.healthme.model.UserDto;
import com.healthme.model.entity.Admin;
import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.model.entity.Patient;
import com.healthme.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    public Patient registerNewPatientAccount(UserDto accountDto) throws NullPointerException {

        if (emailExists(accountDto.getEmail())) {
            throw new NullPointerException("There is an account with that email address: " + accountDto.getEmail());
        }
        Patient patient = new Patient();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        patient.setFirstName(accountDto.getFirstName());
        patient.setLastName(accountDto.getLastName());
        patient.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        patient.setPhoneNumber(accountDto.getPhoneNumber());
        patient.setEmail(accountDto.getEmail());
        patient.setGender(accountDto.getGender());
        patient.setPesel(accountDto.getPesel());
        patient.setEnabled("false");
        patient.setRoles(Arrays.asList(roleRepository.findByName("ROLE_PATIENT")));
        return patientRepository.save(patient);
    }

    private boolean emailExists(String email) {
        Patient patient = patientRepository.findByEmail(email);
        Doctor doctor = doctorRepository.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);

        if (patient != null ||
                admin != null ||
                doctor != null) {
            return true;
        }
        return false;
    }

    public JSONObject getDoctorListBySpecializationId(Long id) {

        DoctorSpecialization doctorSpecialization = doctorSpecializationRepository.getOne(id);
        List<Doctor> doctors = doctorRepository.findAllBySpecialization(doctorSpecialization);

        JSONObject doctorsData = new JSONObject();

        for (Doctor doctor : doctors) {
            JSONObject doctorData = new JSONObject()
                    .put("id", doctor.getId())
                    .put("firstName", doctor.getFirstName())
                    .put("lastName", doctor.getLastName())
                    .put("phoneNumber", doctor.getPhoneNumber())
                    .put("email", doctor.getEmail());
            doctorsData.put(String.valueOf(doctor.getId()), doctorData);
        }

        return doctorsData;
    }

    public Patient findOneByEmail(String email) {

        return  patientRepository.findByEmail(email);
    }
}
