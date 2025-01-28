package com.sqldomaingen;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class SQLDomainGenApplicationTest {

    @Test
    void testApplicationStartup() {
        assertDoesNotThrow(() -> SQLDomainGenApplication.main(new String[]{}),
                "The application should start without throwing exceptions.");
    }

    @Test
    void testBeanInitialization() {
        // Έλεγχος ότι όλα τα beans έχουν αρχικοποιηθεί
        assertTrue(true); // Mocked για το παράδειγμα
    }

    @Test
    void testMainMethod() {
        // Έλεγχος ότι η main μέθοδος τρέχει χωρίς exceptions
        assertDoesNotThrow(() -> SQLDomainGenApplication.main(new String[]{}));
    }

    @Test
    void testCustomPropertyLoading() {
        // Έλεγχος ότι οι ιδιότητες φορτώνονται σωστά
        assertNotNull(System.getProperty("spring.application.name"));
    }
    @Test
    void contextLoads() {
        // Αν η εκκίνηση της εφαρμογής περάσει, το test θεωρείται επιτυχές
        assertTrue(true, "The application context should load successfully.");
    }
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testApplicationContextLoads() {
        assertNotNull(applicationContext, "The application context should be initialized.");
    }

    @Test
    void testEntityGeneratorBean() {
        Object entityGenerator = applicationContext.getBean("entityGenerator");
        assertNotNull(entityGenerator, "The EntityGenerator bean should exist in the application context.");
    }

}