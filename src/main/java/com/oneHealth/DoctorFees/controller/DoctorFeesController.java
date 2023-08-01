package com.oneHealth.DoctorFees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.DoctorFees.entity.DoctorFees;
import com.oneHealth.DoctorFees.exception.DatabaseException;
import com.oneHealth.DoctorFees.exception.FeesNotFoundException;
import com.oneHealth.DoctorFees.service.DoctorFeesService;


import jakarta.validation.Valid;

/**
* Controller class for handling Doctor Fees related HTTP requests.
* @author Madhavi
* @version 3.9.10
*/

@RestController
@RequestMapping("/api/doctors")
public class DoctorFeesController {

	@Autowired
	private DoctorFeesService service;
	
	
	/**
	 * Handles HTTP POST request to save the doctor's fees information into the database.
	 *
	 * @param fees The DoctorFees object containing the doctor's fees details.
	 * @return A ResponseEntity with the response message and HTTP status code.
	 * @throws DatabaseException If there is an issue while interacting with the database.
	 */
	@PostMapping("/saveFees")
	public ResponseEntity<String> saveDoctorFees(@Valid @RequestBody DoctorFees fees) throws DatabaseException {
	    service.saveDoctorFees(fees);
	    return new ResponseEntity<>("Doctor Fees Saved Successfully", HttpStatus.CREATED);
	}

	
	
	
	/**
	 * Handles HTTP GET request to retrieve the doctor's fees information by doctorId from the database.
	 *
	 * @param doctorId The ID of the doctor for whom fees information is to be retrieved.
	 * @return A ResponseEntity with the DoctorFees object and HTTP status code.
	 * @throws FeesNotFoundException If the doctor's fees information is not found in the database.
	 */
	@GetMapping("/getDoctorFees/{doctorId}")
	public ResponseEntity<DoctorFees> getDoctorFees(@PathVariable(value = "doctorId") Long doctorId) throws FeesNotFoundException {
	    DoctorFees obj = service.getFeesByID(doctorId);
	    return ResponseEntity.ok().body(obj);
	}
	
	


	/**
	 * Handles HTTP GET request to retrieve the list of all doctors' fees from the database.
	 *
	 * @return A ResponseEntity with the list of DoctorFees objects and HTTP status code.
	 * @throws DatabaseException If there is an issue while interacting with the database.
	 */
	@GetMapping("/getAllDoctorFees")
	public ResponseEntity<List<DoctorFees>> getAllDoctorFees() throws DatabaseException {
	    // Retrieve the list of all doctors' fees from the service layer.
	    List<DoctorFees> doctorFeesList = service.getAllDoctorsFees();

	    // Return the list of doctors' fees in the response with HTTP status 200 (OK).
	    return new ResponseEntity<>(doctorFeesList, HttpStatus.OK);
	}
	
	
	/**
	 * Handles HTTP PUT request to update the doctor's fees information by doctorId in the database.
	 *
	 * @param doctorID   The ID of the doctor whose fees information needs to be updated.
	 * @param doctorfees The DoctorFees object containing the updated doctor's fees details.
	 * @return A ResponseEntity with the response message and HTTP status code.
	 * @throws FeesNotFoundException If the doctor's fees information is not found in the database.
	 */
	@PutMapping("/updateDoctorFees/{doctorID}")
	public ResponseEntity<String> updateDoctorFees(@PathVariable(value = "doctorID") long doctorID, @RequestBody DoctorFees doctorfees) throws FeesNotFoundException {
	    service.updateFeesByID(doctorID, doctorfees);
	    return new ResponseEntity<>("Doctor Fees updated Successfully", HttpStatus.CREATED);
	}

	
	/**
	 * Handles HTTP DELETE request to delete the doctor's fees information by doctorId from the database.
	 *
	 * @param doctorID The ID of the doctor whose fees information needs to be deleted.
	 * @return A ResponseEntity with the response message and HTTP status code.
	 * @throws FeesNotFoundException If the doctor's fees information is not found in the database.
	 */
	@DeleteMapping("/deleteDoctorFees/{doctorID}")
	public ResponseEntity<String> deleteFeesByID(@PathVariable(value = "doctorID") long doctorID) throws FeesNotFoundException {
	    service.deleteFeesByID(doctorID);
	    return new ResponseEntity<>("Doctor Fees deleted Successfully", HttpStatus.OK);
	}

}


