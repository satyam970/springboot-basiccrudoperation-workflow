package in.coder.crudSpringBootDemo11.map;

import in.coder.crudSpringBootDemo11.dto.CreateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.CreateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentRequestDTO;
import in.coder.crudSpringBootDemo11.dto.UpdateStudentResponseDTO;
import in.coder.crudSpringBootDemo11.entity.Student;

import java.time.LocalDateTime;

public class Map {

    public static Student mapToEntity(CreateStudentRequestDTO requestDTO,Student student) {

         student.setName(requestDTO.getName());
         student.setAge(requestDTO.getAge());
         student.setRollNo(requestDTO.getRollNo());
         student.setSubject(requestDTO.getSubject());
         student.setEmail(requestDTO.getEmail());
         student.setDeleted(false);
         student.setCreatedDate(LocalDateTime.now());
         student.setUpdatedDate(LocalDateTime.now());
         return student;

    }

    public static CreateStudentResponseDTO mapToDTO(Student student) {
         CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();
         createStudentResponseDTO.setId(student.getId());
         createStudentResponseDTO.setName(student.getName());
         createStudentResponseDTO.setAge(student.getAge());
         createStudentResponseDTO.setRollNo(student.getRollNo());
         createStudentResponseDTO.setSubject(student.getSubject());
         createStudentResponseDTO.setEmail(student.getEmail());
         createStudentResponseDTO.setCreatedDate(student.getCreatedDate());
         createStudentResponseDTO.setUpdatedDate(student.getUpdatedDate());
         return createStudentResponseDTO;
    }

     public static UpdateStudentResponseDTO  mapToUpdateDTO(Student student) {
          UpdateStudentResponseDTO createStudentResponseDTO = new UpdateStudentResponseDTO();
          createStudentResponseDTO.setId(student.getId());
          createStudentResponseDTO.setName(student.getName());
          createStudentResponseDTO.setEmail(student.getEmail());
          createStudentResponseDTO.setCreatedDate(student.getCreatedDate());
          createStudentResponseDTO.setAge(student.getAge());
          createStudentResponseDTO.setRollNo(student.getRollNo());
          createStudentResponseDTO.setSubject(student.getSubject());
          createStudentResponseDTO.setUpdatedDate(student.getUpdatedDate());
          return createStudentResponseDTO;
     }

     public static Student mapToUpdateEntity(UpdateStudentRequestDTO requestDTO, Student student) {

          student.setName(requestDTO.getName());
          student.setAge(requestDTO.getAge());
          student.setRollNo(requestDTO.getRollNo());
          student.setSubject(requestDTO.getSubject());
          student.setUpdatedDate(LocalDateTime.now());
          return student;

     }
}
