package tech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.entities.HopDong;
import tech.services.HopDongService;

@Controller
public class HopDongController {

    @Autowired
    private HopDongService hopdongService;

    @PostMapping("/joinClass")
    public String joinClass(
            @RequestParam("userId") Integer userId,
            @RequestParam("lopHocId") Integer lopHocId
    ) {
        try {
            // Gọi service để thêm thông tin tham gia lớp học
            hopdongService.saveHopDong(userId, lopHocId); // Lưu hợp đồng

            // Chuyển hướng về danh sách người dùng sau khi thêm hợp đồng thành công
            return "redirect:/viewAllUsers";
        } catch (RuntimeException e) {
            // Xử lý lỗi nếu có, ví dụ như thông báo hợp đồng đã tồn tại
            return "redirect:/errorPage"; // Chuyển hướng tới trang lỗi
        }
    }

    @GetMapping("list")
    public String getlist(Model model) {
        List<HopDong> hopdong = hopdongService.getAllHopDongs();
        model.addAttribute("hd", hopdong);
        return "listtest";
    }

}
