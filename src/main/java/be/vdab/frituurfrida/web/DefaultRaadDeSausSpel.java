package be.vdab.frituurfrida.web;
import java.io.Serializable;
import java.util.stream.IntStream;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import be.vdab.frituurfrida.services.SausService;

@Component
@SessionScope
class DefaultRaadDeSausSpel implements RaadDeSausSpel, Serializable {
	private final static long serialVersionUID = 1L;
	private final static int MAX_BEURTEN = 10;		// !!!
	private final SausService sausService;
	private int aantalVerkeerdePogingen;
	private String saus;
//	private String sausMetPuntjes;
	private StringBuilder sausMetPuntjes;					// !!! beter dan gewone String
//	private boolean gewonnen;
//	/*
//	 * Volgende variabele had ik niet, maar dit is nodig om expliciet iets op true te kunnen zetten. Anders is gewonnen altijd false,
//	 * maar kan je niet expliciet testen of verloren true is, zoals het geval is na 10 verkeerde beurten.
//	 */
//	private boolean verloren;						// !!! (allebei toch niet nodig)
	DefaultRaadDeSausSpel(SausService sausService) {
		this.sausService = sausService;
		reset();
	}
	@Override
	public String getSaus() {
		return saus;
	}
	@Override
	public String getSausMetPuntjes() {
		return sausMetPuntjes.toString();
	}
	@Override
	public boolean isGewonnen() {
//		return gewonnen;
		return sausMetPuntjes.indexOf(".") == -1;
	}
	@Override
	public boolean isVerloren() {
//		return verloren;
		return aantalVerkeerdePogingen == MAX_BEURTEN;
	}
	@Override
	public int getAantalVerkeerdePogingen() {
		return aantalVerkeerdePogingen;
	}
	@Override
	public void raadLetter(char letter) {
//		boolean geraden = false;
//		char[] puntjesArray = sausMetPuntjes.toCharArray();
//		for (int i=0; i!= saus.length(); i++) {
//			if (letter == saus.charAt(i)) {
//				puntjesArray[i] = letter;
//				geraden = true;
//			}
//		}
//		if (geraden) {
//			sausMetPuntjes = new String(puntjesArray);
////			if (! sausMetPuntjes.chars().anyMatch(karakter -> karakter == '.')) {
////				gewonnen = true;
////			}
//		} else {
////			if (++aantalVerkeerdePogingen == MAX_BEURTEN) {
////				verloren = true;
////			}
//			aantalVerkeerdePogingen++;
//		}
		int letterIndex = saus.indexOf(letter);
		if (letterIndex == -1) {
			aantalVerkeerdePogingen++;
		} else {
			do {
				sausMetPuntjes.setCharAt(letterIndex, letter);
				letterIndex = saus.indexOf(letter, letterIndex + 1);
			} while (letterIndex != -1);
		}
	}
	@Override
	public void reset() {
		int randomId = new Random().nextInt(sausService.findAll().size());
		saus = sausService.findAll().get(randomId).getNaam();
		// Met String werken
//		sausMetPuntjes = "";
//		for (int i = 0; i != saus.length(); i++) {
//			sausMetPuntjes += ".";
//		}
		// Met StringBuilder werken 		// !!!
		sausMetPuntjes = new StringBuilder(saus.length());
		IntStream.rangeClosed(1, saus.length()).forEach(index -> sausMetPuntjes.append('.'));
		aantalVerkeerdePogingen = 0;
	}
}
