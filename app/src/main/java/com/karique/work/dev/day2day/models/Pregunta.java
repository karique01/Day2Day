package com.karique.work.dev.day2day.models;

public class Pregunta {
    private int posicion;
    private String descripcionPregunta;
    private int respuesta;

    public Pregunta() {
    }

    public Pregunta(int posicion, String descripcionPregunta, int respuesta) {
        this.posicion = posicion;
        this.descripcionPregunta = descripcionPregunta;
        this.respuesta = respuesta;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
}
