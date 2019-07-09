package com.example.gruposhots;
/*
 * author: Raul Paolo Payan
 * version: 0.3
 *
 * esta clase se hacen los getters y setters de para manipular los datos de la base de datos
 */

public class Model {

    //variables con los nombres que se encuentran en la base de datos

    String titulo, image, descripcion;

    //constructor vacio de la clase
    public Model(){}

    //getter y setter de las variables
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
