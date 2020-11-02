package com.karique.work.dev.day2day.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NewPasword {
    public static int TIPO_PACIENTE = 1;
    public static int TIPO_TUTOR = 2;
    public static int TIPO_ESPECIALISTA = 3;

    private int userId;
    private String newPassword;
    private int tipo;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", userId);
            jsonObject.put("newPassword", newPassword);
            jsonObject.put("tipo", tipo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public NewPasword() {
    }

    public NewPasword(int userId, String newPassword, int tipo) {
        this.userId = userId;
        this.newPassword = newPassword;
        this.tipo = tipo;
    }

    public static int getTipoPaciente() {
        return TIPO_PACIENTE;
    }

    public static void setTipoPaciente(int tipoPaciente) {
        TIPO_PACIENTE = tipoPaciente;
    }

    public static int getTipoTutor() {
        return TIPO_TUTOR;
    }

    public static void setTipoTutor(int tipoTutor) {
        TIPO_TUTOR = tipoTutor;
    }

    public static int getTipoEspecialista() {
        return TIPO_ESPECIALISTA;
    }

    public static void setTipoEspecialista(int tipoEspecialista) {
        TIPO_ESPECIALISTA = tipoEspecialista;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
