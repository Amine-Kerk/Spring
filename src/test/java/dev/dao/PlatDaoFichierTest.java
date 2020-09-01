package dev.dao;



import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

@SpringJUnitConfig(PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlatDaoFichierTest {

	@Autowired
	private PlatDaoFichier platDaoFichier;

	@Test
	public void ajoutPlat() {

		platDaoFichier.ajouterPlat("filet de spring ", 10000);
		List<Plat> list = platDaoFichier.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

	@Test
	public void TestAjoutPlatBis() {
		platDaoFichier.ajouterPlat("filet de spring", 10000);
		List<Plat> list = platDaoFichier.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

}
