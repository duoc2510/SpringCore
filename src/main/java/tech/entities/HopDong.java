/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.entities;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;

/**
 *
 * @author Thanh Duoc
 */
@Entity
@Table(name = "HopDong")
public class HopDong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;  // Mối quan hệ với User

    @ManyToOne
    @JoinColumn(name = "lop_hoc_id", referencedColumnName = "id", nullable = false)
    private LopHoc lopHoc;  // Mối quan hệ với LopHoc

    @Column(name = "start_date")
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;  // Ngày bắt đầu hợp đồng

    @Column(name = "end_date")
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;  // Ngày kết thúc hợp đồng

    @Column(name = "price")
    private double price;

    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDateRange() {
        return endDate != null && startDate != null && endDate.after(startDate);
    }

    // Các phương thức getter và setter...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
