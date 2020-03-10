package com.comp1601.tipntax;

import org.junit.Test;
import static org.junit.Assert.*;


public class TipNTaxCalculatorUnitTest {

    public static double toleranceForReals = 0.01;

    @Test
    public void negativeAmountTest() throws Exception{
        TipNTaxCalculator calculate = new TipNTaxCalculator();
        double amount = -100;
        double result = calculate.calculate(amount);
        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }

    @Test
    public void tooLargeCalculationTest() throws Exception{
        TipNTaxCalculator calculator = new TipNTaxCalculator();
        double amount = 1000000000;
        double result = calculator.calculate(amount);
        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }

    @Test public void negativeTaxPercentageTest() throws Exception{

        double taxPercentage = -10;
        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, 0);
        double result = calculator.calculate(amount);

        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }


    @Test public void tooHighTaxPercentageTest() throws Exception{

        double taxPercentage = 101;
        double tipPercentage = 10;
        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }

    @Test
    public void negativeTipPercentageTest() throws Exception{
        double taxPercentage = 10;
        double tipPercentage = -10;

        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }

    @Test
    public void tooHighTipPercentageTest() throws Exception{
        double taxPercentage = 10;
        double tipPercentage = 101;

        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }

    @Test
    public void correctTaxTest() throws Exception{
        double taxPercentage = 10;
        double tipPercentage = 0;

        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        //correct calculation of test values
        double actual = 110;

        double tolerance = toleranceForReals;
        assertEquals(result, actual, tolerance);
    }

    @Test
    public void correctTipTest() throws Exception{
        double taxPercentage = 0;
        double tipPercentage = 10;

        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        //correct calculation of test values
        double actual = 110;

        double tolerance = toleranceForReals;
        assertEquals(result, actual, tolerance);
    }

    @Test
    public void correctTaxAndTipTest() throws Exception{
        double taxPercentage = 10;
        double tipPercentage = 10;

        double amount = 100;
        TipNTaxCalculator calculator = new TipNTaxCalculator(taxPercentage, tipPercentage);
        double result = calculator.calculate(amount);

        //correct calculation of test values
        double actual = 120;

        double tolerance = toleranceForReals;
        assertEquals(result, actual, tolerance);
    }
}
