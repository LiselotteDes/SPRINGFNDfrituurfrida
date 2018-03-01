package be.vdab.frituurfrida.services;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.repositories.SausRepository;

public class SausServiceTest {
	private SausRepository dummySausRepository;
	private SausService sausService;
	@Before
	public void before() {
		dummySausRepository = Mockito.mock(SausRepository.class);
		Mockito.when(dummySausRepository.findAll()).thenReturn(
				Arrays.asList(
						new Saus(1, "cocktail", Arrays.asList("mayo", "ketchup")),
						new Saus(2, "mayonaise", Arrays.asList("ei", "mosterd"))));
		sausService = new DefaultSausService(dummySausRepository);
	}
	@Test
	public void findAll() {
		assertEquals(Arrays.asList(
				new Saus(1, "cocktail", Arrays.asList("mayo", "ketchup")),
				new Saus(2, "mayonaise", Arrays.asList("ei", "mosterd"))), 
				sausService.findAll());
	}

}
