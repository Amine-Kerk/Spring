package dev.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.service.PlatServiceVersion2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoJdbc.class }) // config du context spring
@ActiveProfiles({ "service2", "jdbc" })
class PlatDaoJdbcIntegrationTest {

	@Autowired
	private PlatServiceVersion2 service2;

	@Test
	public void listerPlatsNonVide() {
	
		
	}

	@Test
	public void ajouterPlat() {
	}

}
