package com.milmile.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest
@AutoConfigureRestTestClient
public class CustomerAPITest {

    @Autowired
    private RestTestClient testClient;

    @Test
    public void customerDetails() {
        this.testClient.get()
                .uri("/api/v1/customers/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Parag")
                .jsonPath("$.favoriteGenre").isEqualTo("Thriller");
    }

    @Test
    public void customerNotFound() {
        this.testClient.get()
                .uri("/api/v1/customers/10")
                .exchange()
                .expectStatus().is4xxClientError();
    }

}