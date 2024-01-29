
package courseregister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
enum Schedule {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private Schedule schedule;
    private int enrollments;

    public Course(String courseCode, String title, String description, int capacity, Schedule schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrollments = 0;
    }
    

    public boolean isFull() {
        return enrollments >= capacity;
    }

    public void registerStudent() {
        if (!isFull()) {
            enrollments++;
        }
    }

    public void dropStudent() {
        if (enrollments > 0) {
            enrollments--;
        }
    }

     @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", schedule=" + schedule +
                ", enrollments=" + enrollments +
                '}';
    }

  
   
    public int getEnrollments() {
       return enrollments;
    }
     public int getCapacity() {
        return capacity;
    }
    public String getCourseCode() {
        return courseCode;
    }

    
}
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", registeredCourses=" + registeredCourses +
                '}';
    }

   
}

class CourseDatabase {
    private Map<String,Course> courses;

    public CourseDatabase() {
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
       courses.put((String) course.getCourseCode(), course);
    }

    public void removeCourse(Course course) {
        courses.remove(course.getCourseCode());
    }
    public Course getCourse(int index) {
        return new ArrayList<>(courses.values()).get(index);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }
    
    
    public void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
            System.out.println("Available slots: "+(course.getCapacity() - course.getEnrollments()));
            
        }
        
    }
    
}

class StudentDatabase {
    private Map<String,Student> students;

    public StudentDatabase() {
        students = new HashMap<>();
    }

    public void addStudent(Student student) {
         students.put(student.getStudentId(), student);
    }

    public void removeStudent(Student student) {
        students.remove(student.getStudentId());
    }
    public Student getStudent(int index) {
        return new ArrayList<>(students.values()).get(index);
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
    public void listStudents() {
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    public void registerStudent(Student student, Course course) {
        if (!course.isFull()) {
            student.registerCourse(course);
            course.registerStudent();
        }
    }

    public void dropCourse(Student student, Course course) {
        student.dropCourse(course);
        course.dropStudent();
    }
}


public class CourseRegister {

   
    public static void main(String[] args) {
        CourseDatabase courseDatabase = new CourseDatabase();
        courseDatabase.addCourse(new Course("CS101", "Introduction to Computer Science", "Learn Java programming", 30, Schedule.MONDAY));
        courseDatabase.addCourse(new Course("MATH101", "Applied Mathematics I", "Learn derivatives and integrals", 25, Schedule.TUESDAY));

       
        StudentDatabase studentDatabase = new StudentDatabase();
        studentDatabase.addStudent(new Student("12345", "Emily Geller"));
        studentDatabase.addStudent(new Student("67890", "Daniel Andrews"));

       
        System.out.println("Available courses:");
        courseDatabase.listCourses();

        
        System.out.println("\nRegistering students for courses...");
        studentDatabase.registerStudent(studentDatabase.getStudent(0), courseDatabase.getCourse(0));
        studentDatabase.registerStudent(studentDatabase.getStudent(1), courseDatabase.getCourse(1));

        
        System.out.println("\nAvailable courses after registration:");
        courseDatabase.listCourses();

        
        System.out.println("\nRegistered students and their courses:");
        studentDatabase.listStudents();

        
        System.out.println("\nDropping a course for a student...");
        studentDatabase.dropCourse(studentDatabase.getStudent(0), courseDatabase.getCourse(0));

        
        System.out.println("\nRegistered students and their courses after dropping a course:");
        studentDatabase.listStudents();
    }


}



    

  

  
