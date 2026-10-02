package in.coder.crudSpringBootDemo11.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    private Long id;

    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18)
    private int age;

    @NotBlank(message = "Please provide a valid email Id.")
    @Email
    private String email;

    @NotNull(message = "RollNo cannot be null")
    private int rollNo;

    private String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
