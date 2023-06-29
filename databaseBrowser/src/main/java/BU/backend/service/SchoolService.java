package BU.backend.service;

import BU.backend.entity.School;
import BU.backend.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import java.util.List;

//
// Class: SchoolService
//
// Description:
// This class represents, Service class for performing business
// logic related to {@link School} entities
//
@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

//////////////////////////////////////////////////////////////////
/// addListener (eventType, listener, <T>)                     ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: a list of all {@link School} entities            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public List<School> findAll() { return schoolRepository.findAll();  }
}