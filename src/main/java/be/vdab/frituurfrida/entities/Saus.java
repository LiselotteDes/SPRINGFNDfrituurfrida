package be.vdab.frituurfrida.entities;
import java.util.ArrayList;
import java.util.List;

public class Saus {
	private long id;
	private String naam;
	private List<String> ingredienten = new ArrayList<>();
	// Constructor Toevoegen: (ontbrak): Maak een saus door enkel id en naam mee te geven.
	public Saus(long id, String naam) {
		this.id = id;
		this.naam = naam;
	}
	public Saus(long id, String naam, List<String> ingredienten) {
		// Maakt gebruik van vorige constructor
		this(id, naam);
		// this.ingredienten = ingredienten;		FOUT
		this.ingredienten.addAll(ingredienten);		// CORRECT
	}
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public List<String> getIngredienten() {
		return ingredienten;
	}
	// Voegt één ingredient toe
	public void addIngredient(String ingredient) {
		ingredienten.add(ingredient);
	}
}
