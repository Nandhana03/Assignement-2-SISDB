package com.hexaware.SIS.main;

import com.hexaware.SIS.dao.CourseDAO;
import com.hexaware.SIS.dao.impl.CourseDAOImpl;
import com.hexaware.SIS.entity.Course;
import com.hexaware.SIS.entity.Teacher;

import java.util.List;

public class CourseDAOTest {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAOImpl();

        try {
         
            Teacher teacher = new Teacher(1, "Dr. Stella", "James", "stella.james@university.com");
            Course course = new Course("Artificial Intelligence", 4,teacher.getTeacherId()); 
            courseDAO.insertCourse(course);

            // 🔁 Update
            Teacher updatedTeacher = new Teacher(1, "Dr. Stella", "James", "stella.james@university.com");
            Course updatedCourse = new Course(1, "Machine Learning", 4, updatedTeacher);
            courseDAO.updateCourse(updatedCourse);


            Course fetched = courseDAO.getCourseById(1);
            System.out.println("Fetched Course: " + fetched);

            List<Course> allCourses = courseDAO.getAllCourses();
            allCourses.forEach(System.out::println);
            
            courseDAO.deleteCourse(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
