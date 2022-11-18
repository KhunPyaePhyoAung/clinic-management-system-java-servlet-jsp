USE `clinic_management_system_db`;

INSERT INTO `doctor_specialist` (`name`, `description`) VALUES
('Allergist', 'specializes in determining food and environmental allergies'),
('Anesthesiologist', 'specializes in pain prevention during surgery'),
('Cardiologist', 'heart specialist'),
('Dentist', 'tooth specialist'),
('Dermatologist', 'skin specialist'),
('Fertility specialist', 'helps people who have difficulty getting pregnant'),
('Massage therapist', 'specializes in muscle relaxation'),
('Naturopath', 'specializes in natural cures and remedies'),
('Neurologist', 'brain specialist'),
('Obstetrician', 'specialist for pregnant women'),
('Oncologist', 'tumour specialist, including cancer'),
('Ophthalmologist', 'specializes in eye diseases'),
('Pediatrician', 'specialist for babies and children'),
('Psychiatrist', 'specialist in mental health');


INSERT INTO `user` (`name`, `username`, `password`, `email`, `phone`, `date_of_birth`, `gender`, `role`, `status`) VALUES 
('U Maung Maung Thant', 'mgmgthant', 'mgmgthant', 'mgmgthant@gmail.com', '09987654878', '1994-02-12', 'MALE', 'DOCTOR', 'ACTIVE'),
('U Kyi Phyo', 'kyiphyo', 'kyiphyo', 'kyiphyo@gmail.com', '09768956543', '1992-12-01', 'MALE', 'DOCTOR', 'ACTIVE'),
('Daw Myint Khin', 'myintkhin', 'myintkhin', 'myintkhin@gmail.com', '09987634788', '1995-03-22', 'FEMALE', 'DOCTOR', 'ACTIVE'),
('U Kyaw Thiha', 'kyawthiha', 'kyawthiha', 'kyawthiha@gmail.com', '09246877651', '1993-11-11', 'MALE', 'DOCTOR', 'ACTIVE'),
('Daw Mya Mya Aye', 'myamyaaye', 'myamyaaye', 'myamyaaye@gmail.com', '09982233651', '1995-10-30', 'FEMALE', 'DOCTOR', 'ACTIVE'),
('Daw San Thida', 'santhida', 'santhida', 'santhida@gmail.com', '09256876554', '1986-05-12', 'FEMALE', 'DOCTOR', 'ACTIVE'),
('Daw Moe Htet San', 'moehtetsan', 'moehtetsan', 'moehtetsan@gmail.com', '09798766760', '1976-01-02', 'FEMALE', 'DOCTOR', 'ACTIVE');


INSERT INTO `address` (`user_id`, `street`, `city`, `state`, `country`) VALUES 
(1, '12 Street', 'Mandalay', 'Mandalay', ''),
(2, 'Mingalar Street', 'Yangon', 'Yangon', ''),
(3, '6 Street', 'Toungoo', 'Bago', ''),
(4, 'Aung Zayya Street', 'Pyinmana', 'Naypyidaw', ''),
(5, 'Thitsar Street', 'Yangon', 'Yangon', ''),
(6, 'Parami Street', 'Bago', 'Bago', ''),
(7, 'Htin Kyaw Street', 'Yangon', 'Yangon', '');

INSERT INTO `doctor` (`id`, `specialist_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7);