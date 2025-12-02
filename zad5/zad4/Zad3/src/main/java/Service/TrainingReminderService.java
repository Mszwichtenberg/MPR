package Service;

import Interfaces.CertificatesRepository;
import Interfaces.CommunicationService;
import Interfaces.TrainingLogger;
import Models.Certification;
import Models.Employee;

import java.time.LocalDate;
import java.util.List;

public class TrainingReminderService {
    private CertificatesRepository certificatesRepository;
    private CommunicationService communicationService;
    private TrainingLogger logger;

    public TrainingReminderService(CertificatesRepository certificatesRepository, CommunicationService communicationService, TrainingLogger logger) {
        this.certificatesRepository = certificatesRepository;
        this.communicationService = communicationService;
        this.logger = logger;
    }

    public void sendReminders(LocalDate today){
        LocalDate to = today.plusDays(30);
        List<Certification> expiringCertifications = certificatesRepository.findExpiringBetween(today, to);
        for (Certification certification : expiringCertifications) {
            Employee employee = certification.getEmployee();
            String message=buildMessage(certification);
            communicationService.sendReminder(employee, message);
            logger.log("Reminder sent to "+employee.getEmail()+" for "+certification.getType());
        }
    }
    private String buildMessage(Certification certification) {
        return "Reminder: Your training " + certification.getType()
                + " expires: " + certification.getExpiryDate();
    }


}
