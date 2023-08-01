package com.oneHealth.DoctorFees.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.DoctorFees.entity.DoctorFees;
import com.oneHealth.DoctorFees.exception.DatabaseException;
import com.oneHealth.DoctorFees.exception.FeesNotFoundException;
import com.oneHealth.DoctorFees.repository.DoctorFeesRepository;
import com.oneHealth.DoctorFees.service.DoctorFeesService;

/**
 * The DoctorFeesServiceImpl class is responsible for implementing the business logic
 * for managing bank details of doctors. It interacts with the DoctorFeesRepository to perform CRUD operations.
 * @author Madhavi
 * @version 3.9.10
 */
@Service
public class DoctorFeesServiceImpl implements DoctorFeesService {

    // Autowire DoctorFeesRepository to perform CRUD operations on the DoctorFees entity
    @Autowired
    private DoctorFeesRepository doctorFeesRepository;

   
    
    // Method to save a DoctorFees object in the database and handle DatabaseException if any occurs
    @Override
    public DoctorFees saveDoctorFees(DoctorFees doctorFees) throws DatabaseException {
        return doctorFeesRepository.save(doctorFees);
    }

  
    
    // Method to retrieve DoctorFees by its ID and handle FeesNotFoundException if the fees for the given doctorId are not found
	@Override
	public DoctorFees getFeesByID(long doctorId) throws FeesNotFoundException {
		// Try to find the DoctorFees by its ID using the doctorFeesRepository
		// If the DoctorFees is found, return it; otherwise, throw a FeesNotFoundException with an appropriate message
		return doctorFeesRepository.findById(doctorId).orElseThrow(
				() -> new FeesNotFoundException("No Fees Found with This ID" + doctorId)
		);
	}

    
	
	
	// Method to retrieve a list of all DoctorFees objects from the database and handle DatabaseException if any occurs
	@Override
	public List<DoctorFees> getAllDoctorsFees() throws DatabaseException {
		return doctorFeesRepository.findAll();
	}

    
	
	
	
	
	// Method to update DoctorFees by its ID and handle FeesNotFoundException if the fees for the given doctorId are not found
	@Override
	public DoctorFees updateFeesByID(long doctorId, DoctorFees updatedFees) throws FeesNotFoundException {
		// Try to find the DoctorFees by its ID using the doctorFeesRepository
		// If the DoctorFees is found, update its fees with the new fees provided in the updatedFees parameter and save it back to the database
		// If the DoctorFees is not found, throw a FeesNotFoundException with an appropriate message
		DoctorFees doctorFees = doctorFeesRepository.findById(doctorId).orElseThrow(
				() -> new FeesNotFoundException("No Doctor Fees found with this ID: " + doctorId)
		);
		doctorFees.setFees(updatedFees.getFees());

		return doctorFeesRepository.save(doctorFees);
	}

	
	
	
	// Method to delete DoctorFees by its ID and handle FeesNotFoundException if the fees for the given doctorId are not found
	@Override
	public DoctorFees deleteFeesByID(long doctorId) throws FeesNotFoundException {
	    // Check if the doctor with the given doctorId exists in the database
	    // If found, delete the doctor's fees from the database using the doctorFeesRepository.delete() method
	    // If not found, throw a FeesNotFoundException with an appropriate message
	    DoctorFees doctorFees = doctorFeesRepository.findById(doctorId).orElseThrow(
	            () -> new FeesNotFoundException("No Doctor Fees found with this ID: " + doctorId)
	    );

	    doctorFeesRepository.delete(doctorFees);

	    return doctorFees;
	}

}
