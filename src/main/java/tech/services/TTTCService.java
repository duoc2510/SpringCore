/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.parser.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.entities.DichVu;
import tech.entities.ThongTinTiecCuoi;
import tech.entities.User;
import tech.exception.DatCocItHonTongTien;
import tech.exception.UserNotFoundException;
import tech.repositories.DichVuRepository;
import tech.repositories.TTTCRepository;

/**
 * TTTCService - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 25, 2024
 */
@Service
public class TTTCService {

    @Autowired
    private TTTCRepository tcrepo;
    @Autowired
    private DVService dVService;

    // Phương thức lấy danh sách tất cả người dùng
    public List<ThongTinTiecCuoi> getAllTTTC() {
        return tcrepo.findAll();  // Lấy tất cả người dùng từ DB
    }

    // Phương thức lưu người dùng vào DB
    public void saveTTTC(ThongTinTiecCuoi TTTC, HttpSession session) {
        String msg = "";
        TTTC.setTrangThai(0);
        int slB = TTTC.getSlBan();
        double dongia = 0;
        if (slB <= 15) {
            TTTC.setDonGia(2400000 * slB);
        } else if (slB > 15 && slB < 26) {
            TTTC.setDonGia(2300000 * slB);
        } else if (slB > 25 && slB < 36) {
            TTTC.setDonGia(2200000 * slB);
        } else if (slB > 35 && slB < 46) {
            TTTC.setDonGia(2100000 * slB);
        } else {
            TTTC.setDonGia(2000000 * slB);
        }
        Optional<DichVu> dv = dVService.findById(TTTC.getDichVu().getId());
        TTTC.setNgayDatCoc(LocalDate.now());
        double tongtien = TTTC.getDonGia() + dv.get().getPrice();
        double tiendatcoc = TTTC.getTienDatCoc();
        double tienthanhtoan = tongtien - tiendatcoc;
        TTTC.setTienThanhToan(tienthanhtoan);
        if (tiendatcoc < tongtien * 0.1) {
            session.setAttribute(msg, "Tiền Đặt Cọc Bé Hơn Tổng tiền");
        } else {
            tcrepo.save(TTTC);  // Lưu thông tin người dùng vào DB
            session.setAttribute(msg, "Thao tác thành công");
        }
    }

//    public void updateTTTC() {
//        int id = tcrepo.findMaxMaDK();
//        ThongTinTiecCuoi tttc = tcrepo.findById(tttc);
//    }
    public Double tinhTongTien() {
        double tongtien = 0;
        return tongtien;
    }

// Phương thức xóa 
    public void delete(Integer id) {
        tcrepo.deleteById(id);  // Xóa người dùng theo id
    }

    public List<ThongTinTiecCuoi> searchByBoth(String ten) {
        return tcrepo.findByTenCoDauContainingOrTenChuReContaining(ten, ten);
    }
}
