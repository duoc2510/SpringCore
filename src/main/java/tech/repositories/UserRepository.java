package tech.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    // Tìm người dùng theo email
    User findByEmail(String email);

    // Tìm kiếm người dùng theo tên (chứa chuỗi query)
    List<User> findByNameContaining(String query);

    // Tìm kiếm người dùng theo số điện thoại (chứa chuỗi query)
    List<User> findByPhoneNumberContaining(String query);

    // Tìm kiếm người dùng theo tên và số điện thoại (chứa chuỗi query)
    List<User> findByNameContainingAndPhoneNumberContaining(String nameQuery, String phoneQuery);
}
