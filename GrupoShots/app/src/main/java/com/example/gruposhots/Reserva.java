package com.example.gruposhots;

public class Reserva {
    private String establecimiento;
    private String estado;
    private String fecha;
    private String lugar;
    private String usuario;

    public Reserva(String establecimiento, String estado, String fecha, String lugar, String usuario){
        this.establecimiento = establecimiento;
        this.estado = estado;
        this.fecha = fecha;
        this.lugar = lugar;
        this.usuario = usuario;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
