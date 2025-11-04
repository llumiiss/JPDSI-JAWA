INSTRUKCJA DODANIA PRIMEFACES DO PROJEKTU
==========================================

METODA 1: Pobranie ręczne (dla pliku WAR)
------------------------------------------

1. Pobierz bibliotekę PrimeFaces:
   - Odwiedź: https://www.primefaces.org/downloads/
   - Pobierz najnowszą wersję PrimeFaces (np. 12.0.0 lub nowszą)
   - Plik: primefaces-X.X.X.jar

2. Skopiuj plik JAR:
   - Skopiuj pobrany plik primefaces-X.X.X.jar
   - Wklej go do tego folderu: WEB-INF/lib/

3. Jeśli używasz PrimeIcons (ikony), pobierz również:
   - primeicons-X.X.X.jar
   - Skopiuj również do: WEB-INF/lib/

4. Struktura powinna wyglądać tak:
   WEB-INF/
     lib/
       primefaces-12.0.0.jar
       primeicons-6.0.1.jar


METODA 2: Przez Maven (jeśli używasz Maven)
---------------------------------------------

Dodaj do pliku pom.xml:

<dependencies>
    <dependency>
        <groupId>org.primefaces</groupId>
        <artifactId>primefaces</artifactId>
        <version>12.0.0</version>
    </dependency>
</dependencies>


METODA 3: Przez Gradle (jeśli używasz Gradle)
-----------------------------------------------

Dodaj do pliku build.gradle:

dependencies {
    implementation 'org.primefaces:primefaces:12.0.0'
}


WERSJA PRIMEFACES
-----------------
Dla Jakarta EE 10+ (jak w Twoim projekcie) użyj PrimeFaces 12.0.0 lub nowszej.
Dla starszych wersji Java EE użyj PrimeFaces 11.x lub 10.x.

LINKI POMOCNICZE
----------------
- Strona główna: https://www.primefaces.org/
- Download: https://www.primefaces.org/downloads/
- Dokumentacja: https://primefaces.github.io/primefaces/
- GitHub: https://github.com/primefaces/primefaces

UWAGA
-----
Po dodaniu biblioteki, zrestartuj serwer aplikacji (np. WildFly, Tomcat).

