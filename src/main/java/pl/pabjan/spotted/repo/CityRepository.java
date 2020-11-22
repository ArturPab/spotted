package pl.pabjan.spotted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.spotted.model.City;

@Repository
public interface CityRepository extends JpaRepository<Long, City> {
}
