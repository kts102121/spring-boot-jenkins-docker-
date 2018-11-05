package org.kts1021.docker.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kts1021.docker.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testHello() {
        webTestClient
                    .get().uri("/hello")
                    .accept(MediaType.TEXT_PLAIN)
                    .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testConnection() {
        Assert.notNull(userMapper.findByUsername("ksh10211"), "Is null?");
    }
}
