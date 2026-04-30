package Decorator;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void deveRetornarCargaHorariaCurso() {
        Curso curso = new CursoGraduacao(1000.0f);

        assertEquals(1000.0f, curso.getCargaHoraria());
    }
}