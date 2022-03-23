package com.example.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleControllerTest {

//    @Value("${server.port}")
//    private int port;

    @LocalServerPort
    private int port;

//    @Value("${windir}")
//    private String windir;

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
    @Disabled
    void checkIfEnvIsGatheredFromValueAnnotation() {
//        assertNotNull(windir);
//        System.out.println("windir: " + windir);
    }
}