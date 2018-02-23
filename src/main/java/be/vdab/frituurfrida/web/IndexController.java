package be.vdab.frituurfrida.web;

import java.time.LocalDate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.valueobjects.Adres;
import be.vdab.frituurfrida.valueobjects.Gemeente;

@Controller
@RequestMapping("/")
class IndexController {
	@GetMapping
	ModelAndView index(@CookieValue(name="bezocht", required=false)
						String bezocht, HttpServletResponse response) {
		int dag = LocalDate.now().getDayOfWeek().getValue();
		String sluitingsdag = (dag == 1 || dag == 4) ? "gesloten" : "open";
		Cookie cookie = new Cookie("bezocht", "ja");
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		ModelAndView modelAndView = new ModelAndView("index", "sluitingsdag", sluitingsdag)
				.addObject("adres", new Adres("Veemarkt", "4", new Gemeente("Kortrijk", 8500)));
		if (bezocht != null) {
			modelAndView.addObject("bezocht", true);
		}
		return modelAndView;
	}
}
