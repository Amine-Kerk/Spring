package dao.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;

import dev.exception.PlatException;
import dev.service.PlatServiceVersion2;

@ExtendWith(SpringExtension.class) // déléger à spring
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class }) // config du context spring
@ActiveProfiles({ "service2", "memoire" })
public class PlatServiceVersion2IntegrationTest {

	@Autowired
	private PlatServiceVersion2 service2;

	@Test
	public void ajouterPlat() {
		service2.ajouterPlat("merguez", 5000);
	}

	@Test
	public void ajouterPlatPrixInvalid() throws PlatException {
		assertThrows(PlatException.class, () -> service2.ajouterPlat("merguez", 2));
	}

}
