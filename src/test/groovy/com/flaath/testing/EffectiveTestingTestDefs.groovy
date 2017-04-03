package com.flaath.testing

import groovy.json.JsonSlurper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification


abstract class EffectiveTestingTestDefs extends Specification {


    def "GET /calculate/ with valid ID returns tax rate"() {

      given:
        def id = "1234567890"

      when:
        ResponseEntity<String> jsonResponse = restTemplate().getForEntity("${applicationUri()}/calculate/$id", String)
        def json = new JsonSlurper().parseText(jsonResponse.body)

      then:
        json.id == id
        json.taxRate != null
    }


    def "GET /calculate/ with missing ID returns 404"() {

      given:

      when:
        ResponseEntity<String> jsonResponse = restTemplate().getForEntity("${applicationUri()}/calculate/", String)

      then:
        jsonResponse.statusCode == HttpStatus.NOT_FOUND
    }


    def "GET /calculate/ with invalid ID returns 500"() {

      given:

      when:
        ResponseEntity<String> jsonResponse = restTemplate().getForEntity("${applicationUri()}/calculate/NaN", String)

      then:
        jsonResponse.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }


    abstract RestTemplate restTemplate()


    abstract String applicationUri()
}
