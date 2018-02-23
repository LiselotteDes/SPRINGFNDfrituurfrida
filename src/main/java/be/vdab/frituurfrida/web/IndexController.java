package be.vdab.frituurfrida.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.valueobjects.Adres;
import be.vdab.frituurfrida.valueobjects.Gemeente;

@Controller
@RequestMapping("/")
class IndexController {
	@GetMapping
	ModelAndView index() {
		int dag = LocalDate.now().getDayOfWeek().getValue();
		String sluitingsdag = (dag == 1 || dag == 4) ? "gesloten" : "open";
		return new ModelAndView("index", "sluitingsdag", sluitingsdag)
				.addObject("adres", new Adres("Veemarkt", "4", new Gemeente("Kortrijk", 8500)));
	}
}