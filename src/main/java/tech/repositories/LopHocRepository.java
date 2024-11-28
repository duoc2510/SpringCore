package tech.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.entities.LopHoc;
import tech.entities.User;

public interface LopHocRepository extends JpaRepository<LopHoc, Integer> {

    List<LopHoc> findAll();

    List<LopHoc> findByHopDongsUserId(Integer userId);

}
