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
