package Service;

import Models.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PerformanceReviewService {
    public void addRating(Employee employee, int rating) {
        if (rating<1 || rating>5) {
            throw new IllegalArgumentException("Rating needs to be between 1 and 5");
        }
        employee.addRating(rating);
    }

    public List<Integer> getRatings(Employee employee) {
        return employee.getRatings();
    }
    public double getAverageRating(Employee employee) {
        List<Integer> ratings = employee.getRatings();
        if (ratings.isEmpty()) {
            return Double.NaN;
        }
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(Double.NaN);
    }

    public List<Employee> findTopPerformers(List<Employee> employees) {
        double maxAvarage = employees.stream().map(this::getAverageRating).filter(d->!Double.isNaN(d)).max(Double::compareTo).orElse(Double.NaN);
        if (Double.isNaN(maxAvarage)) {
            return List.of();
        }
        return employees.stream().filter(employee -> !Double.isNaN(getAverageRating(employee))).filter(employee -> Double.compare(getAverageRating(employee),maxAvarage)==0).sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());

    }

}
