package dev;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.entite.Plat;

public class AppJpa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1 instance EntityManagerFactory au démarrage pour toute l'application
        // n instances EntityManager (durée de vie courte)

        // Etape 1 - Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("plat-unit");

        // Etape 2 - Création EntityManager
        EntityManager em = emf.createEntityManager();

        TypedQuery<Plat> query = em.createQuery("select p from Plat p",Plat.class);

        List<Plat> listePlats = query.getResultList();

        for (Plat p: listePlats) {
            System.out.println(p.getNom() + " - " + p.getPrixEnCentimesEuros());
        }

        Plat plat = new Plat();
        plat.setNom("couscousroyal");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(plat);

        transaction.commit();

        em.close();
        emf.close();
    }

	}



