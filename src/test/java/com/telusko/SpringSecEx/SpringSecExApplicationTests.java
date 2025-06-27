package com.telusko.SpringSecEx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringSecExApplicationTests {

    @Test
    void contextLoads() {
        // If this passes, your context and H2 DB started successfully
    }

}
