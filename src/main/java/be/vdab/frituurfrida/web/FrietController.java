package be.vdab.frituurfrida.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("frieten")
class FrietController {
	private ZoekDeFrietSpel zoekDeFrietSpel;
	FrietController(ZoekDeFrietSpel zoekDeFrietSpel) {
		this.zoekDeFrietSpel = zoekDeFrietSpel;
	}
	private static final String ZOEK_DE_FRIET_VIEW = "zoekdefriet";
	@GetMapping("zoekdefriet")
	ModelAndView zoekDeFriet() {
		return new ModelAndView(ZOEK_DE_FRIET_VIEW, "spel", zoekDeFrietSpel);
	}
	private static final String REDIRECT_NAAR_ZOEKDEFRIET = "redirect:/frieten/zoekdefriet";
	@PostMapping(value="zoekdefriet", params="index")
	String deurOpenen(int index) {					// ! Vergeet niet de parameter die je doorkrijgt in de method header op te nemen.
		this.zoekDeFrietSpel.openDeur(index);
		/*
		 * ! Je kan een String doorgeven en je niet bekommeren om het ZoekDeFrietSpel object dat je wil meegeven.
		 * Waarom? Je voert een REDIRECT uit!
		 * Dus er volgt automatisch een GET request naar de redirect url en zo komen we weer bij de method zoekDeFriet() 
		 * die dit object meegeeft.
		 * We moeten het voor het return statement met de url dus enkel aanpassen, het doorgeven is hier niet.
		 */
		return REDIRECT_NAAR_ZOEKDEFRIET;				
	}
	@PostMapping("zoekdefriet/nieuwspel")
	String spelResetten() {
		this.zoekDeFrietSpel.resetDeuren();
		return REDIRECT_NAAR_ZOEKDEFRIET;
	}
}
