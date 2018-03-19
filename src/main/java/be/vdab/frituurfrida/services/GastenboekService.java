package be.vdab.frituurfrida.services;
import java.util.List;
import be.vdab.frituurfrida.entities.GastenboekEntry;
public interface GastenboekService {
	void create(GastenboekEntry entry);
	List<GastenboekEntry> findAll();
}
