package BU.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


//
// Class: Student
//
// Description:
// The Student class represents a contact entity in the system.
// It extends the AbstractTable class and contains fields for the first name, last name, school, email, and academicStatus.
// It also implements the Cloneable interface to enable creating copies of Student objects.
//
@Entity
public class Student extends AbstractTable implements Cloneable {

    /* The status of the contact, can be FullTime or PartTime. */
    public enum AcademicStatus {
        FullTime, PartTime
    }


    /*The first name of the student. Cannot be null or empty. */
    @NotNull
    @NotEmpty
    private String firstName = "";

    /* The last name of the student. Cannot be null or empty. */
    @NotNull
    @NotEmpty
    private String lastName = "";

    /* The company the student belongs to. Uses a many-to-one relationship and a join column named "company_id".  */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private School school;

    /* The status of the school. Uses the Status enum and cannot be null. */
    @Enumerated(EnumType.STRING)
    @NotNull
    private Student.AcademicStatus acaStat;

    /*The email address of the student. Must be a valid email and cannot be null or empty. */
    @Email
    @NotNull
    @NotEmpty
    private String email = "";


//////////////////////////////////////////////////////////////////
/// getEmail ()                                                ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the email address of the student.                 ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public String getEmail() {
        return email;
    }


//////////////////////////////////////////////////////////////////
/// setEmail (email)                                           ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the email address of the student.            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setEmail(String email) {
        this.email = email;
    }


//////////////////////////////////////////////////////////////////
/// getStatus ()                                               ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the status of the student.                        ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public AcademicStatus getStatus() {
        return acaStat;
    }



//////////////////////////////////////////////////////////////////
/// setStatus (status)                                         ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the status of the student.                   ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setStatus(AcademicStatus academicStatus) {
        this.acaStat = academicStatus;
    }




//////////////////////////////////////////////////////////////////
/// getLastName ()                                             ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the last name of the student.                     ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public String getLastName() {
        return lastName;
    }


//////////////////////////////////////////////////////////////////
/// setLastName (lastName)                                     ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns:Sets the last name of the student.                 ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


//////////////////////////////////////////////////////////////////
/// getFirstName (firstName)                                   ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Returns the first name of the student.            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public String getFirstName() {
        return firstName;
    }


//////////////////////////////////////////////////////////////////
/// setFirstName (firstName)                                   ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the first name of the student                ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }




//////////////////////////////////////////////////////////////////
/// setCompany ()                                              ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the company the student belongs to           ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setSchool(School school) {
        this.school = school;
    }


//////////////////////////////////////////////////////////////////
/// getCompany ()                                              ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the company the student belongs to                ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public School getCompany() {
        return school;
    }


//////////////////////////////////////////////////////////////////
/// toString ()                                                ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: a string representation of the student            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}