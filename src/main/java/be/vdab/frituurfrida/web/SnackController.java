package be.vdab.frituurfrida.web;
import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("snacks")
class SnackController {
	private final static char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final static String ALFABET_VIEW = "alfabet";
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
}
