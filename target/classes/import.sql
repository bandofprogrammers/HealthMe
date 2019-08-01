insert into ROLES(id,name) VALUES(1,'ROLE_SUPER_USER');
insert into ROLES(id,name) VALUES(2,'ROLE_ADMIN');
insert into ROLES(id,name) VALUES(3,'ROLE_PATIENT');
insert into ROLES(id,name) VALUES(4,'ROLE_DOCTOR');

insert into work_calendar(name) VALUES('2019');

insert into doctors(email,enabled,first_name,gender,last_name,password, pesel,phone_number,work_calendar_id) VALUES('test_doctor@test.com','true','Franciszek','Male','Dolas','$2a$10$fms1K8VFGJY624sDDkVAfe91l4Xfn6EwZ/MCNeIN6d7Sij3pzkxdK','789456123','75012097612',1);

insert into patients(email,enabled,first_name,gender,last_name,password, pesel,phone_number) values('test_patient@test.com','true','Jan','Male','Kowalski','$2a$10$uGY3XuF74nEfdvl41J5IMuu6Hw6La8ka3iQcIilRAqjLQgN325j4m','82032423172','123456789');

insert into admins(email,enabled,first_name,gender,last_name,password,phone_number) values('test_admin@test.com@test.com','true','Admin','Male','Admin','$2a$10$4nOuvkJLQwRBzrUxuZgI1u1P5U/vTJ7mSiQGDKkWTKhL7oOAqy9o.','123456789');
insert into DOCTOR_SPECIALIZATION(id,name) values(1,'Internist');
insert into DOCTOR_SPECIALIZATION(id,name) values(2,'Laryngologist');
insert into DOCTOR_SPECIALIZATION(id,name) values(3,'Cardiologist');


insert into DOCTORS_DOCTOR_SPECIALIZATION_LIST values(1,1);
insert into DOCTORS_DOCTOR_SPECIALIZATION_LIST values(1,2);

insert into doctors_roles(doctor_id,role_id) values(1,4);
insert into patients_roles(patient_id,role_id) values(1,3);
insert into admins_roles(admin_id,role_id) values(1,2);

insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2018-01-13T17:09:42','stay home','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2020-01-13T17:09:42','can go out','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2016-01-13T17:09:42','stay way from people','visit desc',1,1);
insert into visits(local_date_time,recomendations,visit_description,doctor_id,patient_id) VALUES('2019-07-30T17:09:42','rekomendacje','visit desc',1,1);
