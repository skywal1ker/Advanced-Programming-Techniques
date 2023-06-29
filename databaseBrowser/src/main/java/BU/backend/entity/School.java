package BU.backend.entity;

import jakarta.persistence.*;
import java.util.LinkedList;
import java.util.List;





//
// Class: School
//
// Description:
// The Company class represents a school entity in the system.
// It extends the AbstractTable class and contains fields for the company name and a list of students.
//
@Entity
public class School extends AbstractTable {

    /* The name of the company. */
    private String name;

    /* The list of students of the school. Uses eager fetching to ensure that all students are loaded with the school. */
    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
    private List<Student> students = new LinkedList<>();

    /* Default constructor for the school class. */
    public School() {
    }


    /* Constructor for the School class that sets the name of the school.
     @param name the name of the school.  */
    public School(String name) {
        setName(name);
    }


//////////////////////////////////////////////////////////////////
/// getName (name)                                             ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Returns the name of the school.                  ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }


//////////////////////////////////////////////////////////////////
/// setName (name)                                             ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the name of the school.                      ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setName(String name) {
        this.name = name;
    }


//////////////////////////////////////////////////////////////////
/// getStudents ()                                             ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the list of students of the school.              ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public List<Student> getStudents() { return students; }
}