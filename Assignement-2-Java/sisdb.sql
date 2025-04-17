-- create database SISDB;
use SISDB;

CREATE TABLE Students (
    student_id INT AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    date_of_birth DATE,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    CONSTRAINT Students_studentid_pk PRIMARY KEY (student_id),
    CONSTRAINT Students_email_uk UNIQUE (email),
    CONSTRAINT Students_phoneno_uk UNIQUE (phone_number)
);

CREATE TABLE Teacher (
    teacher_id INT AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    CONSTRAINT Teacher_teacherid_pk PRIMARY KEY (teacher_id),
    CONSTRAINT Teacher_email_uk UNIQUE (email)
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT,
    course_name VARCHAR(20) NOT NULL,
    credits INT DEFAULT 0,
    teacher_id INT,
    CONSTRAINT Courses_courseid_pk PRIMARY KEY (course_id),
    CONSTRAINT Courses_teacherid_fk FOREIGN KEY (teacher_id)
        REFERENCES Teacher (teacher_id) ON DELETE SET NULL,
    CHECK (credits > 0)
);
     
CREATE TABLE Enrollments(
	enrollment_id INT AUTO_INCREMENT,
	student_id INT,
	course_id INT,
	enrollment_date DATE DEFAULT (CURRENT_DATE),
	CONSTRAINT Enrollments_enrollmentid_pk PRIMARY KEY(enrollment_id),
	CONSTRAINT Enrollments_studentid_fk FOREIGN KEY(student_id) 
		REFERENCES Students(student_id) ON DELETE CASCADE,
	CONSTRAINT Enrollments_courseid_fk FOREIGN KEY(course_id) 
        REFERENCES Courses(course_id) ON DELETE CASCADE
     );
     
CREATE TABLE Payments(
    payment_id INT AUTO_INCREMENT,
    student_id INT,
    amount DECIMAL(10,2) DEFAULT 0,
    payment_date DATE DEFAULT (CURRENT_DATE-1),
    CONSTRAINT Payments_paymentid_pk PRIMARY KEY(payment_id),
    CONSTRAINT Payments_studentid_fk FOREIGN KEY(student_id) 
        REFERENCES Students(student_id) ON DELETE CASCADE,
    CHECK (amount>0)
);

INSERT INTO Students(first_name,last_name,date_of_birth,email,phone_number) VALUES
('Aarav','Sharma','2000-05-12','aarav.sharma@example.com','9876543210'),
('Ishita','Verma','2001-08-23','ishita.verma@example.com','9876543211'),
('Kabir','Patel','1999-11-15','kabir.patel@example.com','9876543212'),
('Ananya','Rao','2002-03-30','ananya.rao@example.com','9876543213'),
('Vivaan','Iyer','2000-12-05','vivaan.iyer@example.com','9876543214'),
('Sanya','Kapoor','2001-07-18','sanya.kapoor@example.com','9876543215'),
('Aditya','Menon','1998-09-22','aditya.menon@example.com','9876543216'),
('Meera','Joshi','2002-04-09','meera.joshi@example.com','9876543217'),
('Rohan','Pillai','2000-06-14','rohan.pillai@example.com','9876543218'),
('Diya','Nair','2001-01-27','diya.nair@example.com','9876543219');

INSERT INTO Teacher(first_name,last_name,email) VALUES
('Rajesh','Malhotra','rajesh.malhotra@example.com'),
('Sneha','Iyer','sneha.iyer@example.com'),
('Amit','Gupta','amit.gupta@example.com'),
('Priya','Nair','priya.nair@example.com'),
('Vikram', 'Sharma', 'vikram.sharma@example.com'),
('Anjali','Kapoor','anjali.kapoor@example.com'),
('Suresh','Menon','suresh.menon@example.com'),
('Neha','Joshi','neha.joshi@example.com'),
('Arjun','Verma','arjun.verma@example.com'),
('Meera','Rao','meera.rao@example.com');

