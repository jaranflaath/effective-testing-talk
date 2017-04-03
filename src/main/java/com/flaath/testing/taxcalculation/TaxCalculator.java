package com.flaath.testing.taxcalculation;

import com.flaath.testing.domain.TaxResult;

public class TaxCalculator {

    public static TaxResult calculate(String id, Integer income) {

        if (income >= 1000000) {
            return new TaxResult(id, 0.01);
        }

        return new TaxResult(id, 0.50);
    }
}
