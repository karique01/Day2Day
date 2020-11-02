package com.karique.work.dev.day2day.repositories;

import com.karique.work.dev.day2day.models.Pregunta;

import java.util.ArrayList;
import java.util.List;


public class PreguntasRepositories {
    private static PreguntasRepositories preguntasRepositories;
    private List<Pregunta> preguntas;

    private PreguntasRepositories() {
        preguntas = new ArrayList<>();
        preguntas.add(new Pregunta(1, "Poco interés o placer en hacer cosas.", 0));
        preguntas.add(new Pregunta(2, "Se ha sentido decaído(a), deprimido(a) o sin esperanzas.", 0));
        preguntas.add(new Pregunta(3, "Ha tenido dificultad para quedarse o permanecer dormido(a), o ha dormido demasiado.", 0));
        preguntas.add(new Pregunta(4, "Se ha sentido cansado(a) o con poca energía.", 0));
        preguntas.add(new Pregunta(5, "Sin apetito o ha comido en exceso.", 0));
        preguntas.add(new Pregunta(6, "Se ha sentido mal con usted mismo(a) – o que es un fracaso o que ha quedado mal con usted mismo(a) o con su familia.", 0));
        preguntas.add(new Pregunta(7, "Ha tenido dificultad para concentrarse en ciertas actividades, tales como leer el periódico o ver la televisión.", 0));
        preguntas.add(new Pregunta(8, "¿Se ha movido o hablado tan lento que otras personas podrían haberlo notado? o lo contrario – muy inquieto(a) o agitado(a) que ha estado moviéndose mucho más de lo normal.", 0));
        preguntas.add(new Pregunta(9, "Pensamientos de que estaría mejor muerto(a) o de lastimarse de alguna manera.", 0));
    }

    public static PreguntasRepositories getInstance(){
        if (preguntasRepositories == null){
            preguntasRepositories = new PreguntasRepositories();
        }
        return preguntasRepositories;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void reinciarRespuestas(){
        for (int i = 0; i < preguntas.size(); i++) {
            preguntas.get(i).setRespuesta(0);
        }
    }
}
