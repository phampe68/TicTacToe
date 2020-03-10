package com.comp1601.tipntax;


public class TipNTaxCalculator {
    public static final double DefaultTaxRate = 13.0; // 13.0 percent

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setTipPercentage(double tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public static final double DefaultTipPercentage = 15; //15 percent
    public static final double InvalidResult =- 1;

    public double getTaxRate() {
        return taxRate;
    }

    public double getTipPercentage() {
        return tipPercentage;
    }

    private double taxRate;
    private double tipPercentage;

    public TipNTaxCalculator(){
        //set default values
        taxRate = DefaultTaxRate;
        tipPercentage = DefaultTipPercentage;
    }


    public TipNTaxCalculator(double taxRate, double tipPercentage){
        this.taxRate = taxRate;
        this.tipPercentage = tipPercentage;
    }


    public double calculate(double amount){
        if(amount < 0) //negative amount
            return InvalidResult;
        if(amount > 1000) //amount too large
            return InvalidResult;
        if(this.taxRate < 0) //negative tax rate
            return InvalidResult;
        if(this.taxRate > 100) //tax percentage too high
            return InvalidResult;
        if(this.tipPercentage < 0) //negative tip percentage
            return InvalidResult;
        if(this.tipPercentage > 100)// tip percentage too high
            return InvalidResult;

        double tax = amount*(this.taxRate/100);
        double tip = amount*(this.tipPercentage/100);
        return amount + tax + tip;
       // return amount*(1.0 + this.taxRate/100)*(1.0+ this.tipPercentage/100);
    }


}
