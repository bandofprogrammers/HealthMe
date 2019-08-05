package com.healthme.repository;

import com.healthme.model.entity.Doctor;
import com.healthme.model.entity.DoctorSpecialization;
import com.healthme.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.email=:email")
    Doctor findByEmail(@Param("email") String email);

    @Query("SELECT d FROM Doctor d WHERE :role MEMBER OF d.roles")
    List<Doctor> findAllDoctors(@Param("role") Role role);

    @Query("SELECT d FROM Doctor d WHERE :specialization MEMBER OF d.doctorSpecializationList")
    List<Doctor> findAllBySpecialization(@Param("specialization") DoctorSpecialization specialization);
    @Query(value = "SELECT doctors.current_rating,doctors.work_calendar_id,doctors.pesel,doctors.password,doctors.enabled,doctors.gender," +
            "doctors.id,doctors.first_name,doctors.last_name,doctors.phone_number,doctors.email from doctors JOIN  " +
            "doctors_doctor_specialization_list ON doctors_doctor_specialization_list.doctor_id=doctors.id " +
            "where doctor_specialization_list_id=1",nativeQuery = true)
    List<Doctor> findAllBySpecInternistWithRating();
}
