CREATE DATABASE SISDB;
USE SISDB;

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
('Aarav','Sharma','2000-05-12','aarav.sharma@gmail.com','9876543210'),
('Ishita','Verma','2001-08-23','ishita.verma@gmail.com','9876543211'),
('Kabir','Patel','1999-11-15','kabir.patel@gmail.com','9876543212'),
('Ananya','Rao','2002-03-30','ananya.rao@gmail.com','9876543213'),
('Vivaan','Iyer','2000-12-05','vivaan.iyer@gmail.com','9876543214'),
('Sanya','Kapoor','2001-07-18','sanya.kapoor@gmail.com','9876543215'),
('Aditya','Menon','1998-09-22','aditya.menon@gmail.com','9876543216'),
('Meera','Joshi','2002-04-09','meera.joshi@gmail.com','9876543217'),
('Rohan','Pillai','2000-06-14','rohan.pillai@gmail.com','9876543218'),
('Diya','Nair','2001-01-27','diya.nair@gmail.com','9876543219');

INSERT INTO Teacher(first_name,last_name,email) VALUES
('Rajesh','Malhotra','rajesh.malhotra@gmail.com'),
('Sneha','Iyer','sneha.iyer@gmail.com'),
('Amit','Gupta','amit.gupta@gmail.com'),
('Priya','Nair','priya.nair@gmail.com'),
('Vikram', 'Sharma', 'vikram.sharma@gmail.com'),
('Anjali','Kapoor','anjali.kapoor@gmail.com'),
('Suresh','Menon','suresh.menon@gmail.com'),
('Neha','Joshi','neha.joshi@gmail.com'),
('Arjun','Verma','arjun.verma@gmail.com'),
('Meera','Rao','meera.rao@gmail.com');

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
MODIFY COLUMN course_name varchar(40);  -- added this line as the 10th record's course name is         large and at the same time to ensure the course names of max characters being added without errors

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

INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number)  VALUES ("John","Doe",'1995-08-15',"john.doe@gmail.com",'1234567890');
  
INSERT INTO Enrollments (student_id, course_id) VALUES (11,5);
   
UPDATE Teacher set email="rajeshmalhotra@yahoo.com" where teacher_id=1;

DELETE FROM Enrollments WHERE student_id = 10 AND course_id = 10;
  
INSERT INTO Teacher (first_name, last_name, email) VALUES
('Zack', 'Den', 'zac.den@example.com');    
 
UPDATE Courses set teacher_id=11 where course_id=6; 
      
DELETE FROM Students where Student_id=10;
      
UPDATE Payments SET amount=1600 where payment_id=1;
        
INSERT INTO Payments(student_id,amount,payment_date) VALUES(9,5000,'2024-02-15');  -- inserted this line for output purpose

SELECT s.student_id, s.first_name, s.email, SUM(p.amount) AS total_payment FROM payments p INNER JOIN students s ON s.student_id=p.student_id  WHERE s.student_id=9 GROUP BY s.student_id; -- answer

SELECT c.course_id, c.course_name, COUNT(e.student_id) AS student_count FROM courses c INNER JOIN enrollments e ON c.course_id=e.course_id  GROUP BY c.course_id, c.course_name;
 
INSERT INTO Students (first_name, last_name, date_of_birth,
Email, phone_number) VALUES ("Joe","Shang",'1995-08-14',"joeshang@gmail.com",'123456765');
            
SELECT s.student_id, s.first_name FROM students s LEFT JOIN   enrollments e ON s.student_id=e.student_id WHERE e.student_id IS NULL;
                 
SELECT s.first_name, s.last_name, c.course_name FROM students  s INNER JOIN enrollments e ON s.student_id=e.student_id INNER JOIN courses c ON  c.course_id=e.course_id;
                        
SELECT CONCAT (t.first_name,'  ',t.last_name) AS teacher_name, c.course_name FROM teacher t INNER JOIN courses c ON t.teacher_id=c.teacher_id;
                        
SELECT s.student_id, c.course_name, e.enrollment_date FROM students s INNER JOIN enrollments e ON s.student_id=e.student_id INNER JOIN courses c ON c.course_id=e.course_id WHERE c.course_name="Computer Science";
                          
