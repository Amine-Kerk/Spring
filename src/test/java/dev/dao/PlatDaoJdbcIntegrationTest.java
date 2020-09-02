package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;


import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;



@SpringJUnitConfig({JdbcTestConfig.class,PlatDaoJdbc.class})


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { JdbcTestConfig.class, PlatDaoJdbc.class }) // config du context spring


@ActiveProfiles("jdbc")
class PlatDaoJdbcIntegrationTest {

	@Autowired
	private PlatDaoJdbc platDaojdbc ;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	public void listerPlatsNonVide() {
	
		List<Plat>plats =platDaojdbc.listerPlats();
		assertThat(plats).isNotEmpty();
	}

	@Test
	public void ajouterPlat() {
		platDaojdbc.ajouterPlat("Tartiflette", 1500);

        Integer prix = jdbcTemplate.queryForObject("select prix from plat where nom='Tartiflette'", Integer.class);

        assertThat(prix).isEqualTo(1500);

        //assertThat(platDaoJDBC.listerPlats()).extracting(Plat::getNom).contains("Tartiflette");

	}

}
