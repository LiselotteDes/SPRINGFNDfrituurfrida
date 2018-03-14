package be.vdab.frituurfrida.web;
import java.io.Serializable;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
public class DefaultZoekDeFrietSpel implements Serializable, ZoekDeFrietSpel {
	private static final long serialVersionUID = 1L;
	private static final int AANTAL_DEUREN = 7;					// !
	private final Deur[] deuren = new Deur[AANTAL_DEUREN];
	public DefaultZoekDeFrietSpel() {
		resetDeuren();
	}
	@Override
	public void openDeur(int index) {
		deuren[index].open();
	}
	@Override
	public Deur[] getDeuren() {
		return deuren;
	}
	@Override
	public void resetDeuren() {
		int indexMetFriet = new Random().nextInt(AANTAL_DEUREN);
		for(int i=0; i<AANTAL_DEUREN; i++) {
//			if(i == indexMetFriet) {
//				deuren[i] = new Deur(true);
//			} else {
//				deuren[i] = new Deur(false);
//			}
			deuren[i] = new Deur(i == indexMetFriet);
		}
	}
}
