package be.vdab.frituurfrida.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import be.vdab.frituurfrida.entities.GastenboekEntry;

@Repository
class JdbcGastenboekRepository implements GastenboekRepository {
	private final NamedParameterJdbcTemplate template;
	private final SimpleJdbcInsert insert;
	private final RowMapper<GastenboekEntry> entryRowMapper = (resultSet, rowNum) -> new GastenboekEntry(
			resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getTimestamp("datumTijd").toLocalDateTime(), 
			resultSet.getString("bericht"));
	private static final String SQL_SELECT_ALL = "select * from gastenboek order by datumtijd desc";
	JdbcGastenboekRepository(DataSource dataSource, NamedParameterJdbcTemplate template) {
		this.template = template;
		this.insert = new SimpleJdbcInsert(dataSource);
		insert.withTableName("gastenboek");
		insert.usingGeneratedKeyColumns("id");
	}
	@Override
	public void create(GastenboekEntry entry) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("naam", entry.getNaam());
		kolomWaarden.put("datumtijd", entry.getDatumTijd());
		kolomWaarden.put("bericht", entry.getBericht());
		Number id = insert.executeAndReturnKey(kolomWaarden);
		// Ook al gebruik je van dit object het id nog niet, het is een goeie gewoonte van het id meteen in te stellen !!
		entry.setId(id.longValue());
	}
	@Override
	public List<GastenboekEntry> findAll() {
		return template.query(SQL_SELECT_ALL, entryRowMapper);
	}
}
