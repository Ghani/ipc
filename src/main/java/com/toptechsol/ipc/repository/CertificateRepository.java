package com.toptechsol.ipc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Certificate;

@Repository("certificateRepository")
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	
}
