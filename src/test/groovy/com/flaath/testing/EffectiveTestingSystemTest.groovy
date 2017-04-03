package com.flaath.testing

import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate


class EffectiveTestingSystemTest extends EffectiveTestingTestDefs {

    RestTemplate restTemplate = new RestTemplate()


    def setup() {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {

            @Override
            boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return true
            }


            @Override
            void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        })
    }


    @Override
    RestTemplate restTemplate() {
        return restTemplate
    }


    @Override
    String applicationUri() {
        return "http://localhost:8080"
    }
}
