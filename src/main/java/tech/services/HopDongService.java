package tech.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.entities.HopDong;
import tech.entities.LopHoc;
import tech.entities.User;
import tech.exception.HopDongAlreadyExistsException;
import tech.exception.LopHocNotFoundException;
import tech.exception.UserNotFoundException;
import tech.repositories.HopDongRepository;

@Service
public class HopDongService {

    @Autowired
    private HopDongRepository hopDongRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LopHocService lopHocService;

    // Lấy tất cả hợp đồng của người dùng
    public List<HopDong> getHopDongByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User không được để null.");
        }
        return hopDongRepository.findByUser(user);
    }

    // Lấy danh sách tất cả hợp đồng
    public List<HopDong> getAllHopDongs() {
        return hopDongRepository.findAll();
    }

    public void saveHopDong(Integer userId, Integer lopHocId) {
        // Xác thực user và lớp học
        User user = findUserById(userId);
        LopHoc lopHoc = findLopHocById(lopHocId);

        // Kiểm tra nếu người dùng đã tham gia lớp học
        if (hopDongRepository.existsByUserAndLopHoc(user, lopHoc)) {
            throw new HopDongAlreadyExistsException("Người dùng đã tham gia lớp học này!");
        }

        // Tạo hợp đồng mới
        HopDong hopDong = createHopDong(user, lopHoc);

        // Gán giá trị dựa trên loại người dùng
        if ("Discount".equals(user.getUserType())) {
            hopDong.setPrice(100000);
        } else {
            hopDong.setPrice(200000);
        }

        // Lưu hợp đồng
        hopDongRepository.save(hopDong);
    }

// Tìm User dựa trên userId
    private User findUserById(Integer userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User với ID " + userId + " không tồn tại."));
    }

// Tìm Lớp học dựa trên lopHocId
    private LopHoc findLopHocById(Integer lopHocId) {
        return lopHocService.getLopHocById(lopHocId)
                .orElseThrow(() -> new LopHocNotFoundException("Lớp học với ID " + lopHocId + " không tồn tại."));
    }

// Tạo đối tượng HopDong mới
    private HopDong createHopDong(User user, LopHoc lophoc) {
        HopDong hopDong = new HopDong();
        hopDong.setUser(user);
        hopDong.setLopHoc(lophoc);

        // Đặt ngày bắt đầu và ngày kết thúc
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        hopDong.setStartDate(java.sql.Date.valueOf(startDate));
        hopDong.setEndDate(java.sql.Date.valueOf(endDate));

        return hopDong;
    }

    public List<HopDong> getHopDongByLopHocName(String name) {
        return hopDongRepository.findByLopHoc_Name(name);
    }

}
