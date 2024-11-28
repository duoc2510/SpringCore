/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.entities.DichVu;
import tech.entities.ThongTinTiecCuoi;
import tech.entities.User;
import tech.repositories.DichVuRepository;
import tech.repositories.TTTCRepository;

/**
 * DVService - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 25, 2024
 */
@Service
public class DVService {

    @Autowired
    private DichVuRepository dvrepo;
    // Phương thức lấy danh sách tất cả người dùng

    public List<DichVu> getAllDichVu() {
        return dvrepo.findAll();  // Lấy tất cả người dùng từ DB
    }

    // Phương thức tìm người dùng theo id
    @Transactional
    public Optional<DichVu> findById(Integer id) {
        return dvrepo.findById(id);  // Tìm người dùng theo id
    }

}
