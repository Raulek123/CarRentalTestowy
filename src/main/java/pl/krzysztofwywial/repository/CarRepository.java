package pl.krzysztofwywial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofwywial.model.CarEntity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {
}
