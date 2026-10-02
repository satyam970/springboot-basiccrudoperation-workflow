package in.coder.crudSpringBootDemo11.service;

import in.coder.crudSpringBootDemo11.dto.CreateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.CreateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.entity.Student;
import in.coder.crudSpringBootDemo11.exception.DuplicateResourceException;
import in.coder.crudSpringBootDemo11.exception.ResourceNotFoundException;
import in.coder.crudSpringBootDemo11.map.Map;
import in.coder.crudSpringBootDemo11.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createdStudent(CreateStudentRequestDTO studentReq) {
        // business logic perform
        Student createdstudent=Map.mapToEntity(studentReq,new Student());
        if(emailExists(createdstudent)){
            throw new DuplicateResourceException("Student with email:"+createdstudent.getEmail()+" already exists");
        }
        Student studentResp =studentRepository.save(createdstudent);
        CreateStudentResponseDTO newstudentResp=Map.mapToDTO(studentResp);
        return newstudentResp;
    }

    public CreateStudentResponseDTO getStudent(Long id){

       Student studentResp= studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with Id:"+id+" not found"));

       return Map.mapToDTO(studentResp);
    }

    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentResp=studentRepository.findByDeletedIsFalse();
        List<CreateStudentResponseDTO> newStudentResp=studentResp.stream()
                .map(student -> {
                    CreateStudentResponseDTO dto=new CreateStudentResponseDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setAge(student.getAge());
                    dto.setEmail(student.getEmail());
                    dto.setRollNo(student.getRollNo());
                    dto.setSubject(student.getSubject());
                    dto.setCreatedDate(student.getCreatedDate());
                    dto.setUpdatedDate(student.getUpdatedDate());
                    return dto;
                })
                .toList();
        return newStudentResp;
    }

    public UpdateStudentResponseDTO updatesStudent(Long id , UpdateStudentRequestDTO studentReq){
        Student findStudent=studentRepository
                                    .findByIdAndDeletedIsFalse(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("Student with Id:"+id+"not found"));
        Student  newFindStudent= Map.mapToUpdateEntity(studentReq,findStudent);
        Student updatedStudent=studentRepository.save(newFindStudent);
        return Map.mapToUpdateDTO(updatedStudent);
    }

    public void deleteStudent(Long id){
       Student studentToBeDeleted=studentRepository
               .findByIdAndDeletedIsFalse(id)
               .orElseThrow(() -> new ResourceNotFoundException("Student with Id:"+id+"not found"));
       studentRepository.delete(studentToBeDeleted);
    }

    public void deletedStudentSoftly(Long id){
        Student studentToBeDeleted=studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with Id:"+id+"not found"));

       studentToBeDeleted.setDeleted(true);
       studentRepository.save(studentToBeDeleted);
    }

    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }

}
