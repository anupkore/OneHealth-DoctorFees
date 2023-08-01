package com.oneHealth.DoctorFees.entity;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing the DoctorFees information of a doctor.
 * This class is mapped to a database table to store the doctor fees of doctors.
 * @author Madhavi
 * @version 3.9.10
 */
@Entity
public class DoctorFees {

	// Primary key field for DoctorFees table
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Value(value="101")
	private Long doctorId;

	// Field representing the speciality of the doctor
	private String speciality;

	// Field representing the fees charged by the doctor
	private Long fees;

	// Default constructor required for JPA
	public DoctorFees() {
		super();
	}

	// Parameterized constructor to initialize DoctorFees object with values
	public DoctorFees(Long doctorId, String speciality, Long fees) {
		super();
		this.doctorId = doctorId;
		this.speciality = speciality;
		this.fees = fees;
	}

	// Getter method for doctorId
	public Long getDoctorId() {
		return doctorId;
	}

	// Setter method for doctorId
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	// Getter method for speciality
	public String getSpeciality() {
		return speciality;
	}

	// Setter method for speciality
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	// Getter method for fees
	public Long getFees() {
		return fees;
	}

	// Setter method for fees
	public void setFees(Long fees) {
		this.fees = fees;
	}

	// Override toString() method to print DoctorFees object as a string
	@Override
	public String toString() {
		return "DoctorFees [doctorId=" + doctorId + ", speciality=" + speciality + ", fees=" + fees + "]";
	}
}
