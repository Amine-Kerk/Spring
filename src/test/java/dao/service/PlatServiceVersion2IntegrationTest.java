package dao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
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
		List<Plat> list = service2.listerPlats();
		assertThat(list.size()).isEqualTo(1);
	}

	@Test
	public void ajouterPlatPrixInvalid() {
		assertThatThrownBy(
				() -> service2.ajouterPlat("merguez", 2)).isInstanceOf(PlatException.class)
		.hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
