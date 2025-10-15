package com.techcorp;

import com.techcorp.model.ImportSummary;
import com.techcorp.service.EmployeeService;
import com.techcorp.service.ImportService;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== TechCorp Employee Management System ===\n");
        
        // Inicjalizacja serwisów
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService(employeeService);
        
        // Import z pliku CSV
        System.out.println("Importowanie pracowników z pliku CSV...");
        ImportSummary summary = importService.importFromCsv("test-data.csv");
        
        // Wyświetlenie wyników importu
        System.out.println("\n=== Wyniki importu ===");
        System.out.println("Zaimportowano pracowników: " + summary.getImportedCount());
        System.out.println("Liczba błędów: " + summary.getErrors().size());
        
        if (!summary.getErrors().isEmpty()) {
            System.out.println("\nBłędy podczas importu:");
            for (String error : summary.getErrors()) {
                System.out.println("  - " + error);
            }
        }
        
        // Wyświetlenie wszystkich pracowników
        System.out.println("\n=== Lista wszystkich pracowników ===");
        employeeService.getAllEmployees().forEach(System.out::println);
    }
}
