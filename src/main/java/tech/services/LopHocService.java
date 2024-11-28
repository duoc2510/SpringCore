/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.entities.HopDong;
import tech.entities.LopHoc;
import tech.entities.User;
import tech.repositories.HopDongRepository;
import tech.repositories.LopHocRepository;

/**
 *
 * @author Thanh Duoc
 */
@Service
public class LopHocService {

    @Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private HopDongRepository hopDongRepository;

    // Lấy tất cả lớp học
    public List<LopHoc> getAll() {
        return lopHocRepository.findAll();
    }

    public List<LopHoc> getLopHocsByUserId(Integer userId) {
        return lopHocRepository.findByHopDongsUserId(userId);
    }

    public Optional<LopHoc> getLopHocById(Integer id) {
        return lopHocRepository.findById(id);
    }
}
