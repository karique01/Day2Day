package com.karique.work.dev.day2day.network;

public class Day2DayApi {
    public static String BASE_URL = "https://day2daybackend.azurewebsites.net/";  //azure

    public static String LOGIN_PACIENTE_URL = BASE_URL + "api/login/paciente";
    public static String LOGIN_TUTOR_URL = BASE_URL + "api/login/tutor";
    public static String PACIENTES_URL = BASE_URL + "/api/pacientes";
    public static String ESPECIALISTAS_URL = BASE_URL + "/api/especialistas";
    public static String TESTS_URL = BASE_URL + "/api/tests";
    public static String DIARIOS_URL = BASE_URL + "/api/diarios";
    public static String TUTORS_URL = BASE_URL + "/api/tutors";
    public static String RESET_PASSWORD_URL = BASE_URL + "/api/Password/resetPassword";
    public static String SEND_RESET_PASSWORD_EMAIL_URL = BASE_URL + "/api/Password/sendResetPasswordEmail";
    public static String TOKEN_FCM_URL = BASE_URL + "/api/Password/tokenFCM";

    public static String TESTS_BY_PACIENT(String pacienteId){
        return PACIENTES_URL + "/" + pacienteId + "/" + "tests";
    }
    public static String DIARIOS_BY_PACIENT(String pacienteId){
        return PACIENTES_URL + "/" + pacienteId + "/" + "diarios";
    }
    public static String PACIENTS_BY_TUTOR(String tutorId){
        return TUTORS_URL + "/" + tutorId + "/" + "pacientes";
    }
}
