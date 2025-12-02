package Interfaces;

import Models.Certification;

import java.time.LocalDate;
import java.util.List;

public interface CertificatesRepository {
    List<Certification> findExpiringBetween(LocalDate from, LocalDate to);
}
