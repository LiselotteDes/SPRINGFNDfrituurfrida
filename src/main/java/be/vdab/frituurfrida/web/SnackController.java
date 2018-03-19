package be.vdab.frituurfrida.web;

import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.services.SnackService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("snacks")
class SnackController {
	private final static char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final static String ALFABET_VIEW = "alfabet";
	private final static String BEGIN_NAAM_VIEW = "beginnaam";
	private final static String WIJZIGEN_VIEW = "wijzigen";
	private final static String REDIRECT_URL_NA_WIJZIGEN = "redirect:/";
	private final SnackService snackService;
	SnackController(SnackService snackService) {
		this.snackService = snackService;
	}
	@GetMapping("alfabet")
	ModelAndView beginletters() {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET);
	}
	@GetMapping("{beginLetter}")
	ModelAndView findByBeginletter(@PathVariable char beginLetter) {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET)
				.addObject("snacks", snackService.findByBeginNaam(String.valueOf(beginLetter)));
	}
	@GetMapping("beginnaam")
	ModelAndView beginNaam() {
		return new ModelAndView(BEGIN_NAAM_VIEW).addObject(new BeginNaamForm());
	}
	@GetMapping(params = "beginnaam")
	ModelAndView beginnaam(@Valid BeginNaamForm form, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(BEGIN_NAAM_VIEW);
		if (bindingResult.hasErrors()) {
			return modelAndView;
		}
		List<Snack> snacks = snackService.findByBeginNaam(form.getBeginnaam());
		if (snacks.isEmpty()) {
			bindingResult.reject("geenSnacks");
		} else {
			modelAndView.addObject("snacks", snacks);
		}
		return modelAndView;
	}
	@GetMapping("{id}/wijzigen")
	ModelAndView wijzigen(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(WIJZIGEN_VIEW);
		snackService.read(id).ifPresent(snack -> modelAndView.addObject(snack));	// !!!
		return modelAndView;
	}
	@PostMapping("{id}/wijzigen")
//	ModelAndView wijzigen(@Valid Snack snack, BindingResult bindingResult) {
	// Omdat er geen data toegevoegd wordt, kunnen we beter (= simpeler) een String gebruiken als returntype.
	// Spring maakt een Snack object en vult dit aan de hand van de invoervakken in de form en de path variabele id.
	String wijzigen(@Valid Snack snack, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
//			return new ModelAndView(WIJZIGEN_VIEW);
			return WIJZIGEN_VIEW;
		}
		snackService.update(snack);
//		return new ModelAndView(REDIRECT_URL_NA_WIJZIGEN);
		return REDIRECT_URL_NA_WIJZIGEN;
	}
}
