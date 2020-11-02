package com.karique.work.dev.day2day.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NewPasswordEmail {
    public static int TIPO_PACIENTE = 1;
    public static int TIPO_TUTOR = 2;
    public static int TIPO_ESPECIALISTA = 3;

    private String email;
    private String dni;
    private int tipo;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("dni", dni);
            jsonObject.put("tipo", tipo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public NewPasswordEmail() {
    }

    public NewPasswordEmail(String email, String dni, int tipo) {
        this.email = email;
        this.dni = dni;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
