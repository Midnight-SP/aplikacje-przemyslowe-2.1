Zadanie: Integracja z plikami CSV i REST API (1 punkt)
## Kontekst

System zarządzania pracownikami wymaga rozszerzenia o import danych z plików CSV oraz synchronizację z API pod adresem https://jsonplaceholder.typicode.com/users. Dodatkowo system musi generować statystyki analityczne dla poszczególnych firm.

## Wymagania funkcjonalne

### Import z pliku CSV

Należy zaimplementować klasę `ImportService` z metodą `importFromCsv` przyjmującą ścieżkę do pliku. Plik CSV ma strukturę: `firstName`, `lastName`, `email`, `company`, `position`, `salary` z nagłówkiem w pierwszej linii. Metoda pomija nagłówek i puste linie, parsuje każdy wiersz do obiektu `Employee` i dodaje go do `EmployeeService`. Walidacja musi sprawdzać czy stanowisko istnieje w enumie `Position` oraz czy wynagrodzenie jest dodatnie. Błędne wiersze należy zapisać z numerem linii i opisem błędu, ale kontynuować import pozostałych. Metoda zwraca obiekt `ImportSummary` zawierający liczbę zaimportowanych pracowników i listę błędów.

### Integracja z REST API

Należy zaimplementować klasę `ApiService` z metodą `fetchEmployeesFromApi`, która wykonuje zapytanie GET do podanego API, parsuje odpowiedź JSON używając biblioteki Gson i zwraca listę obiektów `Employee`. API zwraca tablicę z polami: `name` (pełne imię do rozdzielenia na `firstName` i `lastName`), `email` oraz `company.name`. Wszystkim użytkownikom z API przypisać stanowisko `PROGRAMISTA` i bazową stawkę tego stanowiska. Metoda rzuca `ApiException` przy błędach HTTP lub problemach z parsowaniem.

### Operacje analityczne

Należy dodać do `EmployeeService` dwie metody analityczne wykorzystujące Stream API. Pierwsza metoda `validateSalaryConsistency` zwraca listę pracowników z wynagrodzeniem niższym niż bazowa stawka ich stanowiska. Druga metoda `getCompanyStatistics` zwraca mapę, gdzie kluczem jest nazwa firmy, a wartością obiekt `CompanyStatistics` zawierający liczbę pracowników w firmie, średnie wynagrodzenie oraz pełne imię i nazwisko osoby z najwyższym wynagrodzeniem.

## Struktura projektu

```
src/
├── model/
│   ├── Employee.java
│   ├── Position.java
│   ├── ImportSummary.java
│   └── CompanyStatistics.java
├── service/
│   ├── EmployeeService.java
│   ├── ImportService.java
│   └── ApiService.java
├── exception/
│   ├── InvalidDataException.java
│   └── ApiException.java
└── Main.java
```

Pakiet `model` zawiera klasy domenowe reprezentujące encje biznesowe. Pakiet `service` zawiera logikę biznesową w dedykowanych serwisach, gdzie każdy serwis ma jedną jasno określoną odpowiedzialność. Pakiet `exception` zawiera wyjątki checked dla różnych kategorii błędów.

## Wymagania techniczne

Wykorzystać `BufferedReader` z try-with-resources do czytania CSV. Parsowanie przez `split` i `trim`. Obsłużyć `IOException` i `IllegalArgumentException` przy walidacji `Position`. Do HTTP użyć `HttpClient` z Java 11 lub nowszego. Do JSON użyć biblioteki Gson (dodać do `pom.xml`: `groupId` com.google.code.gson, `artifactId` gson, `version` 2.10.1). Parsować `JsonArray` z Gson i ekstrahować pola metodami `get` oraz `getAsString`.

Wszystkie operacje na kolekcjach pracowników implementować przez Stream API. Metoda `getCompanyStatistics` powinna używać `Collectors.groupingBy` do grupowania pracowników według nazwy firmy, a następnie dla każdej grupy obliczać statystyki wykorzystując operacje `count`, `average` i `max`. Klasa `CompanyStatistics` powinna mieć konstruktor przyjmujący wszystkie pola oraz nadpisaną metodę `toString`.

Integracja z REST API:

Należy zaimplementować klasę ApiService z metodą fetchEmployeesFromApi, która wykonuje zapytanie GET do podanego API, parsuje odpowiedź JSON używając biblioteki Gson i zwraca listę obiektów Employee. API zwraca tablicę z polami: name (pełne imię do rozdzielenia na firstName i lastName), email oraz company.name. Wszystkim użytkownikom z API przypisać stanowisko PROGRAMISTA i bazową stawkę tego stanowiska. Metoda rzuca ApiException przy błędach HTTP lub problemach z parsowaniem.

Operacje analityczne:

Należy dodać do EmployeeService dwie metody analityczne wykorzystujące Stream API. Pierwsza metoda validateSalaryConsistency zwraca listę pracowników z wynagrodzeniem niższym niż bazowa stawka ich stanowiska. Druga metoda getCompanyStatistics zwraca mapę, gdzie kluczem jest nazwa firmy, a wartością obiekt CompanyStatistics zawierający liczbę pracowników w firmie, średnie wynagrodzenie oraz pełne imię i nazwisko osoby z najwyższym wynagrodzeniem.

Struktura projektu:

src/
├── model/
│   ├── Employee.java
│   ├── Position.java
│   ├── ImportSummary.java
│   └── CompanyStatistics.java
├── service/
│   ├── EmployeeService.java
│   ├── ImportService.java
│   └── ApiService.java
├── exception/
│   ├── InvalidDataException.java
│   └── ApiException.java
└── Main.java

Pakiet model zawiera klasy domenowe reprezentujące encje biznesowe. Pakiet service zawiera logikę biznesową w dedykowanych serwisach, gdzie każdy serwis ma jedną jasno określoną odpowiedzialność. Pakiet exception zawiera wyjątki checked dla różnych kategorii błędów.

Wymagania techniczne:

Wykorzystać BufferedReader z try-with-resources do czytania CSV. Parsowanie przez split i trim. Obsłużyć IOException i IllegalArgumentException przy walidacji Position. Do HTTP użyć HttpClient z Java 11 lub nowszego. Do JSON użyć biblioteki Gson (dodać do pom.xml: groupId com.google.code.gson, artifactId gson, version 2.10.1). Parsować JsonArray z Gson i ekstrahować pola metodami get oraz getAsString.

Wszystkie operacje na kolekcjach pracowników implementować przez Stream API. Metoda getCompanyStatistics powinna używać Collectors.groupingBy do grupowania pracowników według nazwy firmy, a następnie dla każdej grupy obliczać statystyki wykorzystując operacje count, average i max. Klasa CompanyStatistics powinna mieć konstruktor przyjmujący wszystkie pola oraz nadpisaną metodę toString.