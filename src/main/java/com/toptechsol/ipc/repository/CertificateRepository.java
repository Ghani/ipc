package com.toptechsol.ipc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Certificate;

@Repository("certificateRepository")
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	
	
	@Query("SELECT certificate from Certificate certificate "
			+ "WHERE "
			+ "certificate.expiryDate < :date ")
	List<Certificate> listExpiredDocument(@Param("date") Date date);

	
	
	@Query("SELECT certificate from Certificate certificate "
			+ "WHERE "
			+ "certificate.expiryDate BETWEEN :startDate AND :endDate ORDER BY certificate.expiryDate DESC ")
	List<Certificate> listDocumentWillExpireInMonths(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	

	
	
}
