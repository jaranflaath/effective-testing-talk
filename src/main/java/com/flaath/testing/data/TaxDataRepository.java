package com.flaath.testing.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaxDataRepository {

    private Map<String, Integer> incomeRegistry = new HashMap<>();


    public TaxDataRepository() {

        incomeRegistry.put("1234567890", 1500000);
        incomeRegistry.put("0987654321", 300000);
    }


    public Integer lookupIncome(String id) {

        return incomeRegistry.get(id);
    }
}
