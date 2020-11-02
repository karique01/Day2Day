package com.karique.work.dev.day2day.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Especialista {
    private int idespe;
    private String nombre;
    private String apellidos;
    private String tipo_documentacion;
    private int num_doc_identidad;
    private String fecha_nacimiento;
    private int num_colegiatura;
    private String especialidad;
    private String correo;
    private String empresa;
    private String cargo;
    private int estado;
    private String des_estado;
    private String contrasenia;

    private boolean escogido;

    public static List<Especialista> from(JSONArray jsonArray){
        List<Especialista> especialistas = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                especialistas.add(from(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return especialistas;
    }

    public static Especialista from(JSONObject jsonObject){
        Especialista especialista = new Especialista();
        try {
            especialista.setIdespe(jsonObject.getInt("idespe"));
            especialista.setNombre(jsonObject.getString("nombre"));
            especialista.setApellidos(jsonObject.getString("apellidos"));
            especialista.setTipo_documentacion(jsonObject.getString("tipo_documentacion"));
            especialista.setNum_doc_identidad(jsonObject.getInt("num_doc_identidad"));
            especialista.setFecha_nacimiento(jsonObject.getString("fecha_nacimiento"));
            especialista.setNum_colegiatura(jsonObject.getInt("num_colegiatura"));
            especialista.setEspecialidad(jsonObject.getString("especialidad"));
            especialista.setCorreo(jsonObject.getString("correo"));
            especialista.setEmpresa(jsonObject.getString("empresa"));
            especialista.setCargo(jsonObject.getString("cargo"));
            especialista.setEstado(jsonObject.getInt("estado"));
            especialista.setDes_estado(jsonObject.getString("des_estado"));
            especialista.setContrasenia(jsonObject.getString("contrasenia"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return especialista;
    }

    public Especialista() {
    }

    public Especialista(int idespe, String nombre, String apellidos, String tipo_documentacion, int num_doc_identidad, String fecha_nacimiento, int num_colegiatura, String especialidad, String correo, String empresa, String cargo, int estado, String des_estado, String contrasenia) {
        this.idespe = idespe;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo_documentacion = tipo_documentacion;
        this.num_doc_identidad = num_doc_identidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.num_colegiatura = num_colegiatura;
        this.especialidad = especialidad;
        this.correo = correo;
        this.empresa = empresa;
        this.cargo = cargo;
        this.estado = estado;
        this.des_estado = des_estado;
        this.contrasenia = contrasenia;
    }

    public int getIdespe() {
        return idespe;
    }

    public void setIdespe(int idespe) {
        this.idespe = idespe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipo_documentacion() {
        return tipo_documentacion;
    }

    public void setTipo_documentacion(String tipo_documentacion) {
        this.tipo_documentacion = tipo_documentacion;
    }

    public int getNum_doc_identidad() {
        return num_doc_identidad;
    }

    public void setNum_doc_identidad(int num_doc_identidad) {
        this.num_doc_identidad = num_doc_identidad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNum_colegiatura() {
        return num_colegiatura;
    }

    public void setNum_colegiatura(int num_colegiatura) {
        this.num_colegiatura = num_colegiatura;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDes_estado() {
        return des_estado;
    }

    public void setDes_estado(String des_estado) {
        this.des_estado = des_estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEscogido() {
        return escogido;
    }

    public void setEscogido(boolean escogido) {
        this.escogido = escogido;
    }
}
