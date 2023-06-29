package BU.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


//
// Class: AbstractTable
//
// Description:
// The AbstractEntity class is an abstract class that provides basic functionality for entities in the system.
// It contains an ID field that is generated using the SEQUENCE strategy and methods for getting the ID, checking if the entity is persisted,
// and overriding the hashCode and equals methods for proper object comparison.
//
@MappedSuperclass
public abstract class AbstractTable {

    /* The ID field for the entity, generated using the SEQUENCE strategy. */
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

//////////////////////////////////////////////////////////////////
/// getId ()                                                   ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Returns the ID of the entity.                     ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public Long getId() { return id; }



//////////////////////////////////////////////////////////////////
/// isPersisted ()                                             ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: true if the entity has been                       ///
// persisted, false otherwise.                                 ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public boolean isPersisted() { return id != null; }


//////////////////////////////////////////////////////////////////
/// hashCode ()                                                ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: the hashCode value for the entity.                ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    @Override
    public int hashCode() {
        if (getId() != null) { return getId().hashCode(); }
        return super.hashCode();
    }


//////////////////////////////////////////////////////////////////
/// equals (obj)                                               ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: true if the object is equal to                    ///
//           this entity, false otherwise.                     ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        AbstractTable other = (AbstractTable) obj;
        if (getId() == null || other.getId() == null) { return false; }
        return getId().equals(other.getId());
    }
}