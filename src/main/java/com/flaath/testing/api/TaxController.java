package com.flaath.testing.api;

import com.flaath.testing.data.TaxDataRepository;
import com.flaath.testing.domain.TaxResult;
import com.flaath.testing.taxcalculation.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class TaxController {

    @Autowired
    private TaxDataRepository repository;


    @RequestMapping("/{id}")
    @ResponseBody
    public TaxResult calculate(@PathVariable String id) {

        Integer income = repository.lookupIncome(id);
        if (income == null) {
            throw new IllegalArgumentException("Invalid id");
        }

        return TaxCalculator.calculate(id, income);
    }

}
