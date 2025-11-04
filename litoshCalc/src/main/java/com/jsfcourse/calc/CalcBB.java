// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
   private String x;
   private String y;
   private Double result;
   @Inject
   FacesContext ctx;

   public CalcBB() {
   }

   public String getX() {
      return this.x;
   }

   public void setX(String x) {
      this.x = x;
   }

   public String getY() {
      return this.y;
   }

   public void setY(String y) {
      this.y = y;
   }

   public Double getResult() {
      return this.result;
   }

   public void setResult(Double result) {
      this.result = result;
   }

   public boolean doTheMath() {
      try {
         double x = Double.parseDouble(this.x);
         double y = Double.parseDouble(this.y);
         this.result = x + y;
         this.ctx.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", (String)null));
         return true;
      } catch (Exception var5) {
         this.ctx.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", (String)null));
         return false;
      }
   }

   public String calc() {
      return this.doTheMath() ? "showresult" : null;
   }

   public String calc_AJAX() {
      if (this.doTheMath()) {
         this.ctx.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + this.result, (String)null));
      }

      return null;
   }

   public String info() {
      return "info";
   }
}
