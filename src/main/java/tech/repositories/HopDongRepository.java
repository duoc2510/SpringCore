package tech.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.entities.HopDong;
import tech.entities.LopHoc;
import tech.entities.User;

@Repository
public interface HopDongRepository extends JpaRepository<HopDong, Integer> {

    // Lấy hợp đồng của một người dùng
    List<HopDong> findByUser(User user);

    // Lấy hợp đồng của người dùng theo userId
    List<HopDong> findByUser_Id(Integer userId);

    boolean existsByUserAndLopHoc(User user, LopHoc lophoc);

    // Phương thức tìm kiếm hợp đồng theo tên lớp học
    List<HopDong> findByLopHoc_Name(String name);
}
