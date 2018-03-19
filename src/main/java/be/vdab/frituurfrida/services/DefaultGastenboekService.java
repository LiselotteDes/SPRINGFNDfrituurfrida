package be.vdab.frituurfrida.services;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import be.vdab.frituurfrida.entities.GastenboekEntry;

@Service
@Transactional(readOnly=true, isolation = Isolation.READ_COMMITTED)
class DefaultGastenboekService implements GastenboekService {
	
}
