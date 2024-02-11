package net.mvergara.gestao_vagas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FirstTest {

    @Test
    public void deve_calcular_dois_numeros() {
        var result = calculate(2,3);
        assertEquals(5,result);
    }
    @Test
    public void validar_resultado_incorreto(){
        var result = calculate(2,3);
        assertNotEquals(4,result);
    }

    public static int calculate(int num1, int num2) {
        return num1 + num2;
    }

}
