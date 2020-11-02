package com.karique.work.dev.day2day.models;

import com.karique.work.dev.day2day.util.FuncionesFecha;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diario {
    private int iddiario;
    private String texto;
    private String resultado;
    private int realizado;
    private String fecha_hora;
    private int paginas;
    private int paciente_idpaciente;

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("texto", texto);
            jsonObject.put("resultado", resultado);
            jsonObject.put("realizado", realizado);
            jsonObject.put("fecha_hora", fecha_hora);
            jsonObject.put("paginas", paginas);
            jsonObject.put("paciente_idpaciente", paciente_idpaciente);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static List<Diario> from(JSONArray jsonArray){
        List<Diario> diarios = new ArrayList<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            try {
                diarios.add(from(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return diarios;
    }

    public static Diario from(JSONObject jsonObject) {
        Diario diario = new Diario();
        try {
            diario.setIddiario(jsonObject.getInt("iddiario"));
            diario.setTexto(jsonObject.getString("texto"));
            diario.setResultado(jsonObject.getString("resultado"));
            diario.setRealizado(jsonObject.getInt("realizado"));
            diario.setFecha_hora(jsonObject.getString("fecha_hora"));
            diario.setPaginas(jsonObject.getInt("paginas"));
            diario.setPaciente_idpaciente(jsonObject.getInt("paciente_idpaciente"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return diario;
    }

    public Diario() {
    }

    public Diario(int iddiario, String texto, String resultado, int realizado, String fecha_hora, int paginas, int paciente_idpaciente) {
        this.iddiario = iddiario;
        this.texto = texto;
        this.resultado = resultado;
        this.realizado = realizado;
        this.fecha_hora = fecha_hora;
        this.paginas = paginas;
        this.paciente_idpaciente = paciente_idpaciente;
    }


    public int getIddiario() {
        return iddiario;
    }

    public void setIddiario(int iddiario) {
        this.iddiario = iddiario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
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

    public String getFecha_horaFormated() {
        Date fecha = FuncionesFecha.getDateWithHourFromString(getFecha_hora());
        String fechaFormated = FuncionesFecha.formatDateToTextForComment(fecha);
        return fechaFormated;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getPaciente_idpaciente() {
        return paciente_idpaciente;
    }

    public void setPaciente_idpaciente(int paciente_idpaciente) {
        this.paciente_idpaciente = paciente_idpaciente;
    }

    public String getResultadoDetallado() {
        switch (resultado) {
            case "P+":
                return "Muy positivo";
            case "P":
                return "Positivo";
            case "NEU":
                return "Neutral";
            case "N":
                return "Negativo";
            case "N+":
                return "Muy negativo";
            case "NONE":
                return "Sin emocion";
        }
        return "No se pudo determinar la emoción";
    }
}
