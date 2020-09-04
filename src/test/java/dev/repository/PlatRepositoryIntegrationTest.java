package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.security.auth.Subject;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.BddTestConfig;
import dev.config.JpaConfig;
import dev.entite.Ingredient;
import dev.entite.Plat;

@SpringJUnitConfig({ BddTestConfig.class, JpaConfig.class })
@ActiveProfiles("jpa")

public class PlatRepositoryIntegrationTest {

	@Autowired
	private PlatRepository platRepository;

	

	@Test
	@Transactional
	public void testFindAll() {
	int tailleListe = platRepository.findAll().size();
		System.out.println(tailleListe);
		System.out.println(platRepository.findAll());
		assertThat(tailleListe).isEqualTo(6);
	}
	
//	@Test
//	public void testFindAll2() {
//		List<Plat> p = platRepository.findAll();
//		assertThat(p.size()).isEqualTo(7);
//	}
	
	
	@Test
	public void testFindAllSortAsc() {
		List<Plat> p =platRepository.findAll(Sort.by(Sort.Direction.ASC,"prixEnCentimesEuros"));
		
	}
	
	@Test
	@Transactional
	public void testFindAllSortAsc2() {
	int tailleListe = platRepository.findAll().size();
		//System.out.println(tailleListe);
		System.out.println(platRepository.findAll(Sort.by(Sort.Direction.ASC,"prixEnCentimesEuros")));
		assertThat(tailleListe).isEqualTo(6);
	}
	
	@Test
	void testFindAllPageable() {

		Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "nom"));

		Page<Plat> pagePlat = platRepository.findAll(firstPageWithTwoElements);

		assertThat(pagePlat.getSize()).isEqualTo(2);
		assertThat(pagePlat.getContent().get(0).getId()).isEqualTo(4);

	}

	@Test
	void testFindById() {

		// v1
		Optional<Plat> byId = platRepository.findById(4);

		if(byId.isPresent()) {
			Plat plat1 = platRepository.findById(4).get();
			assertThat(plat1.getNom()).isEqualTo("Blanquette de veau");
			assertThat(plat1.getPrixEnCentimesEuros()).isEqualTo(1500);
		}

		// Plat plat = repo.findById(4).get(); // => ANTI-PATTERN

		Plat plat = platRepository.findById(4).orElseThrow(() -> new RuntimeException("L'id = 4 existe, la requête devrait retourner un plat"));

		assertThat(plat.getNom()).isEqualTo("Blanquette de veau");
		assertThat(plat.getPrixEnCentimesEuros()).isEqualTo(1500);

	}

	@Test
	@Transactional
	void testGetOne() {

		Plat plat = platRepository.getOne(2);

		assertThat(plat.getNom()).isEqualTo("Moules-frites");
		assertThat(plat.getPrixEnCentimesEuros()).isEqualTo(1300);

	}

	@Test
	void testCount() {

		Long total = platRepository.count();

		assertThat(total).isEqualTo(6);

	}

	@Test
	void testFindByPrix() {

		List<Plat> platByPrix = platRepository.findByPrixEnCentimesEuros(1300);

		assertThat(platByPrix.size()).isEqualTo(2);
		assertThat(platByPrix.get(0).getNom()).isEqualTo("Magret de canard");

	}

	@Test
	void testAvgPrix() {

		Double moyenne = platRepository.findByAvgPrixSuppA(0);
		
		

		Double sommeAvecFindAll = 0.0;

		List<Plat> listPlat = platRepository.findAll();

		for (Plat plat : listPlat) {
			sommeAvecFindAll += plat.getPrixEnCentimesEuros();
		}

		double moyenneJava8 = listPlat.stream()
				.mapToInt(Plat::getPrixEnCentimesEuros)
				.average()
				.orElse(0);


		assertThat(moyenne).isEqualTo(sommeAvecFindAll / listPlat.size());
		assertThat(moyenne).isEqualTo(moyenneJava8);
	}

	@Test
	void findIngredientsByPlatNom() {

		List<Ingredient> listIngDePlat = platRepository.findIngredientsByPlatNom("Moules-frites");

		assertThat(listIngDePlat.size()).isEqualTo(6);
		assertThat(listIngDePlat.get(0).getNom()).isEqualTo("Moule");

	}

	@Test
	//@Transactional
	void testFindByNomWithIngredients() {
		Plat plat = platRepository.findByNomWithIngredients("Magret de canard").orElseThrow(() -> new RuntimeException("'Magret de canard' existe !!!"));

		assertThat(plat.getIngredients()).extracting(Ingredient::getNom).contains("Sel");
	}

	@Test
	void testSave() {


		long countBefore = platRepository.count();

		Plat plat = new Plat();
		plat.setNom("Pizza");
		plat.setPrixEnCentimesEuros(1400);

		platRepository.save(plat);

		long countAfter = platRepository.count();
		assertThat(countAfter).isEqualTo(countBefore + 1);

		assertThat(platRepository.findAll()).extracting(Plat::getNom).contains(plat.getNom());
	}

	@Test
	@Transactional
	void testChangerNom() {

		platRepository.updateNomPlat("Couscous", "Couscous-Merguez");

		List<Plat> listPlat = platRepository.findAll();

		assertThat(listPlat).extracting(Plat::getNom).contains("Couscous-Merguez", Index.atIndex(2));

	}


}
