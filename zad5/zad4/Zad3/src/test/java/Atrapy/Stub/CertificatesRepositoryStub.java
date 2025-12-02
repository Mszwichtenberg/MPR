package Atrapy.Stub;

import Interfaces.CertificatesRepository;
import Models.Certification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CertificatesRepositoryStub implements CertificatesRepository {
    private List<Certification> certifications=new ArrayList<>();
    public CertificatesRepositoryStub withCertifications(Certification certification) {
        certifications.add(certification);
    return  this;
    }

    @Override
    public List<Certification> findExpiringBetween(LocalDate from, LocalDate to) {
        return new ArrayList<>(certifications);
    }
}
