package com.mycompany.litoshcalcprimefaces.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/result")
public class CreditCalculatorServlet extends HttpServlet {

    // Obsługuje żądanie POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate")) / 100 / 12;
        int loanTerm = Integer.parseInt(request.getParameter("loanTerm")) * 12;

        double monthlyPayment = (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));

        // Zwrócenie wyniku do przeglądarki
        response.setContentType("text/html");
        response.getWriter().println("<h1>Twoja miesięczna rata: " + String.format("%.2f", monthlyPayment) + "</h1>");
    }
}