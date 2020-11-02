package com.karique.work.dev.day2day.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private int idtutor;
    private String nombre;
    private String apellidos;
    private String tipo_documentacion;
    private String num_doc_identidad;
    private String fecha_nacimiento;
    private int telefono;
    private String correo;
    private String contrasenia;
    private String relacion;
    private int estado;
    private String des_estado;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idtutor", idtutor);
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellidos", apellidos);
            jsonObject.put("tipo_documentacion", tipo_documentacion);
            jsonObject.put("num_doc_identidad", num_doc_identidad);
            jsonObject.put("fecha_nacimiento", fecha_nacimiento);
            jsonObject.put("telefono", telefono);
            jsonObject.put("correo", correo);
            jsonObject.put("contrasenia", contrasenia);
            jsonObject.put("relacion", relacion);
            jsonObject.put("estado", estado);
            jsonObject.put("des_estado", des_estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static List<Tutor> from(JSONArray jsonArray){
        List<Tutor> tutors = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tutors.add(from(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tutors;
    }

    public static Tutor from(JSONObject jsonObject){
        Tutor tutor = new Tutor();
        try {
            tutor.setIdtutor(jsonObject.getInt("idtutor"));
            tutor.setNombre(jsonObject.getString("nombre"));
            tutor.setApellidos(jsonObject.getString("apellidos"));
            tutor.setTipo_documentacion(jsonObject.getString("tipo_documentacion"));
            tutor.setNum_doc_identidad(jsonObject.getString("num_doc_identidad").trim());
            tutor.setFecha_nacimiento(jsonObject.getString("fecha_nacimiento"));
            tutor.setTelefono(jsonObject.getInt("telefono"));
            tutor.setCorreo(jsonObject.getString("correo"));
            tutor.setContrasenia(jsonObject.getString("contrasenia").trim());
            tutor.setRelacion(jsonObject.getString("relacion"));
            tutor.setEstado(jsonObject.getInt("estado"));
            tutor.setDes_estado(jsonObject.getString("des_estado"));

            tutor.setNum_doc_identidad(tutor.getNum_doc_identidad().trim());
            tutor.setContrasenia(tutor.getContrasenia().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tutor;
    }

    public Tutor() {
    }

    public Tutor(int idtutor, String nombre, String apellidos, String tipo_documentacion, String num_doc_identidad, String fecha_nacimiento, int telefono, String correo, String contrasenia, String relacion, int estado, String des_estado) {
        this.idtutor = idtutor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo_documentacion = tipo_documentacion;
        this.num_doc_identidad = num_doc_identidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.relacion = relacion;
        this.estado = estado;
        this.des_estado = des_estado;
    }

    public int getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(int idtutor) {
        this.idtutor = idtutor;
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

    public String getNum_doc_identidad() {
        return num_doc_identidad;
    }

    public void setNum_doc_identidad(String num_doc_identidad) {
        this.num_doc_identidad = num_doc_identidad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
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
}
