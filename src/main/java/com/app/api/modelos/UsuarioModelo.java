package com.app.api.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class UsuarioModelo {

    @Id
    @Column(name = "cedula")
    private int cedula;

    @Column
    private String clave;

    @Column
    private String nombre;

    @Column
    private String telefono;

    @Column
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<DocenteModelo> docentes = new ArrayList<DocenteModelo>();

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
