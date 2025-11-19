package com.mycompany.litoshcalc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/result")  // Mapowanie do URL "/result"
public class CreditCalculatorServlet extends HttpServlet {

    // Obsługuje żądanie POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pobranie danych z formularza
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate")) / 100 / 12; // miesięczne oprocentowanie
        int loanTerm = Integer.parseInt(request.getParameter("loanTerm")) * 12; // okres w miesiącach

        // Obliczenie miesięcznej raty
        double monthlyPayment = (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));

        // Ustawienie typu odpowiedzi
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Zwrócenie wyniku do przeglądarki
        out.println("<html>");
        out.println("<head><title>Kalkulator Kredytowy</title></head>");
        out.println("<body>");
        out.println("<h1>Twoja miesięczna rata: " + String.format("%.2f", monthlyPayment) + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
