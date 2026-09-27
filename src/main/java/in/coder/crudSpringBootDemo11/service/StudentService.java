package in.coder.crudSpringBootDemo11.service;

import in.coder.crudSpringBootDemo11.entity.Student;
import in.coder.crudSpringBootDemo11.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createdStudent(Student studentReq) {
        // business logic perform
        Student studentResp =studentRepository.save(studentReq);

        return studentResp;
    }

    public Student getStudent(Long id){

        Optional<Student> studentResp=studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()){
            return studentResp.get();
        }

        return null;
    }

    public List<Student> getAllStudent(){
        List<Student> studentResp=studentRepository.findByDeletedIsFalse();

        return studentResp;
    }

    public Optional<Student> updatesStudent(Long id , Student studentReq){
       Optional<Student>  isPresent=studentRepository.findByIdAndDeletedIsFalse(id);
       if(isPresent.isPresent()){
          Student updatedStudent=isPresent.get();
          updatedStudent.setName(studentReq.getName());
          updatedStudent.setEmail(studentReq.getEmail());
          updatedStudent.setAge(studentReq.getAge());
          updatedStudent.setSubject(studentReq.getSubject());
          updatedStudent.setRollNo(studentReq.getRollNo());
          updatedStudent.setDeleted(studentReq.getDeleted());

          studentRepository.save(updatedStudent);
          return Optional.of(updatedStudent);
       }
       return Optional.empty();
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
