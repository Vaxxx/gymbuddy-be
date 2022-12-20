package ng.com.createsoftware.gymbuddybe.repository;

import ng.com.createsoftware.gymbuddybe.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
