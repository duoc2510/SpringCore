package tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.entities.User;
import tech.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Phương thức lưu người dùng vào DB
    public void saveUser(User user) {
        userRepository.save(user);  // Lưu thông tin người dùng vào DB
    }

    // Phương thức lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();  // Lấy tất cả người dùng từ DB
    }

    // Phương thức tìm người dùng theo id
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);  // Tìm người dùng theo id
    }

    // Phương thức cập nhật thông tin người dùng
    public void updateUser(User user) {
        if (userRepository.existsById(user.getId())) {  // Kiểm tra người dùng có tồn tại không
            userRepository.save(user);  // Lưu lại thông tin người dùng đã cập nhật
        }
    }

    // Phương thức xóa người dùng
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);  // Xóa người dùng theo id
    }
    // Tìm kiếm người dùng theo tên hoặc số điện thoại

    // Tìm kiếm người dùng theo tên hoặc số điện thoại
    public List<User> searchUsersByName(String query) {
        return userRepository.findByNameContaining(query);
    }

    // Tìm kiếm người dùng theo tên và số điện thoại
    public List<User> searchUsersByPhone(String query) {
        return userRepository.findByPhoneNumberContaining(query);
    }

    public List<User> searchByBoth(String name, String phoneNumber) {
        return userRepository.findByNameContainingAndPhoneNumberContaining(name, phoneNumber);
    }
}
