package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.BddTestConfig;
import dev.config.JpaConfig;

import dev.entite.Plat;

@SpringJUnitConfig({ BddTestConfig.class, JpaConfig.class })
@ActiveProfiles("jpa")

public class PlatRepositoryIntegrationTest {

	@Autowired
	private PlatRepository platrepository;

	

	@Test
	@Transactional
	public void testFindAll() {
	int tailleListe = platrepository.findAll().size();
		System.out.println(tailleListe);
		System.out.println(platrepository.findAll());
		assertThat(tailleListe).isEqualTo(6);
	}
	
	@Test
	public void testFindAll2() {
		List<Plat> p = platrepository.findAll();
		assertThat(p.size()).isEqualTo(6);
	}
	
	
	@Test
	public void testFindAllSortAsc() {
		List<Plat> p =platrepository.findAll(Sort.by(Sort.Direction.ASC,"prixEnCentimesEuros"));
		
	}
	
	@Test
	@Transactional
	public void testFindAllSortAsc2() {
	int tailleListe = platrepository.findAll().size();
		System.out.println(tailleListe);
		System.out.println(platrepository.findAll(Sort.by(Sort.Direction.ASC,"prixEnCentimesEuros")));
		assertThat(tailleListe).isEqualTo(6);
	}
	
	
	

}
