package be.vdab.frituurfrida.web;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SausControllerTest {
	private SausController controller;
	@Before
	public void before() {
		controller = new SausController();
	}
	@Test
	public void sauzenWerktSamenMetDeJuisteJSP() {
		assertEquals("sauzen", controller.sauzen().getViewName());
	}
	@Test
	public void sauzenGeeftJuisteDataAanJSP() {
		assertTrue(controller.sauzen().getModel().containsKey("sauzen"));
	}
}
