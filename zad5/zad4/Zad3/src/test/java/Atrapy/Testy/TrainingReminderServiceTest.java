package Atrapy.Testy;

import Atrapy.Dummy.TrainingLoggerDummy;
import Atrapy.Mock.CommunicationServiceMock;
import Atrapy.Spy.CommunicationServiceSpy;
import Atrapy.Stub.CertificatesRepositoryStub;
import Interfaces.CertificatesRepository;
import Models.Certification;
import Models.Department;
import Models.Employee;
import Service.TrainingReminderService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
public class TrainingReminderServiceTest {
    @Test
    void shouldSendRemindersForExpiringCertifications() {

        Employee e1 = new Employee("Jan", "jan@corp.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));
        Employee e2 = new Employee("Janek", "janek@dsadsadsa.com", "TechCorp", Department.manager, LocalDate.of(2019, 1, 1));
        LocalDate today = LocalDate.of(2025, 1, 1);

        Certification c1 = new Certification(e1, "BHP", today.plusDays(10));
        Certification c2 = new Certification(e2, "RODO", today.plusDays(5));

        CertificatesRepositoryStub repoStub = new CertificatesRepositoryStub()
                .withCertifications(c1)
                .withCertifications(c2);

        CommunicationServiceSpy spy = new CommunicationServiceSpy();
        TrainingLoggerDummy dummyLogger = new TrainingLoggerDummy();

        TrainingReminderService service = new TrainingReminderService(repoStub, spy, dummyLogger);

        service.sendReminders(today);

        assertThat(spy.getSentRemindersSize()).isEqualTo(2);
        assertThat(spy.getSentReminders().get(0).employee).isEqualTo(e1);
        assertThat(spy.getSentReminders().get(1).employee).isEqualTo(e2);
    }
    @Test
    void shouldUseMockToVerifyNumbersOfReminders() {
        Employee e2 = new Employee("Janek", "janek@dsadsadsa.com", "TechCorp", Department.manager, LocalDate.of(2019, 1, 1));
        LocalDate today = LocalDate.of(2025, 1, 1);

        Certification c1 = new Certification(e2, "BHP", today.plusDays(7));
        CertificatesRepositoryStub repoStub = new CertificatesRepositoryStub().withCertifications(c1);
        CommunicationServiceMock mock = new CommunicationServiceMock()
                .expectedCount(1);

        TrainingLoggerDummy dummyLogger = new TrainingLoggerDummy();

        TrainingReminderService service = new TrainingReminderService(repoStub, mock, dummyLogger);

        service.sendReminders(today);
        mock.verify();
    }
}
