package com.karique.work.dev.day2day.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private int idtest;
    private String nombre_test;
    private int resultado;
    private int realizado;
    private String fecha_hora;
    private int paciente_idpaciente;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre_test", nombre_test);
            jsonObject.put("resultado", resultado);
            jsonObject.put("realizado", realizado);
            jsonObject.put("fecha_hora", fecha_hora);
            jsonObject.put("paciente_idpaciente", paciente_idpaciente);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static List<Test> from(JSONArray jsonArray){
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tests.add(from(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tests;
    }

    public static Test from(JSONObject jsonObject){
        Test test = new Test();
        try {
            test.setIdtest(jsonObject.getInt("idtest"));
            test.setNombre_test(jsonObject.getString("nombre_test"));
            test.setResultado(jsonObject.getInt("resultado"));
            test.setRealizado(jsonObject.getInt("realizado"));
            test.setFecha_hora(jsonObject.getString("fecha_hora"));
            test.setPaciente_idpaciente(jsonObject.getInt("paciente_idpaciente"));

            test.setNombre_test(test.getNombre_test().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return test;
    }

    public Test() {
    }

    public Test(int idtest, String nombre_test, int resultado, int realizado, String fecha_hora, int paciente_idpaciente) {
        this.idtest = idtest;
        this.nombre_test = nombre_test;
        this.resultado = resultado;
        this.realizado = realizado;
        this.fecha_hora = fecha_hora;
        this.paciente_idpaciente = paciente_idpaciente;
    }

    public int getIdtest() {
        return idtest;
    }

    public void setIdtest(int idtest) {
        this.idtest = idtest;
    }

    public String getNombre_test() {
        return nombre_test;
    }

    public void setNombre_test(String nombre_test) {
        this.nombre_test = nombre_test;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getRealizado() {
        return realizado;
    }

    public void setRealizado(int realizado) {
        this.realizado = realizado;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getPaciente_idpaciente() {
        return paciente_idpaciente;
    }

    public void setPaciente_idpaciente(int paciente_idpaciente) {
        this.paciente_idpaciente = paciente_idpaciente;
    }

    public String getDescripcionResultado(){
        return resultado <= 4 ? "Positivo" : resultado <= 14 ? "Neutro" : "Negativo";
    }

    public String getDescripcionLargaResultado(){
        return resultado <= 4 ? "¡Enhorabuena! Hoy es un gran día, llamar o escribirle un mensaje hará que sea aún mejor." :
                resultado <= 14 ? "Hacer alguna actividad en grupo o compartir algo en común, ayuda a despejar la mente, basta con unos minutos." :
                        "Ahora es un momento para darle apoyo, reúnete con él y visítalo, es en estos momentos cuando más te necesita.";
    }
}
