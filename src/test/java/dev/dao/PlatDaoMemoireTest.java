package dev.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;

	// TODO
	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	// TODO
	@Test
	void listerPlatsVideALInitialisation() {
		// TODO
		List<Plat> resultat = platDaoMemoire.listerPlats();
		assertThat(resultat).isEmpty();
		
	}

	// TODO
	@Test
	void ajouterPlatCasPassants() {
		// TODO
		List<Plat> resultatAvant = platDaoMemoire.listerPlats();
		assertThat(resultatAvant.size()).isEqualTo(0);
		//ajout dun plat de test
		platDaoMemoire.ajouterPlat("couscous", 1500);
		
		List<Plat> resultatApres= platDaoMemoire.listerPlats();
		assertThat(resultatApres.size()).isEqualTo(1);
		assertThat(resultatApres).extracting(Plat::getNom).containsAnyOf("couscous");
		assertThat(resultatApres).extracting(Plat::getPrixEnCentimesEuros).containsAnyOf(1500);
		
		assertThat(resultatApres.get(0).getNom()).isEqualTo("couscous");
		
		
	
		
	
		
	}
}