SELECT CONCAT (s.first_name, '  ', s.last_name) AS non_paying_student_name FROM students s LEFT JOIN payments p ON s.student_id=p.payment_id WHERE p.student_id IS NULL;
                          
SELECT c.course_id, c.course_name FROM courses c LEFT JOIN enrollments e ON c.course_id=e.course_id WHERE e.course_id IS NULL;
                           
SELECT e1.student_id, COUNT(e2.course_id) as course_count FROM enrollments e1  JOIN  enrollments e2 ON e1.student_id=e2.student_id GROUP BY (e1.student_id) HAVING course_count>1;
                            
SELECT  t.teacher_id, CONCAT (t.first_name,' ',t.last_name) AS teacher_name FROM teacher t LEFT JOIN courses c ON t.teacher_id=c.teacher_id WHERE c.course_id IS NULL;
                            
SELECT AVG(student_count) AS avg_stud_count FROM 
(SELECT COUNT(student_id) AS student_count FROM enrollments e group by course_id) AS stud_count_query;

SELECT s.student_id,s.first_name FROM payments p INNER JOIN 
students s ON s.student_id=p.student_id WHERE amount=(SELECT MAX(amount) FROM payments);


SELECT c.course_id,c.course_name,course_count
FROM (SELECT course_id,COUNT(student_id) AS course_count FROM enrollments GROUP BY course_id) AS course_counts
JOIN courses c ON course_counts.course_id=c.course_id
WHERE course_count=(SELECT MAX(course_count) FROM
(SELECT COUNT(student_id) AS course_count FROM enrollments 
GROUP BY course_id) AS max_count_table);

SELECT t.teacher_id,CONCAT(t.first_name,t.last_name) as teacher_name,c.course_id,c.course_name, (SELECT SUM(amount) FROM payments p JOIN enrollments e ON e.student_id=p.student_id JOIN courses c ON c.course_id=e.course_id WHERE t.teacher_id=c.teacher_id) AS tot_payment FROM TEACHER t INNER JOIN courses c ON t.teacher_id=c.teacher_id;
 
SELECT s.student_id,s.first_name,s.last_name
FROM students s WHERE (SELECT COUNT(*) FROM enrollments e 
WHERE e.student_id=s.student_id) = (SELECT COUNT(*) FROM courses);


SELECT t.teacher_id,CONCAT(t.first_name, '  ', t.last_name) AS teacher_name FROM teacher t WHERE t.teacher_id NOT IN (SELECT DISTINCT c.teacher_id FROM courses c);
    
    
SELECT AVG(YEAR(CURDATE())-YEAR(date_of_birth)) AS average_age FROM students;
     
SELECT c.course_id,c.course_name FROM courses c  WHERE c.course_id NOT IN (SELECT e.course_id FROM enrollments e);
     
SELECT e.student_id,e.course_id,(SELECT SUM(p.amount) FROM payments p WHERE p.student_id=e.student_id) AS total_payment FROM enrollments e JOIN students s ON e.student_id=s.student_id;
               
               
               
SELECT student_id,(SELECT COUNT(*) FROM payments p WHERE p.student_id=s.student_id) AS payment_count FROM students s WHERE (SELECT COUNT(*) FROM payments p WHERE p.student_id=s.student_id) > 1;
           
SELECT s.student_id,CONCAT(s.first_name,'  ',s.last_name) AS student_name,SUM(p.amount) AS tot_payments FROM students s INNER JOIN payments p ON p.student_id=s.student_id GROUP BY s.student_id;
           
SELECT c.course_id,c.course_name,COUNT(e.student_id) AS   tot_students FROM courses c LEFT JOIN enrollments e ON c.course_id=e.course_id GROUP BY c.course_id;
           
SELECT s.student_id,CONCAT(s.first_name,'  ',s.last_name) AS student_name,ROUND(AVG(p.amount),2) AS avg_amt 
FROM students s INNER JOIN payments p ON s.student_id = p.student_id GROUP BY s.student_id;