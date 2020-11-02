package com.karique.work.dev.day2day.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.models.Tutor;

public class SessionManager {
    private static final String PREFERENCE_NAME = "com.karique.work.dev.day2day";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static int NO_EXISTE_VALOR = -1;

    private static final String USER_LOGIN = "userLogin";

    //tutor
    private static final String ID_TUTOR = "idtutor";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDOS = "apellidos";
    private static final String TIPO_DOCUMENTACION = "tipo_documentacion";
    private static final String NUM_DOC_IDENTIDAD = "num_doc_identidad";
    private static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    private static final String TELEFONO = "telefono";
    private static final String CORREO = "correo";
    private static final String CONTRASENIA = "contrasenia";
    private static final String RELACION = "relacion";
    private static final String ESTADO = "estado";
    private static final String DES_ESTADO = "des_estado";

    //paciente
    private static final String ID_PACIENTE = "idpaciente";
    private static final String NIVEL_ESTUDIOS = "nivel_estudios";
    private static final String OCUPACION = "ocupacion";
    private static final String DIRECCION = "direccion";
    private static final String DISTRITO = "distrito";
    private static final String PROVINCIA = "provincia";
    private static final String DEPARTAMENTO = "departamento";
    private static final String DES_ESTUDIOS = "des_estudios";
    private static final String ESPECIALISTA_IDESPE = "especialista_idespe";
    private static final String TUTOR_IDTUTOR = "tutor_idtutor";

