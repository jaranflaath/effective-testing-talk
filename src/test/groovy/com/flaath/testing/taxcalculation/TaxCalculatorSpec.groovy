package com.flaath.testing.taxcalculation

import spock.lang.Specification


class TaxCalculatorSpec extends Specification {

    def "calculate with income = 1m gives low tax rate"() {

      expect:
        TaxCalculator.calculate("1", 1000000).taxRate == 0.01

    }


    def "calculate with income > 1m gives low tax rate"() {

      expect:
        TaxCalculator.calculate("2", 1500000).taxRate == 0.01

    }


    def "calculate with income < 1m gives low tax rate"() {

      expect:
        TaxCalculator.calculate("3", 300000).taxRate == 0.5

    }
}
