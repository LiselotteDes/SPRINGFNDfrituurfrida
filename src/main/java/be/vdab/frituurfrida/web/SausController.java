package be.vdab.frituurfrida.web;
import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.services.SausService;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	private final List<Saus> sauzen;
	SausController(SausService sausService) {
		this.sauzen = sausService.findAll();
	}
	@GetMapping
	ModelAndView sauzen() {
		return new ModelAndView(SAUZEN_VIEW, "sauzen", sauzen);
	}
}
