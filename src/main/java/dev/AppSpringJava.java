package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import dev.config.AppConfig;
import dev.ihm.Menu;

public class AppSpringJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Création du contexte Spring à partir d'une configuration Java
	try (	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)){
		  // récupération du bean Menu
		  Menu menu = context.getBean(Menu.class);
		  menu.afficher();
		  // fermeture du Scanner
		  context.getBean(Scanner.class).close();
		  // fermeture du contexte Spring
		  context.close();
	} catch (DataAccessException e) {
		System.out.println("Problème d'accès à la base de données");
	}
	}

}
