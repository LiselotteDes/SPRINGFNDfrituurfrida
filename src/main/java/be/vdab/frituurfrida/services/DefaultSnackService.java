package be.vdab.frituurfrida.services;
import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.repositories.SnackRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultSnackService implements SnackService {
	private final SnackRepository snackRepository;
	DefaultSnackService(SnackRepository snackRepository) {
		this.snackRepository = snackRepository;
	}
	@Override
	public Optional<Snack> read(long id) {
		return snackRepository.read(id);
	}
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Snack snack) {
		snackRepository.update(snack);
	}
	@Override
	public List<Snack> findByBeginNaam(String beginNaam) {
		return snackRepository.findByBeginNaam(beginNaam);
	}
}