INSERT INTO Courses(course_name,credits,teacher_id) VALUES
('Mathematics',4,1),
('Physics',3,2),
('Chemistry',3,3),
('Biology',4,4),
('Computer Science',5,5),
('History',2,6),
('English Literature',3,7),
('Economics',4,8),
('Psychology',3,9),
('Environmental Science',4,10);

ALTER TABLE Courses
MODIFY COLUMN course_name varchar(40); -- added this line as the 10th record's course name is large and at the same time to ensure the course names of max characters being added without errors

INSERT INTO Enrollments(student_id,course_id,enrollment_date) VALUES
(1,3,'2023-06-15'),
(2,5,'2023-07-10'),
(3,1,'2023-08-20'),
(4,4,'2023-09-05'),
(5,2,'2023-10-12'),
(6,6,'2023-11-25'),
(7,8,'2023-12-01'),
(8,7,'2024-01-18'),
(9,9,'2024-02-14'),
(10,10,'2024-03-08');

INSERT INTO Payments(student_id,amount,payment_date) VALUES
(1,5000.00,'2023-06-10'),
(2,4500.50,'2023-07-12'),
(3,6000.75,'2023-08-15'),
(4,3000.00,'2023-09-20'),
(5,5500.25,'2023-10-05'),
(6,4000.00,'2023-11-18'),
(7,3500.00,'2023-12-22'),
(8,4800.50,'2024-01-08'),
(9,5200.00,'2024-02-14'),
(10,4600.75,'2024-03-01');

-- task 2.1
INSERT INTO Students(first_name,last_name,date_of_birth,email,phone_number) VALUES 
("John","Doe",'1995-08-15',"john.doe@example.com",'1234567890');

-- task 2.2
INSERT INTO Enrollments (student_id,course_id) VALUES (11,5);

-- task 2.3
UPDATE Teacher set email="rajeshmalhotra@yahoo.com" where teacher_id=1;

-- task 2.4
DELETE FROM Enrollments WHERE student_id = 10 AND course_id = 10;

-- task 2.5
INSERT INTO Teacher(first_name,last_name,email) VALUES
('Zack','Den','zac.den@example.com');

-- task 2.6
UPDATE Courses set teacher_id=11 where course_id=6;

-- task 2.7
DELETE FROM Students where Student_id=10;

-- task 2.8
UPDATE Payments SET amount=1600 where payment_id=1;

-- task 3.1
INSERT INTO Payments(student_id,amount,payment_date) VALUES(9,5000,'2024-02-15');

SELECT s.student_id,s.first_name,s.email,sum(p.amount) as total_payment from payments p inner join 
students s on s.student_id=p.student_id  where s.student_id=9 group by s.student_id;

-- task 3.2
select c.course_id,c.course_name,count(e.student_id) as student_count from courses c inner join 
enrollments e on c.course_id=e.course_id group by c.course_id,c.course_name;

-- task 3.3
INSERT INTO Students(first_name,last_name,date_of_birth,email,phone_number) VALUES 
("Joe","Shang",'1995-08-14',"joeshang@gmail.com",'123456765');

select s.student_id,s.first_name from students s left join 
enrollments e on s.student_id=e.student_id where e.student_id is null;

-- task 3.4
select s.first_name,s.last_name,c.course_name from students s inner join 
enrollments e on s.student_id=e.student_id inner join courses c on c.course_id=e.course_id;

-- task 3.5
select concat(t.first_name,' ',t.last_name) as teacher_name,c.course_name from teacher t inner join 
courses c on t.teacher_id=c.teacher_id; 

-- task 3.6
select s.student_id,c.course_name,e.enrollment_date from students s inner join 
enrollments e on s.student_id=e.student_id inner join courses c on c.course_id=e.course_id
where c.course_name="Computer Science";

-- task 3.7
select concat(s.first_name,' ',s.last_name) as non_paying_student_name from students s left join 
payments p on s.student_id=p.payment_id where p.student_id is null;

-- task 3.8
select c.course_id,c.course_name from courses c left join enrollments e 
on c.course_id=e.course_id where e.course_id is null;

