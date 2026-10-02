package in.coder.crudSpringBootDemo11.controller;


import in.coder.crudSpringBootDemo11.dto.CreateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.CreateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.entity.Student;
import in.coder.crudSpringBootDemo11.service.StudentService;
import jakarta.persistence.Id;
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

    @PostMapping("/create") // create student
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@RequestBody CreateStudentRequestDTO createStudentRequestDTO) {
        CreateStudentResponseDTO createdStudent= studentService.createdStudent(createStudentRequestDTO);
        if(createdStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdStudent);

    }

    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@RequestParam Long  id){
      CreateStudentResponseDTO  studentResp=studentService.getStudent(id);

      if(studentResp==null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // or return ResponseEntity.notFount().build();
      }

      return ResponseEntity.ok(studentResp);
    }

    @PutMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudents(){
        List<CreateStudentResponseDTO> studentResp=studentService.getAllStudent();
        if(studentResp==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentResp);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequestDTO student){
        UpdateStudentResponseDTO updatedStudent=studentService.updatesStudent(id , student);
        if(updatedStudent==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedStudent);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isdeleted=studentService.deleteStudent(id);

        if(!isdeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record NOT Found For Deletion");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PatchMapping("/soft-deleted")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id ){
        Boolean isDeleted=studentService.deletedStudentSoftly(id);

        if(!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record NOT Found For Deletion");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Softly Deleted Successfully");
    }

}
