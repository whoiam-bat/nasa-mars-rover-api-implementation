package ua.com.demo.spacey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.demo.spacey.model.Preference;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Integer> {

    @Query(value = "SELECT p FROM preference p " +
            "LEFT JOIN customer_preference cp ON p.preference_id = cp.preference_id " +
            "WHERE CP.customer_id = ?1", nativeQuery = true)
    List<Preference> findAllByCustomerId(int customerId);
}
