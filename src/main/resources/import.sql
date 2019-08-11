insert into roles(id,name) VALUES(1,'ROLE_SUPER_USER');
insert into roles(id,name) VALUES(2,'ROLE_ADMIN');
insert into roles(id,name) VALUES(3,'ROLE_PATIENT');
insert into roles(id,name) VALUES(4,'ROLE_DOCTOR');

insert into work_calendar(name) VALUES('2019');
insert into work_calendar(name) VALUES('2019');

insert into doctors(email,enabled,first_name,gender,last_name,password, pesel,phone_number,work_calendar_id,current_rating) VALUES('test_doctor@test.com','true','Franciszek','Male','Dolas','$2a$10$ILdmx3eYRsv./4GTIgE.D.M3qYXBDcVsBF4ViMUkI7KsHZ9PeG/N2','67042443299','75012097612',1,0.0);
insert into doctors(email,enabled,first_name,gender,last_name,password, pesel,phone_number,work_calendar_id,current_rating) VALUES('test_doctor2@test.com','true','Gregory','Male','House','$2a$10$XiYeyPndIdC0bJrZY48p2.IsJA9Cz4f/Tcg5BVMzX6/CpvallGyu.','58020465790','741852963',2,0.0);

Update work_calendar set doctor_id=1 where id=1;
Update work_calendar set doctor_id=2 where id=2;

insert into patients(email,enabled,first_name,gender,last_name,password, pesel,phone_number) values('test_patient@test.com','true','Jan','Male','Kowalski','$2a$10$uGY3XuF74nEfdvl41J5IMuu6Hw6La8ka3iQcIilRAqjLQgN325j4m','82032423172','123456789');

insert into admins(email,enabled,first_name,gender,last_name,password,phone_number) values('test_admin@test.com','true','Admin','Male','Admin','$2a$10$2FbSogr2l92nk0otRnm/QeVe085ISWvuF172CXc3l3TJBzmAaAj6y','123456789');
insert into doctor_specialization(name) values('Internist');
insert into doctor_specialization(name) values('Laryngologist');
insert into doctor_specialization(name) values('Cardiologist');


insert into doctors_doctor_specialization_list(doctor_id,doctor_specialization_list_id) values(1,1);
insert into doctors_doctor_specialization_list(doctor_id,doctor_specialization_list_id) values(1,2);
insert into doctors_doctor_specialization_list(doctor_id,doctor_specialization_list_id) values(2,3);

insert into doctors_roles(doctor_id,role_id) values(1,4);
insert into doctors_roles(doctor_id,role_id) values(2,4);
insert into patients_roles(patient_id,role_id) values(1,3);
insert into admins_roles(admin_id,role_id) values(1,2);

insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2018-01-13T17:09:42','stay home','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2020-01-13T17:09:42','can go out','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2016-01-13T17:09:42','stay away from people','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2019-08-11T17:18:42','rekomendacje','visit desc',1,1);


insert into work_day(date,doctor_id,work_calendar_id) VALUES('2019-08-08',1,1);
insert into work_calendar_days_of_work(work_calendar_id,days_of_work_id) VALUES(1,1);
insert into work_hour(hour,doctor_id,patient_id,work_day_id) VALUES('8:00',1,1,1);
insert into work_hour(hour,doctor_id,patient_id,work_day_id) VALUES('9:00',1,1,1);
insert into work_hour(hour,doctor_id,work_day_id) VALUES('10:00',1,1);
insert into work_hour(hour,doctor_id,work_day_id) VALUES('11:00',1,1);
insert into work_day_working_hours(work_day_id,working_hours_id) VALUES(1,1);
insert into work_day_working_hours(work_day_id,working_hours_id) VALUES(1,2);
