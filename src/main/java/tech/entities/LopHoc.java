/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;

@Entity
@Table(name = "LopHoc")
public class LopHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "Name may not be null")
    private String name;

    @OneToMany(mappedBy = "lopHoc", cascade = CascadeType.ALL)
    private List<HopDong> hopDongs;  // Mối quan hệ với bảng HopDong

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LopHoc lopHoc = (LopHoc) o;
        return Objects.equals(id, lopHoc.id); // So sánh dựa trên id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash dựa trên id
    }

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

    public List<HopDong> getHopDongs() {
        return hopDongs;
    }

    public void setHopDongs(List<HopDong> hopDongs) {
        this.hopDongs = hopDongs;
    }
}
