package com.toptechsol.ipc.service;

import java.util.Date;
import java.util.List;

import com.toptechsol.ipc.model.Certificate;

public interface CertificateService {
	Certificate findById(Long id);
	Certificate save(Certificate certifcate);
	void deleteCertificate(Long id);
	
	List<Certificate> listExpiredDocument(Date date);
	List<Certificate> listDocumentWillExpireInMonths(Date startDate,Date endDate);
	
}
