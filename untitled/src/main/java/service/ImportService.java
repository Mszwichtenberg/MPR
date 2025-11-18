package service;

import exception.InvalidDataException;
import model.Employee;
import model.ImportSummary;
import model.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportService {
    private EmployeeService employeeService;
    public ImportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ImportSummary importFromCsv(String filePath) throws InvalidDataException {
        List<String> errors = new ArrayList<>();
        int importedCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 6) {
                    errors.add("Line " + lineNumber + ": invalid column count");
                    continue;
                }

                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String email = parts[2].trim();
                String company = parts[3].trim();
                String positionStr = parts[4].trim();
                String salaryStr = parts[5].trim();

                try {
                    Position position = Position.valueOf(positionStr.toUpperCase());

                    double salary = Double.parseDouble(salaryStr);
                    if (salary <= 0) {
                        errors.add("Line " + lineNumber + ": salary must be positive");
                        continue;
                    }

                    Employee employee = new Employee(
                            firstName,
                            lastName,
                            email,
                            company,
                            position,
                            salary
                    );

                    employeeService.addEmployee(employee);
                    importedCount++;

                } catch (IllegalArgumentException e) {
                    errors.add("Line " + lineNumber + ": invalid position or salary format (" + e.getMessage() + ")");
                }
            }

        } catch (IOException e) {

            throw new InvalidDataException("Error reading CSV file: " + filePath, e);
        }

        return new ImportSummary(importedCount, errors);
    }

}
