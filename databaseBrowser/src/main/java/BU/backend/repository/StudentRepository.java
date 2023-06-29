package BU.backend.repository;

import BU.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


//
// interface: StudentRepository
//
// Description:
// This interface extends the
// {@link JpaRepository} interface with the entity class type
//
public interface StudentRepository extends JpaRepository<Student, Long> {


///////////////////////////////////////////////////////////////////
/// search (searchTerm)                                         ///
/// Input : data to be inserted                                 ///
/// Output: None                                                ///
/// Returns a list of students whose first or                   ///
// last name contains the search term                           ///
///                                                             ///
///////////////////////////////////////////////////////////////////
    @Query("select c from Student c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Student> search(@Param("searchTerm") String searchTerm);
}