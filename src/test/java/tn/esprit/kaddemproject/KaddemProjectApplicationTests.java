package tn.esprit.kaddemproject;

import org.aspectj.lang.annotation.AfterReturning;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import tn.esprit.kaddemproject.services.IEtudiantService;

@SpringBootTest
class KaddemProjectApplicationTests {
@Autowired
private IEtudiantService service;
    @Test
    @Order(1)
    void contextLoads() {
    }

}
