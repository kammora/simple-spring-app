package com.example.simple;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleControllerTest {

    @Value("${server.port}")
    private int port;

    @Value("${windir}")
    private String windir;

    @Autowired
    private Environment environment;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    void checkIfIndexResponseContainsHelloMessage() {
        ResponseEntity<String> restTemplateForEntity = testRestTemplate.getForEntity("http://localhost:" + port, String.class);

        assertThat(restTemplateForEntity.getBody()).contains("hello!");
    }

    @Test
    void checkIfBothMethodsOfGettingEnvWork(){
        assertNotEquals(port, Integer.getInteger(environment.getProperty("server.port")));
        assertNotEquals(port, 0);
    }

    @Test
    void checkIfEnvIsGatheredFromValueAnnotation() {
        assertNotNull(windir);
        System.out.println("windir: " + windir);
    }
}