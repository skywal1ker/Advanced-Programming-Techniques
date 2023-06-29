package BU.backend.repository;

import BU.backend.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

//
// interface: SchoolRepository
//
// Description:
// This interface extends the
// {@link JpaRepository} interface with the entity class type
//  A repository interface for performing CRUD operations on {@link School} entities.
//
public interface SchoolRepository extends JpaRepository<School, Long> {
}