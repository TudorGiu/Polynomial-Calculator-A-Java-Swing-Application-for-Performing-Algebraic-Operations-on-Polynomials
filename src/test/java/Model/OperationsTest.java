package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class OperationsTest {


    @Test
    public void runAllTests() throws DivisionByZeroException {
        additionTest();
        subtractionTest();
        multiplicationTest();
        divisionTest();
        integrationTest();
        differentiationTest();
    }

    @Test
    public void additionTest(){
        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();

        pol1.getMonomials().put(2,2.);
        pol1.getMonomials().put(3,6.);
        pol1.getMonomials().put(1,-12.);

        pol2.getMonomials().put(5,2.);
        pol2.getMonomials().put(10,1.);
        pol2.getMonomials().put(4,0.);

        Polynomial result = new Polynomial();
        result.getMonomials().put(1,-12.0);
        result.getMonomials().put(2, 2.0);
        result.getMonomials().put(3,6.0);
        result.getMonomials().put(5,2.0);
        result.getMonomials().put(10,1.0);

        assertEquals(result.getMonomials(), Operations.addition(pol1, pol2).getPol1().getMonomials());
    }

    @Test
    public void subtractionTest(){
        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();

        pol1.getMonomials().put(2,2.);
        pol1.getMonomials().put(3,6.);
        pol1.getMonomials().put(1,-12.);

        pol2.getMonomials().put(1,7.);
        pol2.getMonomials().put(2,9.);
        pol2.getMonomials().put(10,10.);

        Polynomial result = new Polynomial();
        result.getMonomials().put(1,-19.0);
        result.getMonomials().put(2, -7.0);
        result.getMonomials().put(3,6.);
        result.getMonomials().put(10,-10.0);

        assertEquals(result.getMonomials(), Operations.subtraction(pol1, pol2).getPol1().getMonomials());
    }

    @Test
    public void multiplicationTest(){
        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();
        Polynomial pol3 = new Polynomial();

        pol1.getMonomials().put(2,2.);
        pol1.getMonomials().put(3,6.);
        pol1.getMonomials().put(1,-12.);

        pol2.getMonomials().put(1,7.);
        pol2.getMonomials().put(2,9.);
        pol2.getMonomials().put(10,10.);

        pol3.getMonomials().put(0,0.);

        Polynomial result1 = new Polynomial();
        result1.getMonomials().put(2,-84.0);
        result1.getMonomials().put(3, -94.0);
        result1.getMonomials().put(4,60.);
        result1.getMonomials().put(5,54.0);
        result1.getMonomials().put(11,-120.0);
        result1.getMonomials().put(12,20.0);
        result1.getMonomials().put(13,60.0);

        Polynomial result2 = new Polynomial();

        assertEquals(result1.getMonomials(), Operations.multiplication(pol1, pol2).getPol1().getMonomials());
        assertEquals(result2.getMonomials(), Operations.multiplication(pol1, pol3).getPol1().getMonomials());
    }

    @Test
    public void divisionTest() throws DivisionByZeroException {
        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();

        pol1.getMonomials().put(10,1.);
        pol1.getMonomials().put(0,2.);

        pol2.getMonomials().put(5,1.);

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        quotient.getMonomials().put(5,1.);

        remainder.getMonomials().put(0,2.);


        assertEquals(quotient.getMonomials(), Operations.division(pol1, pol2).getPol1().getMonomials());
        assertEquals(remainder.getMonomials(), Operations.division(pol1, pol2).getPol2().getMonomials());
    }

    @Test
    public void integrationTest(){
        Polynomial pol = new Polynomial();

        pol.getMonomials().put(3,1.);
        pol.getMonomials().put(2,5.);
        pol.getMonomials().put(1,-0.2);

        Polynomial result = new Polynomial();

        result.getMonomials().put(4,0.25);
        result.getMonomials().put(3,5./3);
        result.getMonomials().put(2,-0.1);

        assertEquals(result.getMonomials(), Operations.integration(pol).getPol1().getMonomials());
    }

    @Test
    public void differentiationTest(){
        Polynomial pol = new Polynomial();

        pol.getMonomials().put(3,1.);
        pol.getMonomials().put(2,5.);
        pol.getMonomials().put(1,-0.2);

        Polynomial result = new Polynomial();

        result.getMonomials().put(2,3.);
        result.getMonomials().put(1,10.);
        result.getMonomials().put(0,-0.2);

        assertEquals(result.getMonomials(), Operations.differentiation(pol).getPol1().getMonomials());
    }
}