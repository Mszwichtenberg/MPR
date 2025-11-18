package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exception.ApiException;
import model.Employee;
import model.Position;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    public List<Employee> fetchEmployeesFromApi() throws ApiException {
        List<Employee> result = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new ApiException("HTTP error: " + statusCode);
            }

            String body = response.body();

            JsonArray array = JsonParser.parseString(body).getAsJsonArray();

            for (JsonElement element : array) {
                JsonObject obj = element.getAsJsonObject();

                String fullName = obj.get("name").getAsString();
                String email = obj.get("email").getAsString();
                String companyName = obj
                        .getAsJsonObject("company")
                        .get("name")
                        .getAsString();

                String firstName;
                String lastName;
                String[] nameParts = fullName.trim().split(" ");
                if (nameParts.length == 1) {
                    firstName = nameParts[0];
                    lastName = "";
                } else {
                    firstName = nameParts[0];
                    lastName = nameParts[nameParts.length - 1];
                }

                Position position = Position.PROGRAMMER;
                double salary = position.getBaseSalary();

                Employee employee = new Employee(
                        firstName,
                        lastName,
                        email,
                        companyName,
                        position,
                        salary
                );

                result.add(employee);
            }

            return result;

        } catch (IOException | InterruptedException e) {
            throw new ApiException("Error calling API", e);
        } catch (Exception e) {
            throw new ApiException("Error parsing API response", e);
        }
    }
}
