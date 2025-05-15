# 🛠️ Complaint Service — REST API do obsługi reklamacji

Ten projekt to backendowy serwis REST napisany w Javie z wykorzystaniem Spring Boot, służący do rejestrowania i zarządzania reklamacjami produktów. 
W założeniach stworzyłam prostą autentykacje użytkownika do automatycznego wyciągania identyfikatora użytkownika po jego zalogowaniu, dzięki czemu użytkownik może wylistować tylko własne reklamacje. Użytkownicy mogą logować się, tworzyć reklamacje przypisane do produktów, edytować je oraz przeglądać historię własnych zgłoszeń.

---

## 📚 Funkcjonalności

-  Logowanie użytkownika z JWT
-  Dodawanie reklamacji (zliczanie duplikatów)
-  Edycja treści reklamacji
-  Lista reklamacji zalogowanego użytkownika
-  Automatyczne ustalanie kraju zgłaszającego na podstawie IP
-  Każda operacja wymaga autoryzacji (JWT)

---

## 🚀 Technologie

- Java 17
- Spring Boot
- Spring Security (JWT)
- PostgreSQL
- MapStruct
- Maven
- GeoLite2 - do wyrywania kraju wysłania requestu

(Lombok niestety nie chciał mi złapać na Intellij Community 😔 )

---

## 🔐 Autoryzacja

Logowanie odbywa się przez endpoint `/auth/login`, który zwraca JWT. Token należy przesyłać w nagłówku `Authorization`:

Dla uproszczenia zakładam, że użytkownik musi być zalogowany aby dokonać reklamacji. Zakładam, że reklamacja zgłaszana jest per produkt (nie per zamówienie). Nie sprawdzam czy dany użytkownik dany produkt zamówił czy nie. Dla uproszczenia zakładam również że istnieje API które zwraca listę produktów (tak, aby można było wybrać, który produkt chce się zareklamować) z których wyciągane będą identyfikatory wymagane do endpoint'ów zaimplementowanych w zadaniu.
W związku z brakiem podstatowego API CRUD (brak wymagań) dla kolekcji produktów i użytkowników dodałam klasę inicjalizującą dane początkowe DataInitializer.class.

## Logowanie:

Zwróconym JWT uwierzytelniam się w następnych requestach. Z JWT pobieram informację o aktualnie zalogowanym użytkowniku- pozwala mi to aktumatycznie przypisać użytkownika do reklamacji. 
![image](https://github.com/user-attachments/assets/ff0dd08b-fbd6-4fa6-bd2f-4642a69c14bc)

## Wylistowanie zgłoszonych reklamacji:
![image](https://github.com/user-attachments/assets/7a4f785d-4260-443f-9e58-6061fbdc7690)
![image](https://github.com/user-attachments/assets/7be2a048-e74e-4dd1-8fb2-de7e63f273fe)

## Dodanie nowej reklamacji:
![image](https://github.com/user-attachments/assets/5ac3e1d7-bb89-40d4-9816-a5cf9d1ef339)
![image](https://github.com/user-attachments/assets/518b5aaa-fbf0-4ea3-8e8d-51bb48b692de)

Próba dodania duplikatu- zwiększa licznik bez edycji pozostałych danych:
![image](https://github.com/user-attachments/assets/eb3ff75c-d7e2-4d6c-9a2a-bbe124ba2dd0)

## Edycja reklamacji:
![image](https://github.com/user-attachments/assets/4c22f46b-9655-468d-89e9-01585aa0ef7c)
![image](https://github.com/user-attachments/assets/139fcbd4-268d-4b8a-be93-31714932bb23)

 





