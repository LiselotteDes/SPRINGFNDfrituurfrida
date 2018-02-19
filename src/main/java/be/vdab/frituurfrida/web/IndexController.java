package be.vdab.frituurfrida.web;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class IndexController {
	@GetMapping
	String index() {
		int dag = LocalDate.now().getDayOfWeek().getValue();
		return (dag == 1 || dag == 4) ? "gesloten" : "open";
	}
}
