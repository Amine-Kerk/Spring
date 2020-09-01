package dao.service;


import static org.assertj.core.api.Assertions.anyOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.assertj.core.error.AnyElementShouldMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

import dev.service.PlatServiceVersion1;


class PlatServiceVersion1Test  {

	private IPlatDao dao;
	private PlatServiceVersion1 service1;
	@BeforeEach
	public void setUp() {
	   dao = Mockito.mock(IPlatDao.class);
      service1 = new PlatServiceVersion1(dao); 
}

//un test unitaire de la méthode ajouterPlat avec un nom invalide (moins de 3 caractères).
	//Vérifier qu’une exception est bien lancée.
	
	@Test
	public void ajouterPlatNomInvalide() throws PlatException{
		assertThrows(PlatException.class, ()->service1.ajouterPlat("c", 1500));
		
	}
	
	@Test
	public void ajouterPlatPrixInvalide() throws PlatException {
		assertThrows(PlatException.class, ()->service1.ajouterPlat("merguez", 5));
		
	}
	
	@Test
	public void methodeInvoquee() {
		service1.ajouterPlat("merguez", 600);
		verify(dao).ajouterPlat("merguez",600);
		
	}
}
