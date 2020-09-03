package dev.dao;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.BddTestConfig;
import dev.config.JpaConfig;

@SpringJUnitConfig({
		BddTestConfig.class, // DataSource H2, JdbcTemplate
		JpaConfig.class, // EntityManagerFactory, JpaTransaction, @Transactional
		PlatDaoJpa.class // la classe à tester
})
@ActiveProfiles("jpa")
public class PlatDaoJpaIntregrationTest extends BasePlatDaoBddIntegrationTest{

}
