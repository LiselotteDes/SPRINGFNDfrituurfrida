package be.vdab.frituurfrida.repositories;

import static org.junit.Assert.*;

import be.vdab.frituurfrida.entities.Snack;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JdbcSnackRepositoryTest {
	@Autowired
	private JdbcSnackRepository repository;
	@Autowired
	private NamedParameterJdbcTemplate template;
	private long voegSnackToe() {
		template.update("insert into snacks(naam,prijs) values ('integration test', 10)", Collections.emptyMap());
		return template.queryForObject("select max(id) from snacks", Collections.emptyMap(), Long.class);
	}
	@Test
	public void read() {
		long hoogsteId = voegSnackToe();
		assertEquals("integration test", repository.read(hoogsteId).get().getNaam());
	}
	@Test
	public void update() {
		long hoogsteId = voegSnackToe();
		Snack snack = new Snack(hoogsteId, "integration test", BigDecimal.ONE);
		repository.update(snack);
		assertEquals(0, 
				BigDecimal.ONE.compareTo(
						template.queryForObject("select prijs from snacks where id = :id", 
								Collections.singletonMap("id", hoogsteId), BigDecimal.class)));
	}
	@Test
	public void findByBeginNaamGeeftJuisteSnacks() {
		voegSnackToe();
		List<Snack> snacks = repository.findByBeginNaam("i");
		snacks.stream().forEach(snack -> assertTrue(snack.getNaam().startsWith("i")));
	}
	@Test
	public void findByBeginNaamGeeftJuistAantalSnacks() {
		voegSnackToe();
		long aantalSnacks = template.queryForObject("select count(*) from snacks where naam like 'i%'", Collections.emptyMap(), Long.class);
		assertEquals(aantalSnacks, repository.findByBeginNaam("i").size());
	}
}
