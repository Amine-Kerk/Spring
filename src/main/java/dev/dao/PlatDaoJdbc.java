package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;
import dev.entite.PlatRowMapper;

@Repository
//selectionner le profile activé 
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao {
//outils
	private JdbcTemplate jdbcTemplate;
	
//injection par constructeur 	
	
	public PlatDaoJdbc(DataSource datasource) {
		this.jdbcTemplate=new JdbcTemplate(datasource);
		
	}

	@Override
	public List<Plat> listerPlats() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from plat", new PlatRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		// TODO Auto-generated method stub
		 this.jdbcTemplate.update("insert into plat(nom) values(?)", nomPlat);
	}

}
