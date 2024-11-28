/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import tech.validator.Phone;
import tech.validator.ValidDate30Withins;

/**
 * ThongTinTiecCuoi - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 25, 2024
 */
@Entity
@Table(name = "thongtintieccuoi")
public class ThongTinTiecCuoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tencodau",columnDefinition = "nvarchar(255)",nullable = false, length = 50)
    @NotBlank(message = "tencodau may not be null")
    @Length(min = 5, max = 50, message = "tencodau must be between 5 and 50 characters")
    private String tenCoDau;
    @Column(name = "tenchure",columnDefinition = "nvarchar(255)", nullable = false, length = 50)
    @NotBlank(message = "tenchure may not be null")
    @Length(min = 5, max = 50, message = "tenchure must be between 5 and 50 characters")
    private String tenChuRe;

    @Column(name = "ngaytochuc")
    @NotNull(message = "Date of Birth may not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate30Withins
    private LocalDate ngayToChuc;
    @Column(name = "slBan")
    @Min(value = 1)
    @Max(value = 100)
    private Integer slBan;
    @Column(name = "dongia")
    private double donGia;
    @ManyToOne
    @JoinColumn(name = "dichvudikem", referencedColumnName = "ID_DichVu", nullable = false)
    private DichVu dichVu;
    @Column(name = "tiendatcoc")
    private double tienDatCoc;
    @Column(name = "tienthanhtoan")
    private double tienThanhToan;
    @Column(name = "ngaythanhtoan")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayThanhToan;  // Ngày bắt đầu hợp đồng
    @Column(name = "ngaydatcoc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayDatCoc;  // Ngày bắt đầu hợp đồng
    @Column(name = "ghichu")
    private String ghiChu;
    @Column(name = "trangthai")
    private Integer trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenCoDau() {
        return tenCoDau;
    }

    public void setTenCoDau(String tenCoDau) {
        this.tenCoDau = tenCoDau;
    }

    public String getTenChuRe() {
        return tenChuRe;
    }

    public void setTenChuRe(String tenChuRe) {
        this.tenChuRe = tenChuRe;
    }

    public LocalDate getNgayToChuc() {
        return ngayToChuc;
    }

    public void setNgayToChuc(LocalDate ngayToChuc) {
        this.ngayToChuc = ngayToChuc;
    }

    public Integer getSlBan() {
        return slBan;
    }

    public void setSlBan(Integer slBan) {
        this.slBan = slBan;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public double getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(double tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public double getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(double tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public LocalDate getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDate ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayDatCoc() {
        return ngayDatCoc;
    }

    public void setNgayDatCoc(LocalDate ngayDatCoc) {
        this.ngayDatCoc = ngayDatCoc;
    }

}
