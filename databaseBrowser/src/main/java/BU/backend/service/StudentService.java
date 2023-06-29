package BU.backend.service;

import BU.backend.entity.School;
import BU.backend.repository.SchoolRepository;
import BU.backend.entity.Student;
import BU.backend.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//
// Class: StudentService
//
// Description:
// This class represents the service layer of the Contact entity, responsible for handling
// business logic and data access operations related to contacts.
//
@Service
public class StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private StudentRepository studentRepository;
    private SchoolRepository schoolRepository;

    public StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }



//////////////////////////////////////////////////////////////////
/// findAll ()                                                 ///
/// Input :  Can be null or empty                              ///
/// Output: None                                               ///
/// Returns: List of Contact objects                           ///
//           representing all contacts.                        ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public List<Student> findAll() {
        return studentRepository.findAll();
    }






//////////////////////////////////////////////////////////////////
/// findAll (String used to filter the results.)               ///
/// Input :  Can be null or empty                              ///
/// Output: None                                               ///
/// Returns: List of Contact objects representing              ///
//           the filtered contacts.                            ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public List<Student> findAll(String strFilt) {
        if (strFilt == null || strFilt.isEmpty()) {
            return studentRepository.findAll();
        } else {
            return studentRepository.search(strFilt);
        }
    }

//////////////////////////////////////////////////////////////////
/// count ()                                                   ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the total number of contacts present              ///
//           in the system.                                    ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public long count() {
        return studentRepository.count();
    }


//////////////////////////////////////////////////////////////////
/// delete (contact)                                           ///
/// Input : data to be inserted                                ///
/// Output: None                                               ///
/// Returns: Deletes a given Contact object from the system    ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void delete(Student student) {
        studentRepository.delete(student);
    }


//////////////////////////////////////////////////////////////////
/// save (contact)                                             ///
/// Input : data to be inserted                                ///
/// Output: None                                               ///
/// Returns: Saves a given Contact object to the system.       ///
//           If the Contact object is null,                    ///
//           logs a warning message                            ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void save(Student student) {
        if (student == null) {
            LOGGER.log(Level.SEVERE, "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        studentRepository.save(student);
    }




//////////////////////////////////////////////////////////////////
/// populateTestData ()                                        ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Populates the system with test data               ///
//           if no data is present yet.                        ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    @PostConstruct
    public void populateTestData() {
        if (schoolRepository.count() == 0) {
            schoolRepository.saveAll(
                    Stream.of("bu", "nu")
                            .map(School::new)
                            .collect(Collectors.toList()));
        }
        if (studentRepository.count() == 0) {
            Random r = new Random(0);
            List<School> schools = schoolRepository.findAll();
            studentRepository.saveAll(
                    Stream.of("John Doe", "Brian O'Conner",
                                    "Dominik Toretto", "Tom Hanks", "Alejandro Gustavo", "Angel Gustavo",
                                    "Bradley Cooper", "Kristen Stewart", "Michael Jackson", "Ryann Cooper", "Jane Jackson",
                                    "Kelly Clarkson", "Paul Walker", "Katelyn Macdonald", "Mike Tyson")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Student student = new Student();
                                student.setFirstName(split[0]);
                                student.setLastName(split[1]);
                                student.setSchool(schools.get(r.nextInt(schools.size())));
                                student.setStatus(Student.AcademicStatus.values()[r.nextInt(Student.AcademicStatus.values().length)]);
                                String email = (student
                                        .getLastName() + "@" + student.getCompany().getName().replaceAll("[\\s-]", "") +
                                        ".edu").toLowerCase();
                                student.setEmail(email);
                                return student;
                            }).collect(Collectors.toList()));
        }
    }
}

