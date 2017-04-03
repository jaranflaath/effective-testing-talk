package com.flaath.testing

import groovy.json.JsonSlurper
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EffectiveTestingIntegrationTest extends Specification {

    @LocalServerPort
    Integer localServerPort

    TestRestTemplate restTemplate = new TestRestTemplate()


    def "GET /calculate/ with valid ID returns tax rate"() {

      given:
        def id = "1234567890"

      when:
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity("http://localhost:$localServerPort/calculate/$id", String)
        def json = new JsonSlurper().parseText(jsonResponse.body)

      then:
        json.id == id
        json.taxRate != null
    }

    def "GET /calculate/ with missing ID returns 404"() {

      given:

      when:
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity("http://localhost:$localServerPort/calculate/", String)

      then:
        jsonResponse.statusCode == HttpStatus.NOT_FOUND
    }


    def "GET /calculate/ with invalid ID returns 500"() {

      given:

      when:
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity("http://localhost:$localServerPort/calculate/NaN", String)

      then:
        jsonResponse.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }
}
