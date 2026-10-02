package in.coder.crudSpringBootDemo11.service;

import in.coder.crudSpringBootDemo11.dto.CreateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.CreateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.entity.Student;
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
        Student studentResp =studentRepository.save(createdstudent);
        CreateStudentResponseDTO newstudentResp=Map.mapToDTO(studentResp);
        return newstudentResp;
    }

    public CreateStudentResponseDTO getStudent(Long id){

        Optional<Student> studentResp=studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()){
            return Map.mapToDTO(studentResp.get());
        }

        return null;
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
        Optional<Student> findStudent=studentRepository.findByIdAndDeletedIsFalse(id);
        System.out.println(findStudent.get().getEmail());
        Student  newFindStudent= Map.mapToUpdateEntity(studentReq,findStudent.get());
        System.out.println(newFindStudent.getEmail());
        Student updatedStudent=studentRepository.save(newFindStudent);
        System.out.println(updatedStudent.getEmail());
        return Map.mapToUpdateDTO(updatedStudent);
    }

    public Boolean deleteStudent(Long id){
       boolean isStudent=studentRepository.existsById(id);

       if(isStudent){
           studentRepository.deleteById(id);
           return true;
       }
       return false;

    }

    public Boolean deletedStudentSoftly(Long id){
       Optional<Student> isDeletedSoft=studentRepository.findByIdAndDeletedIsFalse(id);

       if(isDeletedSoft.isEmpty()){
           return false;
       }

       Student studentToSave =isDeletedSoft.get();
       studentToSave.setDeleted(true);
       studentRepository.save(studentToSave);
       return true;
    }


}
