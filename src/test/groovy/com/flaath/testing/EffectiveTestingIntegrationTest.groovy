package com.flaath.testing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.web.client.RestTemplate


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EffectiveTestingIntegrationTest extends EffectiveTestingTestDefs {

    @Autowired
    TestRestTemplate restTemplate


    @Override
    RestTemplate restTemplate() {
        return restTemplate.getRestTemplate()
    }


    @Override
    String applicationUri() {
        return ""
    }
}
