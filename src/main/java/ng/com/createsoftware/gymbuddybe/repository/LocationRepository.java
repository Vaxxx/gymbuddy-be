package ng.com.createsoftware.gymbuddybe.repository;

import ng.com.createsoftware.gymbuddybe.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
