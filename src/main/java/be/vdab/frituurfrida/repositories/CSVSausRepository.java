package be.vdab.frituurfrida.repositories;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;

@Repository
class CSVSausRepository implements SausRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(SausRepository.class);
	private static final Path SAUZEN_PATH = Paths.get("/data/sauzen.csv");
	@Override
	public List<Saus> findAll() {
		List<Saus> sauzen = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(SAUZEN_PATH)) {
			for (String regel; (regel = reader.readLine()) != null;) {
				long id = Long.parseLong(regel.split(",")[0]);
				String naam = regel.split(",")[1];
				int positieTweedeKomma = regel.indexOf(",", regel.indexOf(naam));
				List<String> ingredienten = Arrays.asList(
						regel.substring(positieTweedeKomma + 1).split(","));
				sauzen.add(new Saus(id, naam, ingredienten)); 
			}
			return sauzen;
		} catch (IOException ex) {
			String fout = "kan sauzen niet lezen";
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
	}

}
