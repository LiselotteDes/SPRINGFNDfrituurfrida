package be.vdab.frituurfrida.web;

public interface RaadDeSausSpel {
	String getSausMetPuntjes();
	String getSaus();
	void raadLetter(char letter);
//	boolean raadSaus();
	void reset();
	int getAantalVerkeerdePogingen();
//	void toonVerkeerdePogingen(int aantalVerkeerdePogingen);
	boolean isGewonnen();
	boolean isVerloren();
}
