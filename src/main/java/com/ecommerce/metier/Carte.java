package com.ecommerce.metier;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Carte {
    private int idcarte;
    private String numero;
    private String dateExpiration;
    private int crypto;

    @Id
    @Column(name = "idcarte", nullable = false)
    public int getIdcarte() {
        return idcarte;
    }

    public void setIdcarte(int idcarte) {
        this.idcarte = idcarte;
    }

    @Basic
    @Column(name = "numero", nullable = false, length = 16)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "dateExpiration", nullable = false, length = 5)
    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Basic
    @Column(name = "crypto", nullable = false)
    public int getCrypto() {
        return crypto;
    }

    public void setCrypto(int crypto) {
        this.crypto = crypto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return idcarte == carte.idcarte && crypto == carte.crypto && Objects.equals(numero, carte.numero) && Objects.equals(dateExpiration, carte.dateExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcarte, numero, dateExpiration, crypto);
    }
}
