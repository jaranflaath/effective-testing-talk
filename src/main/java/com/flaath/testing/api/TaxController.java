package com.flaath.testing.api;

import com.flaath.testing.data.TaxDataRepository;
import com.flaath.testing.domain.TaxResult;
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
        if (income >= 1000000) {
            return new TaxResult(id, 0.01);
        }

        return new TaxResult(id, 0.50);
    }

}
