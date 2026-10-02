package in.coder.crudSpringBootDemo11.controller;


import in.coder.crudSpringBootDemo11.dto.CreateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.CreateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.entity.Student;
import in.coder.crudSpringBootDemo11.service.StudentService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping // create student
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO) {
        CreateStudentResponseDTO createdStudent= studentService.createdStudent(createStudentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdStudent);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long  id){
      CreateStudentResponseDTO  studentResp=studentService.getStudent(id);
      return ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudents(){
        List<CreateStudentResponseDTO> studentResp=studentService.getAllStudent();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentResp);
    }

    @PutMapping
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequestDTO student){
        UpdateStudentResponseDTO updatedStudent=studentService.updatesStudent(id , student);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted successfully");
    }

    @PatchMapping("/soft-deleted")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id ){
        studentService.deletedStudentSoftly(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Softly Deleted Successfully");
    }

}
