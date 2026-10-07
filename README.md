# Kalkulator kredytowy — Jakarta EE (Servlet → JSF / PrimeFaces)

> Kalkulator raty kredytu w **Jakarta EE 11**, rozwijany etapami: od czystego **Servletu** z formularzem HTML do widoku **JSF z komponentami PrimeFaces** i walidacją po stronie serwera.

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta_EE-11-F8981D)
![PrimeFaces](https://img.shields.io/badge/PrimeFaces-15-0a5c9e)
![Maven](https://img.shields.io/badge/Maven-WAR-C71A36?logo=apachemaven&logoColor=white)
![NetBeans](https://img.shields.io/badge/IDE-NetBeans-1B6AC6?logo=apachenetbeanside&logoColor=white)

## Dlaczego powstał ten projekt

Laboratoria z przedmiotu **JPDSI** (Uniwersytet Śląski, Informatyka, III rok). To ten sam kalkulator, który wcześniej napisałem w PHP ([PBAW](https://github.com/llumiiss/PBAW)), przeniesiony do ekosystemu Javy. Dzięki temu łatwo porównać podejście „skrypt + szablon” z podejściem komponentowym (JSF) na serwerze aplikacyjnym.

## Etapy

| Moduł | Technologia | Opis |
|---|---|---|
| [`litoshcalc`](litoshcalc) | Servlet (`@WebServlet`), HTML + CSS | formularz HTML wysyła `POST /result`, servlet liczy ratę i zwraca stronę z wynikiem |
| [`litoshcalcPrimefaces`](litoshcalcPrimefaces) | JSF (Facelets `.xhtml`) + PrimeFaces | ten sam formularz zbudowany z komponentów `p:inputText` / `p:commandButton` powiązanych z beanem przez EL |
| [`litoshcalcValidate`](litoshcalcValidate) | JSF + PrimeFaces | etap poświęcony walidacji danych wejściowych (`required`, komunikaty) |

Każdy moduł to osobny projekt Maven (`packaging: war`) z endpointem kontrolnym JAX-RS `GET /resources/jakartaee11` → `ping Jakarta EE`.

## Logika obliczeń

Rata miesięczna liczona jest wzorem na ratę annuitetową (równe raty):

```
          K · r
R = ─────────────────      K – kwota kredytu
     1 − (1 + r)^(−n)      r – oprocentowanie roczne / 12 / 100
                           n – okres w latach · 12
```

```java
double monthlyPayment = (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));
```

## Uruchomienie

Wymagania: **JDK 17+**, **Maven 3.9+** i serwer zgodny z Jakarta EE 11 (np. **GlassFish 8**, Payara lub WildFly), albo NetBeans ze skonfigurowanym serwerem.

```bash
cd litoshcalc
mvn clean package
# wdroż target/litoshcalc-1.0-SNAPSHOT.war na serwer aplikacyjny
```

Następnie otwórz `http://localhost:8080/litoshcalc-1.0-SNAPSHOT/`.

## Status i dalsze kroki

- [x] Etap 1 — Servlet + formularz HTML (działa)
- [ ] Etap 2/3 — widok JSF jest gotowy, ale brakuje beana `creditCalculator` (`@Named @RequestScoped`) z polami formularza i metodą `calculate()`
- [ ] walidacja Bean Validation (`@NotNull`, `@Positive`, `@DecimalMin`) zamiast samego `required`
- [ ] harmonogram spłat w tabeli `p:dataTable` i testy jednostkowe logiki obliczeń (JUnit 5)

## Autor

**Maksym Litosh** — Uniwersytet Śląski w Katowicach.
