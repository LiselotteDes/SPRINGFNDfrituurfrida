package be.vdab.frituurfrida.web;

class RaadDeSausForm {
	/*
	 * Eerst had ik char als type: werkt ook.
	 * Waarom Character? Omdat char niet op null kan staan. Heeft altijd een beginwaarde (zoals int 0 is): '\u0000'.
	 * Dit effect zie je als je eerst naar de website gaat: er staat een vraagtekentje in het vak.
	 * Dit kan je vermijden door een wrappertype te nemen voor je formulieren !
	 */
	private Character letter = ' ';

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}
}
