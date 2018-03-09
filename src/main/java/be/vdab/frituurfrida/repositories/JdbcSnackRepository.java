package be.vdab.frituurfrida.repositories;
import be.vdab.frituurfrida.entities.Snack;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class JdbcSnackRepository implements SnackRepository {
	// *** MEMBER-VARIABELEN ***
	private final NamedParameterJdbcTemplate template;
	private final RowMapper<Snack> snackRowMapper = (resultSet, rowNum) -> new Snack(
			resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getBigDecimal("prijs"));
	private static final String SQL_READ = "select id, naam, prijs from snacks where id = :id";
	private static final String SQL_UPDATE_SNACK = "update snacks set naam = :naam, prijs = :prijs where id = :id";
	private static final String SQL_SELECT_BY_BEGIN_NAAM = "select * from snacks where naam like :begin";
	// *** CONSTRUCTOR ***
	JdbcSnackRepository(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	// *** TE IMPLEMENTEREN METHODS ***
	@Override
	public Optional<Snack> read(long id) {
		try {
			return Optional.of(template.queryForObject(SQL_READ, Collections.singletonMap("id", id), snackRowMapper));
		} catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
	@Override
	public void update(Snack snack) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", snack.getId());
		parameters.put("naam", snack.getNaam());
		parameters.put("prijs", snack.getPrijs());
		template.update(SQL_UPDATE_SNACK, parameters);
	}
	@Override
	public List<Snack> findByBeginNaam(String beginNaam) {
		return template.query(SQL_SELECT_BY_BEGIN_NAAM, Collections.singletonMap("begin", beginNaam + '%'), snackRowMapper);
	}
}
