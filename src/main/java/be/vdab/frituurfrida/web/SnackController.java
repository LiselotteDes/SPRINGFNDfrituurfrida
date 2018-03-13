package be.vdab.frituurfrida.web;

import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.services.SnackService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("snacks")
class SnackController {
	private final static char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final static String ALFABET_VIEW = "alfabet";
	private final static String BEGIN_NAAM_VIEW = "beginnaam";
	private final SnackService snackService;
	SnackController(SnackService snackService) {
		this.snackService = snackService;
	}
	@GetMapping
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
}
