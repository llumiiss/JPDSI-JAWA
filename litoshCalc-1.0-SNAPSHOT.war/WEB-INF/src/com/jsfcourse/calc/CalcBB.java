package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
    
    private Double loanAmount;
    private Double interestRate;
    private Double loanTerm;
    private Double monthlyPayment;
    private Double totalPayment;
    private Double totalInterest;
    
    public Double getLoanAmount() {
        return loanAmount;
    }
    
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    public Double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
    
    public Double getLoanTerm() {
        return loanTerm;
    }
    
    public void setLoanTerm(Double loanTerm) {
        this.loanTerm = loanTerm;
    }
    
    public Double getMonthlyPayment() {
        return monthlyPayment;
    }
    
    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    
    public Double getTotalPayment() {
        return totalPayment;
    }
    
    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
    
    public Double getTotalInterest() {
        return totalInterest;
    }
    
    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }
    
    public String calc() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // Walidacja
        if (loanAmount == null || loanAmount <= 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Kwota kredytu musi być większa od zera"));
            return null;
        }
        
        if (interestRate == null || interestRate < 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Oprocentowanie nie może być ujemne"));
            return null;
        }
        
        if (loanTerm == null || loanTerm <= 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Okres kredytowania musi być większy od zera"));
            return null;
        }
        
        // Obliczenia kredytu
        // M = P * [r(1+r)^n] / [(1+r)^n - 1]
        // gdzie:
        // M = rata miesięczna
        // P = kwota kredytu (principal)
        // r = miesięczne oprocentowanie (roczne / 12 / 100)
        // n = liczba miesięcy
        
        double monthlyRate = interestRate / 12.0 / 100.0;
        int numberOfMonths = (int)(loanTerm * 12);
        
        if (monthlyRate == 0) {
            // Jeśli oprocentowanie = 0, rata to po prostu kwota / liczba miesięcy
            monthlyPayment = loanAmount / numberOfMonths;
        } else {
            double power = Math.pow(1 + monthlyRate, numberOfMonths);
            monthlyPayment = loanAmount * (monthlyRate * power) / (power - 1);
        }
        
        // Zaokrąglenie do 2 miejsc po przecinku
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;
        
        // Całkowita kwota do spłaty
        totalPayment = monthlyPayment * numberOfMonths;
        totalPayment = Math.round(totalPayment * 100.0) / 100.0;
        
        // Całkowite odsetki
        totalInterest = totalPayment - loanAmount;
        totalInterest = Math.round(totalInterest * 100.0) / 100.0;
        
        return "showresult";
    }
    
    public void calc_AJAX() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // Walidacja
        if (loanAmount == null || loanAmount <= 0) {
            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Kwota kredytu musi być większa od zera"));
            return;
        }
        
        if (interestRate == null || interestRate < 0) {
            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Oprocentowanie nie może być ujemne"));
            return;
        }
        
        if (loanTerm == null || loanTerm <= 0) {
            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Błąd", "Okres kredytowania musi być większy od zera"));
            return;
        }
        
        // Obliczenia kredytu
        double monthlyRate = interestRate / 12.0 / 100.0;
        int numberOfMonths = (int)(loanTerm * 12);
        
        if (monthlyRate == 0) {
            monthlyPayment = loanAmount / numberOfMonths;
        } else {
            double power = Math.pow(1 + monthlyRate, numberOfMonths);
            monthlyPayment = loanAmount * (monthlyRate * power) / (power - 1);
        }
        
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;
        totalPayment = monthlyPayment * numberOfMonths;
        totalPayment = Math.round(totalPayment * 100.0) / 100.0;
        totalInterest = totalPayment - loanAmount;
        totalInterest = Math.round(totalInterest * 100.0) / 100.0;
        
        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Sukces", "Rata miesięczna: " + monthlyPayment + " PLN"));
    }
    
    public String info() {
        return "info";
    }
}

