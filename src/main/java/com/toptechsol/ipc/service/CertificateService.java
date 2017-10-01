package com.toptechsol.ipc.service;

import com.toptechsol.ipc.model.Certificate;

public interface CertificateService {
	Certificate findById(Long id);
	Certificate save(Certificate certifcate);
	void deleteCertificate(Long id);
}
