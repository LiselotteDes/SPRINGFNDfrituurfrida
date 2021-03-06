package be.vdab.frituurfrida.web;
import be.vdab.frituurfrida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sauzen")
class SausController {
	private final static String SAUZEN_VIEW = "sauzen";
//	private final List<Saus> sauzen = Arrays.asList(
//			new Saus(1L, "cocktail", Arrays.asList("mayo", "ketchup")),
//			new Saus(2L, "mayonaise", Arrays.asList("ei", "olie", "azijn")),
//			new Saus(3L, "mosterd", Arrays.asList("mosterdzaadjes")),
//			new Saus(4L, "tartare", Arrays.asList("mayo", "kruiden")),
//			new Saus(5L, "vinaigrette", Arrays.asList("olie", "azijn")));
//	private List<Saus> sauzen;
	private final SausService sausService;
	private final RaadDeSausSpel raadDeSausSpel;
	SausController(SausService sausService, RaadDeSausSpel raadDeSausSpel) {
		this.sausService = sausService;
		this.raadDeSausSpel = raadDeSausSpel;
	}
	@GetMapping
	ModelAndView sauzen() {
//		sauzen = sausService.findAll();
		return new ModelAndView(SAUZEN_VIEW, "sauzen", sausService.findAll());
	}
	// ************** Saus Raden Spel **************
	private final static String RAAD_DE_SAUS_VIEW = "raaddesaus";
	@GetMapping("raaddesaus")
	ModelAndView raadDeSaus() {
		return new ModelAndView(RAAD_DE_SAUS_VIEW, "spel", raadDeSausSpel)
				.addObject(new RaadDeSausForm());
	}
	private final static String REDIRECT_URL_NA_RADEN = "redirect:/sauzen/raaddesaus";
//	@PostMapping("raaddesaus")	// Zo had ik het eerst en dit werkte ook ??
	@PostMapping(value="raaddesaus", params="letter")
	String raden(RaadDeSausForm form) {
		this.raadDeSausSpel.raadLetter(form.getLetter());
		return REDIRECT_URL_NA_RADEN;
	}
	
	private final static String REDIRECT_URL_NA_RESET = "redirect:/sauzen/raaddesaus";
	@PostMapping("raaddesaus/nieuwspel")
	String resetten() {
		this.raadDeSausSpel.reset();
		return REDIRECT_URL_NA_RESET;
	}
}
