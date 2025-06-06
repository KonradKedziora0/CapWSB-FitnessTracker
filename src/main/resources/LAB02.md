# LABORATORIUM 02

Zadania proszę realizować zgodnie z własnym tempem. Zadania mają pomóc w przygotowaniu do Egzaminu oraz zrozumieniu
Frameworka Spring.
Termin upływa po 14 dniach od rozpoczęciu laboratorium. Po przesłaniu zadania proszę o zaznaczenie na Moodle wykonanie
zadania.

## ZADANIE 1. Sieciowe API do operacji typu CRUD na użytkownikach

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość zarządzania użytkownikami
serwisu FitnessTracker:

- móc ich wyszukiwać, pobierać
- móc wprowadzać nowych użytkowników do systemu
- móc usuwać użytkowników z systemu
- móc aktualizować użytkowników

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [X] wylistowanie podstawowych informacji o wszystkich użytkownikach zapisanych w systemie (tylko ID oraz nazwa
  użytkownika)
- [X] pobranie szczegółów dotyczących wybranego użytkownika (dowolny parametr: ID/imię & nazwisko/datę urodzenia/
  e-mail)
- [X] utworzenie nowego użytkownika
- [X] usunięcie użytkownika (konkretny, np. konkretny ID danego uzytkownika)
- [X] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [X] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany
- [X] aktualizowanie użytkowników w systemie (dowolnie wybrany atrybut)

### Wymagania techniczne

- [X] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [X] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [X] rozwiązanie powinno spełniać zasady SOLID
- [X] testy integracyjne powinny poprawnie się wykonywać UserApiIntegrationTest
- [ ] (OPCJONALNIE) rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [X] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [X] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [X] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [X] rozwiązanie powinno wykorzystywać rekordy (Java 16+) do definicji obiektów transferu danych (DTO)