-- task 3.9
select e1.student_id, count(e2.course_id) as course_count from 
enrollments e1 join enrollments e2 on e1.student_id=e2.student_id 
group by (e1.student_id) having course_count>1;

-- task 3.10
select t.teacher_id,concat(t.first_name,' ',t.last_name) as teacher_name
from teacher t left join courses c on t.teacher_id=c.teacher_id 
where c.course_id is null;



-- task 4.1
select avg(student_count) as avg_stud_count from 
(select count(student_id) as student_count from enrollments e group by course_id) as stud_count_query;

-- task 4.2
select s.student_id,s.first_name from payments p inner join 
students s on s.student_id=p.student_id where 
amount=(select max(amount) from payments);

-- task 4.3

select count(course_id)as course_count,course_id from enrollments 
group by course_id order by course_count desc limit 1;

select course_id from (select count(course_id)as course_count,course_id from enrollments 
group by course_id) as ref_table group by course_id having (select count(course_id) from enrollments 
group by course_id) in (select max(course_count) as max_count from (select count(course_id)as course_count from enrollments 
group by course_id) as ref);


select c.course_id, c.course_name, course_count
from (select course_id, count(student_id) as course_count from enrollments
group by course_id) as course_counts
join courses c on course_counts.course_id=c.course_id
where course_count=(select max(course_count) from
(select count(student_id) as course_count 
from enrollments group by course_id) as max_count_table
);

-- SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrollment_count
-- FROM courses c
-- JOIN enrollments e ON c.course_id = e.course_id
-- GROUP BY c.course_id, c.course_name
-- HAVING COUNT(e.student_id) = (
--     SELECT MAX(course_count) 
--     FROM (
--         SELECT COUNT(student_id) AS course_count 
--         FROM enrollments 
--         GROUP BY course_id
--     ) AS subquery
-- );

-- task 4.4
select t.teacher_id,concat(t.first_name,t.last_name) as teacher_name,c.course_id,c.course_name,
(select sum(amount) from payments p join enrollments e on
e.student_id=p.student_id join courses c on c.course_id=e.course_id
where t.teacher_id=c.teacher_id) as tot_payment from teacher t inner join courses c 
on t.teacher_id=c.teacher_id;

-- task 4.5
SELECT s.student_id, s.first_name, s.last_name
FROM students s WHERE (SELECT COUNT(*) FROM enrollments e 
WHERE e.student_id = s.student_id) = (SELECT COUNT(*) FROM courses);

-- task 4.6
SELECT t.teacher_id, CONCAT(t.first_name, ' ', t.last_name) AS teacher_name FROM teacher t
WHERE t.teacher_id NOT IN (SELECT DISTINCT c.teacher_id FROM courses c);

-- task 4.7
SELECT AVG(YEAR(CURDATE()) - YEAR(date_of_birth)) AS average_age FROM students;

-- task 4.8
select c.course_id,c.course_name from courses c
where c.course_id not in (select e.course_id from enrollments e);

-- task 4.9
SELECT e.student_id,e.course_id,(SELECT SUM(p.amount) FROM payments p 
WHERE p.student_id = e.student_id) AS total_payment
FROM enrollments e JOIN students s ON e.student_id = s.student_id;

-- task 4.10
select student_id,count(student_id) as counts from payments group by student_id having counts>1;

select student_id, (select count(*) from payments p 
where p.student_id = s.student_id) as payment_count
from students s where (select count(*) 
from payments p where p.student_id = s.student_id) > 1;


-- task 4.11
select s.student_id,concat(s.first_name,' ',s.last_name) as student_name,sum(p.amount) as tot_payments 
from students s inner join payments p
on p.student_id=s.student_id group by s.student_id;

-- task 4.12
select c.course_id,c.course_name,count(e.student_id) as tot_students from courses c left join enrollments e
on c.course_id=e.course_id group by c.course_id; 

-- task 4.13
select s.student_id,concat(s.first_name,' ',s.last_name) as student_name, round(avg(p.amount),2) as avg_amt 
from students s inner join payments p on s.student_id=p.student_id group by s.student_id;