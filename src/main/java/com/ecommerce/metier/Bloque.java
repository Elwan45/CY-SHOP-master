package com.ecommerce.metier;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bloque {
    private int idb;
    private int idc;
    private Client clientByIdc;

    @Id
    @Column(name = "idb", nullable = false)
    public int getIdb() {
        return idb;
    }

    public void setIdb(int idb) {
        this.idb = idb;
    }

    /*@Basic
    @Column(name = "idc", nullable = false)
    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloque bloque = (Bloque) o;
        return idb == bloque.idb && idc == bloque.idc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idb, idc);
    }
    @ManyToOne
    @JoinColumn(name = "idc", referencedColumnName = "idc")
    public Client getClientByIdc() {
        return clientByIdc;
    }

    public void setClientByIdc(Client clientByIdc) {
        this.clientByIdc = clientByIdc;
    }
}
