package model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String address;

    @ManyToOne
    private Province province;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String address, Province province) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.province = province;
    }

    public Customer(String name, String email, String address, Province province) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", province=" + province +
                '}';
    }
}
