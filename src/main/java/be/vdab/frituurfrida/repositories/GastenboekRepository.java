package be.vdab.frituurfrida.repositories;
import java.util.List;
import be.vdab.frituurfrida.entities.GastenboekEntry;

public interface GastenboekRepository {
	void create(GastenboekEntry entry);
	List<GastenboekEntry> findAll();
}
