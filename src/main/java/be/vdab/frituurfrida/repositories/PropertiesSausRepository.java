package be.vdab.frituurfrida.repositories;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;
@Repository
@Qualifier("Properties")
class PropertiesSausRepository implements SausRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesSausRepository.class);
	private static final Path PAD = Paths.get("/data/sauzen.properties");
	@Override
	public List<Saus> findAll() {
		List<Saus> sauzen = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(PAD)) {
			for (String regel; (regel = reader.readLine()) != null; ) {
				if (! regel.isEmpty()) {	// blanko regel overslaan
					String[] entry = regel.split(":");
					if (entry.length < 2) {
						String fout = PAD + ":" + regel + " bevat minder dan 2 onderdelen";
						LOGGER.error(fout);
						throw new SausRepositoryException(fout);
					}
					try {
						long id = Long.parseLong(entry[0]);
						String[] onderdelen = entry[1].split(",");
						Saus saus = new Saus(id, onderdelen[0]);
						for (int i = 1; i < onderdelen.length; i++) {
							saus.addIngredient(onderdelen[i].trim());
						}
						sauzen.add(saus);
					} catch (NumberFormatException ex) {
						String fout = PAD + ":" + regel + " bevat verkeerd id";
						LOGGER.error(fout, ex);
						throw new SausRepositoryException(fout);
					}
				}
			}
		} catch (IOException ex) {
			String fout = "Fout bij het lezen van " + PAD;
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
		return sauzen;
	}
}
