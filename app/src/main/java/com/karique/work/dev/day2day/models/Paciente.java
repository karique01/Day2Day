package com.karique.work.dev.day2day.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private int idpaciente;
    private String nombre;
    private String apellidos;
    private String tipo_documentacion;
    private String num_doc_identidad;
    private String fecha_nacimiento;
    private int nivel_estudios;
    private String ocupacion;
    private String correo;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private int telefono;
    private String contrasenia;
    private int estado;
    private String des_estudios;
    private String des_estado;
    private int especialista_idespe;
    private int tutor_idtutor;

    public static Paciente from(Bundle bundle){
        Paciente paciente = new Paciente();
        paciente.setIdpaciente(bundle.getInt("idpaciente"));
        paciente.setNombre(bundle.getString("nombre"));
        paciente.setApellidos(bundle.getString("apellidos"));
        paciente.setTipo_documentacion(bundle.getString("tipo_documentacion"));
        paciente.setNum_doc_identidad(bundle.getString("num_doc_identidad"));
        paciente.setFecha_nacimiento(bundle.getString("fecha_nacimiento"));
        paciente.setNivel_estudios(bundle.getInt("nivel_estudios"));
        paciente.setOcupacion(bundle.getString("ocupacion"));
        paciente.setCorreo(bundle.getString("correo"));
        paciente.setDireccion(bundle.getString("direccion"));
        paciente.setDistrito(bundle.getString("distrito"));
        paciente.setProvincia(bundle.getString("provincia"));
        paciente.setDepartamento(bundle.getString("departamento"));
        paciente.setTelefono(bundle.getInt("telefono"));
        paciente.setContrasenia(bundle.getString("contrasenia"));
        paciente.setEstado(bundle.getInt("estado"));
        paciente.setDes_estudios(bundle.getString("des_estudios"));
        paciente.setDes_estado(bundle.getString("des_estado"));
        paciente.setEspecialista_idespe(bundle.getInt("especialista_idespe"));
        paciente.setTutor_idtutor(bundle.getInt("tutor_idtutor"));

        return paciente;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt("idpaciente", idpaciente);
        bundle.putString("nombre", nombre);
        bundle.putString("apellidos", apellidos);
        bundle.putString("tipo_documentacion", tipo_documentacion);
        bundle.putString("num_doc_identidad", num_doc_identidad);
        bundle.putString("fecha_nacimiento", fecha_nacimiento);
        bundle.putInt("nivel_estudios", nivel_estudios);
        bundle.putString("ocupacion", ocupacion);
        bundle.putString("correo", correo);
        bundle.putString("direccion", direccion);
        bundle.putString("distrito", distrito);
        bundle.putString("provincia", provincia);
        bundle.putString("departamento", departamento);
        bundle.putInt("telefono", telefono);
        bundle.putString("contrasenia", contrasenia);
        bundle.putInt("estado", estado);
        bundle.putString("des_estudios", des_estudios);
        bundle.putString("des_estado", des_estado);
        bundle.putInt("especialista_idespe", especialista_idespe);
        bundle.putInt("tutor_idtutor", tutor_idtutor);

        return bundle;
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellidos", apellidos);
            jsonObject.put("tipo_documentacion", tipo_documentacion);
            jsonObject.put("num_doc_identidad", num_doc_identidad);
            jsonObject.put("fecha_nacimiento", fecha_nacimiento);
            jsonObject.put("nivel_estudios", nivel_estudios);
            jsonObject.put("ocupacion", ocupacion);
            jsonObject.put("correo", correo);
            jsonObject.put("direccion", direccion);
            jsonObject.put("distrito", distrito);
            jsonObject.put("provincia", provincia);
            jsonObject.put("departamento", departamento);
            jsonObject.put("telefono", telefono);
            jsonObject.put("contrasenia", contrasenia);
            jsonObject.put("estado", estado);
            jsonObject.put("des_estudios", des_estudios);
            jsonObject.put("des_estado", des_estado);
            jsonObject.put("especialista_idespe", especialista_idespe);
            jsonObject.put("tutor_idtutor", tutor_idtutor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static List<Paciente> from(JSONArray jsonArray){
        List<Paciente> pacientes = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                pacientes.add(from(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return pacientes;
    }

    public static Paciente from(JSONObject jsonObject){
        Paciente paciente = new Paciente();
        try {
            paciente.setIdpaciente(jsonObject.getInt("idpaciente"));
            paciente.setNombre(jsonObject.getString("nombre"));
            paciente.setApellidos(jsonObject.getString("apellidos"));
            paciente.setTipo_documentacion(jsonObject.getString("tipo_documentacion"));
            paciente.setNum_doc_identidad(jsonObject.getString("num_doc_identidad").trim());
            paciente.setFecha_nacimiento(jsonObject.getString("fecha_nacimiento"));
            paciente.setNivel_estudios(jsonObject.getInt("nivel_estudios"));
            paciente.setOcupacion(jsonObject.getString("ocupacion"));
            paciente.setCorreo(jsonObject.getString("correo"));
            paciente.setDireccion(jsonObject.getString("direccion"));
            paciente.setDistrito(jsonObject.getString("distrito"));
            paciente.setProvincia(jsonObject.getString("provincia"));
            paciente.setDepartamento(jsonObject.getString("departamento"));
            paciente.setTelefono(jsonObject.getInt("telefono"));
            paciente.setContrasenia(jsonObject.getString("contrasenia").trim());
            paciente.setEstado(jsonObject.getInt("estado"));
            paciente.setDes_estudios(jsonObject.getString("des_estudios"));
            paciente.setDes_estado(jsonObject.getString("des_estado"));
            paciente.setEspecialista_idespe(jsonObject.getInt("especialista_idespe"));
            paciente.setTutor_idtutor(jsonObject.getInt("tutor_idtutor"));

            paciente.setNum_doc_identidad(paciente.getNum_doc_identidad().trim());
            paciente.setContrasenia(paciente.getContrasenia().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    public Paciente() {
    }

    public Paciente(int idpaciente, String nombre, String apellidos, String tipo_documentacion, String num_doc_identidad, String fecha_nacimiento, int nivel_estudios, String ocupacion, String correo, String direccion, String distrito, String provincia, String departamento, int telefono, String contrasenia, int estado, String des_estudios, String des_estado, int especialista_idespe, int tutor_idtutor) {
        this.idpaciente = idpaciente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo_documentacion = tipo_documentacion;
        this.num_doc_identidad = num_doc_identidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nivel_estudios = nivel_estudios;
        this.ocupacion = ocupacion;
        this.correo = correo;
        this.direccion = direccion;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.des_estudios = des_estudios;
        this.des_estado = des_estado;
        this.especialista_idespe = especialista_idespe;
        this.tutor_idtutor = tutor_idtutor;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
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

    public int getNivel_estudios() {
        return nivel_estudios;
    }

    public void setNivel_estudios(int nivel_estudios) {
        this.nivel_estudios = nivel_estudios;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDes_estudios() {
        return des_estudios;
    }

    public void setDes_estudios(String des_estudios) {
        this.des_estudios = des_estudios;
    }

    public String getDes_estado() {
        return des_estado;
    }

    public void setDes_estado(String des_estado) {
        this.des_estado = des_estado;
    }

    public int getEspecialista_idespe() {
        return especialista_idespe;
    }

    public void setEspecialista_idespe(int especialista_idespe) {
        this.especialista_idespe = especialista_idespe;
    }

    public int getTutor_idtutor() {
        return tutor_idtutor;
    }

    public void setTutor_idtutor(int tutor_idtutor) {
        this.tutor_idtutor = tutor_idtutor;
    }
    public String getnivel_estudiosStr() {
        return nivel_estudios == 1 ? "Primaria" : nivel_estudios == 2 ? "Secundaria" : "Superior";
    }
}
