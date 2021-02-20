package services;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is already present!");
        }
        studentRepository.save(student);
        System.out.println("student  : " + student);
    }

    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Id doesn't exists!");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("student with id : " + id + " doesn't exists!"));

            if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
                student.setName(name);
            }

            if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

                Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                if (studentOptional.isPresent()) {
                    throw new IllegalStateException("email already taken!");
                }

                student.setEmail(email);
            }
    }
}
