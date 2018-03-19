package be.vdab.frituurfrida.entities;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class GastenboekEntry {
	private long id;
	@NotBlank
	@NotNull
	private String naam;
	@NotNull
	@DateTimeFormat(style="S-")
	private LocalDateTime datumTijd;
	@NotBlank
	private String bericht;
	
	public GastenboekEntry(long id, String naam, LocalDateTime datumTijd, String bericht) {
		
	}
	public GastenboekEntry() {
		this.datumTijd = LocalDateTime.now();
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public LocalDateTime getDatumTijd() {
		return datumTijd;
	}
	public String getBericht() {
		return bericht;
	}
	
}