    private static SessionManager sessionManager;
    public static SessionManager getInstance(Context context){
        if (sessionManager == null){
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    private SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void setUserIsLogged(boolean userLogin) { editor.putBoolean(USER_LOGIN, userLogin).commit(); }
    public boolean getUserIsLogged() {
        return preferences.getBoolean(USER_LOGIN, false);
    }

    private void setidtutor(int idtutor) { editor.putInt(ID_TUTOR, idtutor).commit(); }
    public int getidtutor() { return preferences.getInt(ID_TUTOR, NO_EXISTE_VALOR); }

    private void setnombre(String nombre) { editor.putString(NOMBRE, nombre).commit(); }
    public String getnombre() { return preferences.getString(NOMBRE, ""); }

    private void setapellidos(String apellidos) { editor.putString(APELLIDOS, apellidos).commit(); }
    public String getapellidos() { return preferences.getString(APELLIDOS, ""); }

    private void settipo_documentacion(String tipo_documentacion) { editor.putString(TIPO_DOCUMENTACION, tipo_documentacion).commit(); }
    public String gettipo_documentacion() { return preferences.getString(TIPO_DOCUMENTACION, ""); }

    private void setnum_doc_identidad(String num_doc_identidad) { editor.putString(NUM_DOC_IDENTIDAD, num_doc_identidad).commit(); }
    public String getnum_doc_identidad() { return preferences.getString(NUM_DOC_IDENTIDAD, ""); }

    private void setfecha_nacimiento(String fecha_nacimiento) { editor.putString(FECHA_NACIMIENTO, fecha_nacimiento).commit(); }
    public String getfecha_nacimiento() { return preferences.getString(FECHA_NACIMIENTO, ""); }

    private void settelefono(int telefono) { editor.putInt(TELEFONO, telefono).commit(); }
    public int gettelefono() { return preferences.getInt(TELEFONO, NO_EXISTE_VALOR); }

    private void setcorreo(String correo) { editor.putString(CORREO, correo).commit(); }
    public String getcorreo() { return preferences.getString(CORREO, ""); }

    private void setcontrasenia(String contrasenia) { editor.putString(CONTRASENIA, contrasenia).commit(); }
    public String getcontrasenia() { return preferences.getString(CONTRASENIA, "").trim(); }

    private void setrelacion(String relacion) { editor.putString(RELACION, relacion).commit(); }
    public String getrelacion() { return preferences.getString(RELACION, ""); }

    private void setestado(int estado) { editor.putInt(ESTADO, estado).commit(); }
    public int getestado() { return preferences.getInt(ESTADO, NO_EXISTE_VALOR); }

    private void setdes_estado(String des_estado) { editor.putString(DES_ESTADO, des_estado).commit(); }
    public String getdes_estado() { return preferences.getString(DES_ESTADO, ""); }

    private void setidpaciente(int idpaciente) { editor.putInt(ID_PACIENTE, idpaciente).commit(); }
    public int getidpaciente() { return preferences.getInt(ID_PACIENTE, NO_EXISTE_VALOR); }

    private void setnivel_estudios(int nivel_estudios) { editor.putInt(NIVEL_ESTUDIOS, nivel_estudios).commit(); }
    public int getnivel_estudios() { return preferences.getInt(NIVEL_ESTUDIOS, NO_EXISTE_VALOR); }
    public String getnivel_estudiosStr() {
        int ne = preferences.getInt(NIVEL_ESTUDIOS, NO_EXISTE_VALOR);
        return ne == 1 ? "Primaria" : ne == 2 ? "Secundaria" : "Superior";
    }

    private void setocupacion(String ocupacion) { editor.putString(OCUPACION, ocupacion).commit(); }
    public String getocupacion() { return preferences.getString(OCUPACION, ""); }

    private void setdireccion(String direccion) { editor.putString(DIRECCION, direccion).commit(); }
    public String getdireccion() { return preferences.getString(DIRECCION, ""); }

    private void setdistrito(String distrito) { editor.putString(DISTRITO, distrito).commit(); }
    public String getdistrito() { return preferences.getString(DISTRITO, ""); }

    private void setprovincia(String provincia) { editor.putString(PROVINCIA, provincia).commit(); }
    public String getprovincia() { return preferences.getString(PROVINCIA, ""); }

    private void setdepartamento(String departamento) { editor.putString(DEPARTAMENTO, departamento).commit(); }
    public String getdepartamento() { return preferences.getString(DEPARTAMENTO, ""); }

    private void setdes_estudios(String des_estudios) { editor.putString(DES_ESTUDIOS, des_estudios).commit(); }
    public String getdes_estudios() { return preferences.getString(DES_ESTUDIOS, ""); }

    private void setespecialista_idespe(int especialista_idespe) { editor.putInt(ESPECIALISTA_IDESPE, especialista_idespe).commit(); }
    public int getespecialista_idespe() { return preferences.getInt(ESPECIALISTA_IDESPE, NO_EXISTE_VALOR); }

    private void settutor_idtutor(int tutor_idtutor) { editor.putInt(TUTOR_IDTUTOR, tutor_idtutor).commit(); }
    public int gettutor_idtutor() { return preferences.getInt(TUTOR_IDTUTOR, NO_EXISTE_VALOR); }




    public Paciente toPaciente(){
        Paciente paciente = new Paciente();
        paciente.setIdpaciente(getidpaciente());
        paciente.setNombre(getnombre());
        paciente.setApellidos(getapellidos());
        paciente.setTipo_documentacion(gettipo_documentacion());
        paciente.setNum_doc_identidad(getnum_doc_identidad());
        paciente.setFecha_nacimiento(getfecha_nacimiento());
        paciente.setNivel_estudios(getnivel_estudios());
        paciente.setOcupacion(getocupacion());
        paciente.setCorreo(getcorreo());
        paciente.setDireccion(getdireccion());
        paciente.setDistrito(getdistrito());
        paciente.setProvincia(getprovincia());
        paciente.setDepartamento(getdepartamento());
        paciente.setTelefono(gettelefono());
        paciente.setContrasenia(getcontrasenia());
        paciente.setEstado(getestado());
        paciente.setDes_estudios(getdes_estudios());
        paciente.setDes_estado(getdes_estado());
        paciente.setEspecialista_idespe(getespecialista_idespe());
        paciente.setTutor_idtutor(gettutor_idtutor());

        return paciente;
    }

    public boolean isPaciente(){
        int userTypeId = getidpaciente();
        return !(userTypeId == NO_EXISTE_VALOR);
    }

    public boolean isTutor(){
        int userTypeId = getidtutor();
        return !(userTypeId == NO_EXISTE_VALOR);
    }

    public void savePaciente(Paciente userLogin){
        setUserIsLogged(true);
        setidpaciente(userLogin.getIdpaciente());
        setnombre(userLogin.getNombre());
        setapellidos(userLogin.getApellidos());
        settipo_documentacion(userLogin.getTipo_documentacion());
        setnum_doc_identidad(userLogin.getNum_doc_identidad());
        setfecha_nacimiento(userLogin.getFecha_nacimiento());
        setnivel_estudios(userLogin.getNivel_estudios());
        setocupacion(userLogin.getOcupacion());
        setcorreo(userLogin.getCorreo());
        setdireccion(userLogin.getDireccion());
        setdistrito(userLogin.getDistrito());
        setprovincia(userLogin.getProvincia());
        setdepartamento(userLogin.getDepartamento());
        settelefono(userLogin.getTelefono());
        setcontrasenia(userLogin.getContrasenia());
        setestado(userLogin.getEstado());
        setdes_estudios(userLogin.getDes_estudios());
        setdes_estado(userLogin.getDes_estado());
        setespecialista_idespe(userLogin.getEspecialista_idespe());
        settutor_idtutor(userLogin.getTutor_idtutor());
    }

    public void deletePaciente(){
        setUserIsLogged(false);
        setidpaciente(NO_EXISTE_VALOR);
        setnombre("");
        setapellidos("");
        settipo_documentacion("");
        setnum_doc_identidad("");
        setfecha_nacimiento("");
        setnivel_estudios(NO_EXISTE_VALOR);
        setocupacion("");
        setcorreo("");
        setdireccion("");
        setdistrito("");
        setprovincia("");
        setdepartamento("");
        settelefono(NO_EXISTE_VALOR);
        setcontrasenia("");
        setestado(NO_EXISTE_VALOR);
        setdes_estudios("");
        setdes_estado("");
        setespecialista_idespe(NO_EXISTE_VALOR);
        settutor_idtutor(NO_EXISTE_VALOR);
    }

    public void saveTutor(Tutor userLogin){
        setUserIsLogged(true);
        setidtutor(userLogin.getIdtutor());
        setnombre(userLogin.getNombre());
        setapellidos(userLogin.getApellidos());
        settipo_documentacion(userLogin.getTipo_documentacion());
        setnum_doc_identidad(userLogin.getNum_doc_identidad());
        setfecha_nacimiento(userLogin.getFecha_nacimiento());
        settelefono(userLogin.getTelefono());
        setcorreo(userLogin.getCorreo());
        setcontrasenia(userLogin.getContrasenia());
        setrelacion(userLogin.getRelacion());
        setestado(userLogin.getEstado());
        setdes_estado(userLogin.getDes_estado());
    }

    public void deleteTutor(){
        setUserIsLogged(false);
        setidtutor(NO_EXISTE_VALOR);
        setnombre("");
        setapellidos("");
        settipo_documentacion("");
        setnum_doc_identidad("");
        setfecha_nacimiento("");
        settelefono(NO_EXISTE_VALOR);
        setcorreo("");
        setcontrasenia("");
        setrelacion("");
        setestado(NO_EXISTE_VALOR);
        setdes_estado("");
    }
}
