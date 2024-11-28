/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tech.entities.DichVu;
import tech.entities.HopDong;
import tech.entities.ThongTinTiecCuoi;
import tech.entities.User;
import tech.services.DVService;
import tech.services.TTTCService;

/**
 * TTTCController - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 25, 2024
 */
@Controller
public class TTTCController {

    @Autowired
    private TTTCService tcservice;
    @Autowired
    private DVService dvservice;

    @GetMapping("/tttc")
    public String doGetViewAllTTTC(Model model) {
        List<ThongTinTiecCuoi> tttcs = tcservice.getAllTTTC();
        model.addAttribute("tttcs", tttcs);
        return "view-all-TTTC";  // Tạo form thêm người dùng
    }

    @RequestMapping(value = "/deleteTTTC/{id}", method = RequestMethod.GET)
    public String doGetDeleteUser(@PathVariable("id") Integer id) {
        // Xóa người dùng theo id
        tcservice.delete(id);

        return "redirect:/tttc";  // Chuyển hướng đến trang danh sách người dùng sau khi xóa
    }

    // GET request to show add user form
    @RequestMapping(value = "/addTTTC", method = RequestMethod.GET)
    public String doGetAddTTTC(Model model) {
        model.addAttribute("tttc", new ThongTinTiecCuoi());
        return "add-tttc";  // Tạo form thêm người dùng
    }

    // POST request to process form data
    @RequestMapping(value = "/addTTTC", method = RequestMethod.POST)
    public String doPostAddUser(@ModelAttribute("tttc") @Validated ThongTinTiecCuoi tttc, BindingResult result, Model model, HttpSession session) {
        // Kiểm tra lỗi khi submit form
        Object msg = null;
        if (result.hasErrors()) {
            // In ra các lỗi trên console để debug
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
            }
            return "add-tttc";  // Trả lại trang add-user nếu có lỗi
        }
        // Lưu thông tin người dùng vào cơ sở dữ liệu
        tcservice.saveTTTC(tttc, session);

//        model.addAttribute("msg", msg);
//        return "redirect:/viewUser/" + tttc.getId();  // Chuyển hướng đến trang xem người dùng sau khi lưu
        return "redirect:/tttc";  // Chuyển hướng đến trang danh sách người dùng sau khi xóa
    }

    @PostMapping("/searchTTTC")
    public String search(@RequestParam(value = "searchByName", required = false) boolean searchByName,
            @RequestParam(value = "nameSearchTerm", required = false) String nameSearchTerm,
            Model model) {

        List<ThongTinTiecCuoi> tttcs = new ArrayList<>();  // Khởi tạo danh sách người dùng
        tttcs = tcservice.searchByBoth(nameSearchTerm);

        // Thêm danh sách người dùng và hợp đồng vào model để hiển thị
        model.addAttribute("tttcs", tttcs);

        return "view-all-TTTC";  // Trả về trang hiển thị kết quả tìm kiếm
    }

    @ModelAttribute("dichVus")
    public List<DichVu> dichVus() {
        return dvservice.getAllDichVu();
    }
}
