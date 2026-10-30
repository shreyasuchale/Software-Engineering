import java.util.*;

class Student {
    private final String id;
    private String name;
    private String email;
    private String course;

    public Student(String id, String name, String email, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email + " | Course: " + course;
    }
}

class StudentManagementSystem {
    private final Map<String, String> userCredentials = new HashMap<>();
    private final Map<String, Student> studentDatabase = new HashMap<>();

    // Sprint 1 / XP Feature: User Registration
    public boolean registerUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        if (userCredentials.containsKey(username)) {
            return false;
        }
        userCredentials.put(username, password);
        return true;
    }

    // Sprint 1 / XP Feature: User Login
    public boolean loginUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return userCredentials.get(username).equals(password);
        }
        return false;
    }

    // Sprint 1 / XP Feature: Add Student
    public boolean addStudent(Student student) {
        if (student == null || studentDatabase.containsKey(student.getId())) {
            return false;
        }
        studentDatabase.put(student.getId(), student);
        return true;
    }

    // Sprint 2 / XP Feature: Search Student
    public Student searchStudent(String id) {
        return studentDatabase.get(id);
    }

    // Sprint 2 / XP Feature: Update Student
    public boolean updateStudent(String id, String newName, String newEmail, String newCourse) {
        Student student = studentDatabase.get(id);
        if (student != null) {
            student.setName(newName);
            student.setEmail(newEmail);
            student.setCourse(newCourse);
            return true;
        }
        return false;
    }

    // Sprint 2 / XP Feature: Delete Student
    public boolean deleteStudent(String id) {
        if (studentDatabase.containsKey(id)) {
            studentDatabase.remove(id);
            return true;
        }
        return false;
    }

    // Display all students
    public void displayAllStudents() {
        if (studentDatabase.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        studentDatabase.values().forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        // --- 1. Automated TDD Unit Tests (XP Practice) ---
        System.out.println("============================================");
        System.out.println("   RUNNING XP TEST-DRIVEN UNIT TESTS");
        System.out.println("============================================");
        runUnitTests();

        // --- 2. Interactive Application ---
        System.out.println("\n============================================");
        System.out.println("   STARTING STUDENT MANAGEMENT SYSTEM");
        System.out.println("============================================");
        runInteractiveApp();
    }

    private static void runUnitTests() {
        StudentManagementSystem sms = new StudentManagementSystem();

        // Test 1: User Registration
        boolean regTest = sms.registerUser("admin", "admin123");
        System.out.println("Test registerUser(): " + (regTest ? "PASSED" : "FAILED"));

        // Test 2: Valid Login
        boolean loginPassTest = sms.loginUser("admin", "admin123");
        System.out.println("Test loginUser() [Valid]: " + (loginPassTest ? "PASSED" : "FAILED"));

        // Test 3: Invalid Login
        boolean loginFailTest = !sms.loginUser("admin", "wrongpass");
        System.out.println("Test loginUser() [Invalid]: " + (loginFailTest ? "PASSED" : "FAILED"));

        // Test 4: Add Student
        Student s1 = new Student("S101", "Alice", "alice@test.com", "Computer Science");
        boolean addTest = sms.addStudent(s1);
        System.out.println("Test addStudent(): " + (addTest ? "PASSED" : "FAILED"));

        // Test 5: Search Student
        Student found = sms.searchStudent("S101");
        System.out.println(
                "Test searchStudent(): " + (found != null && found.getName().equals("Alice") ? "PASSED" : "FAILED"));

        // Test 6: Update Student
        boolean updateTest = sms.updateStudent("S101", "Alice Smith", "alice.s@test.com", "Data Science");
        System.out.println("Test updateStudent(): " + (updateTest ? "PASSED" : "FAILED"));

        // Test 7: Delete Student
        boolean deleteTest = sms.deleteStudent("S101");
        System.out.println("Test deleteStudent(): " + (deleteTest ? "PASSED" : "FAILED"));
    }

    private static void runInteractiveApp() {
        try (Scanner sc = new Scanner(System.in)) {
            StudentManagementSystem sms = new StudentManagementSystem();
            boolean loggedIn = false;
            
            OUTER:
            while (true) {
                if (!loggedIn) {
                    System.out.println("\n--- Authentication Menu ---");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.print("Enter choice: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1 ->                         {
                            System.out.print("Enter username: ");
                            String user = sc.nextLine();
                            System.out.print("Enter password: ");
                            String pass = sc.nextLine();
                            if (sms.registerUser(user, pass)) {
                                System.out.println("Registration successful! You can now log in.");
                            } else {
                                System.out.println("Registration failed. Username may already exist.");
                            }                              }
                        case 2 ->                         {
                            System.out.print("Enter username: ");
                            String user = sc.nextLine();
                            System.out.print("Enter password: ");
                            String pass = sc.nextLine();
                            if (sms.loginUser(user, pass)) {
                                loggedIn = true;
                                System.out.println("Login successful! Welcome, " + user + ".");
                            } else {
                                System.out.println("Invalid credentials. Try again.");
                            }                              }
                        case 3 -> {
                            System.out.println("Exiting application.");
                            break OUTER;
                        }
                        default -> {
                        }
                    }
                } else {
                    System.out.println("\n--- Student Management Dashboard ---");
                    System.out.println("1. Add Student");
                    System.out.println("2. Search Student");
                    System.out.println("3. Update Student");
                    System.out.println("4. Delete Student");
                    System.out.println("5. View All Students");
                    System.out.println("6. Logout");
                    System.out.print("Enter choice: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter Student ID: ");
                            String id = sc.nextLine();
                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter Course: ");
                            String course = sc.nextLine();
                            if (sms.addStudent(new Student(id, name, email, course))) {
                                System.out.println("Student added successfully.");
                            } else {
                                System.out.println("Error: Student ID already exists.");
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter Student ID to search: ");
                            String searchId = sc.nextLine();
                            Student found = sms.searchStudent(searchId);
                            if (found != null) {
                                System.out.println("Found: " + found);
                            } else {
                                System.out.println("Student not found.");
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter Student ID to update: ");
                            String updateId = sc.nextLine();
                            System.out.print("Enter New Name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter New Email: ");
                            String newEmail = sc.nextLine();
                            System.out.print("Enter New Course: ");
                            String newCourse = sc.nextLine();
                            if (sms.updateStudent(updateId, newName, newEmail, newCourse)) {
                                System.out.println("Student updated successfully.");
                            } else {
                                System.out.println("Student ID not found.");
                            }
                        }
                        case 4 -> {
                            System.out.print("Enter Student ID to delete: ");
                            String deleteId = sc.nextLine();
                            if (sms.deleteStudent(deleteId)) {
                                System.out.println("Student deleted successfully.");
                            } else {
                                System.out.println("Student ID not found.");
                            }
                        }
                        case 5 -> sms.displayAllStudents();
                        case 6 -> {
                            loggedIn = false;
                            System.out.println("Logged out successfully.");
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        }
    }
}
