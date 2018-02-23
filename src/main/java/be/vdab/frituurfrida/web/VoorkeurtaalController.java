package be.vdab.frituurfrida.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("voorkeurtaal")
class VoorkeurtaalController {
	private final static String VIEW = "voorkeurtaal";
	@GetMapping
	ModelAndView inNederlands(@RequestHeader("accept-language") String acceptLanguage) {
		boolean isNederlands = acceptLanguage.startsWith("nl,") || acceptLanguage.startsWith("nl-");
		return new ModelAndView(VIEW, "inNederlands", isNederlands);
	}
}
