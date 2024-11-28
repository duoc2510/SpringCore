package tech.entities;

import java.time.LocalDate;
import tech.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Past;
import tech.validator.Gmail;
import tech.validator.ValidDate30Withins;
import tech.validator.ValidDateFormat;

@Entity
@Table(name = "users") // Tên bảng trong cơ sở dữ liệu
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "Name may not be null")
    @Length(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email may not be null")
    @Gmail(message = "Invalid email format, Example duocnlt2510@gmail.com")
    private String email;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of Birth may not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate30Withins
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", length = 15)
    @Phone(message = "Số điện thoại phải là 10 số và bắt đầu từ 09, ví dụ 09xxxyyy12")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HopDong> hopDongs;  // Mối quan hệ với bảng HopDong

    @Column(name = "user_type")
    @NotBlank(message = "UserType may not be null")
    private String userType;

    // Các phương thức getter, setter...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<HopDong> getHopDongs() {
        return hopDongs;
    }

    public void setHopDongs(List<HopDong> hopDongs) {
        this.hopDongs = hopDongs;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
