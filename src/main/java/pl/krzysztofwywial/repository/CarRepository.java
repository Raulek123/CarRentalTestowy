package pl.krzysztofwywial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.krzysztofwywial.model.CarEntity;

import javax.persistence.Entity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {
}
