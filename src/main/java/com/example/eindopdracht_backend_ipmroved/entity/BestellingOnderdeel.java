package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;

@Entity
public class BestellingOnderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aankoopbon_id")
    private Aankoopbon aankoopbon;

    @ManyToOne
    private Product product;

    private int hoeveelheid;

    private double prijs;

    // Getters en setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aankoopbon getAankoopbon() {
        return aankoopbon;
    }

    public void setAankoopbon(Aankoopbon aankoopbon) {
        this.aankoopbon = aankoopbon;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
