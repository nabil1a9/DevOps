package tn.esprit.kaddemproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.kaddemproject.repositories.DepartementRepository;

@SpringBootTest
class KaddemProjectApplicationTests {
    @MockBean
    private DepartementRepository departementRepository; // Mock the repository

    @Test
    void contextLoads() {
    }

}
