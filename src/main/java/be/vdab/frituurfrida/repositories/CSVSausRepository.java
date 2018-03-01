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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;

/*
 * Repository is (als speciale Component) niet fout, maar heeft niet zo veel nut.
 * In classes die een db aanspreken heeft dit wel nut, door de vertaalslag van 
 * opgeworpen JDBC exceptions.
 * Hier wordt geen db benaderd maar een csv-bestand.
 * Het mag echter blijven staan.
 */
@Repository
@Qualifier("CSV")
class CSVSausRepository implements SausRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVSausRepository.class);
//	private static final Path SAUZEN_PATH = Paths.get("/data/sauzen.csv");
	private final Path pad;		// Waarom is dit niet meer static?
	CSVSausRepository(@Value("${CSV}") Path pad) {
		this.pad = pad;
	}
	@Override
	public List<Saus> findAll() {
		List<Saus> sauzen = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(pad)) {
			for (String regel; (regel = reader.readLine()) != null;) {
				if (!regel.isEmpty()) {		// blanco regel overslaan
					String[] onderdelen = regel.split(",");
					if (onderdelen.length < 2) {
						String fout = pad + ":" + regel +" bevat minder dan 2 onderdelen";
						LOGGER.error(fout);
						throw new SausRepositoryException(fout);
					}
					try {
						long id = Long.parseLong(onderdelen[0]);
						String naam = onderdelen[1];
						Saus saus = new Saus(id, naam);
						for (int index = 2; index < onderdelen.length; index++) {
							saus.addIngredient(onderdelen[index]);
						}
						sauzen.add(saus);
					} catch (NumberFormatException ex) {
						String fout = pad + ":" + regel + " bevat verkeerd id";
						LOGGER.error(fout, ex);
						throw new SausRepositoryException(fout);
					}
				}
			}
		} catch (IOException ex) {
			String fout = "Fout bij lezen " + pad;
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
		return sauzen;
	}
}
