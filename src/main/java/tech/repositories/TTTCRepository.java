/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.entities.ThongTinTiecCuoi;

/**
 * TTTCRepository - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 25, 2024
 */
public interface TTTCRepository extends JpaRepository<ThongTinTiecCuoi, Integer> {

    @Query("SELECT d.id FROM ThongTinTiecCuoi d ORDER BY d.id DESC")
    Integer findMaxMaDK();

    List<ThongTinTiecCuoi> findByTenCoDauContainingOrTenChuReContaining(String ten, String ten2);

}
