package be.vdab.frituurfrida.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.frituurfrida.services.SausService;

public class SausControllerTest {
	private SausController controller;
//	private SausService dummySausService;
	@Before
	public void before() {
//		dummySausService = Mockito.mock(SausService.class);
		controller = new SausController(Mockito.mock(SausService.class));
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
