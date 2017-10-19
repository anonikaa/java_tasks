package org;


import org.testng.Assert;
import org.testng.annotations.*;

public class TestCalc {

    private String sSum, sMin, sMul, sDiv, sDivByZero, sNegOneNumber, sNegTwoNumber, sNegAllNumber, sFormat,
    sFormat1, sFormat2, sToRound;

    private Calc ex;

    @Parameters({"sum", "min", "multiply", "div", "divByZero", "negOneNumber", "negTwoNumber", "negAllNumber", "format",
            "format1", "format2", "toRound"})
    @BeforeTest
    public void setUp(String sum, String min, String mul, String div, String divByZero, String negOneNumber, String negTwoNumber,
    String negAllNumber, String format, String format1, String format2, String toRound) {
        this.sMin = min;
        this.sSum = sum;
        this.sMul = mul;
        this.sDiv = div;
        this.sDivByZero = divByZero;
        this.sNegOneNumber = negOneNumber;
        this.sNegTwoNumber = negTwoNumber;
        this.sNegAllNumber = negAllNumber;
        this.sFormat = format;
        this.sFormat1 = format1;
        this.sFormat2 = format2;
        this.sToRound = toRound;
        ex = new Calc();
    }


    @Test
    public void testCountSum() { // сложение
        float f = ex.count(sSum);
        float exp = 3.21f;
        Assert.assertEquals(f, exp);

    }

    @Test
    public void testCountMin() { //  вычитание
        float f = ex.count(sMin);
        float exp = 1.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountMultiply() { // умножение
        float f = ex.count(sMul);
        float exp = 1.0f;
        Assert.assertEquals(f, exp);

    }

    @Test
    public void testCountDiv() { //деление
        float f = ex.count(sDiv);
        float exp = 3.00f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public  void testCountDivByZero (){
        float f = ex.count(sDivByZero);
        float exp = 0.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountNegOneNumber() { // первое число отрицательное
        float f = ex.count(sNegOneNumber);
        float exp = -2.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountNegTwoNumber() { // второе число отрицательное
        float f = ex.count(sNegTwoNumber);
        float exp = 6.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountNegAllNumber() { // оба числа отрицательные
        float f = ex.count(sNegAllNumber);
        float exp = -6.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountFormat() { // некорректный формат
        float f = ex.count(sFormat);
        float exp = 0.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountFormat1() { // некорректный формат1
        float f = ex.count(sFormat1);
        float exp = 0.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountFormat2() { // некорректный формат 2
        float f = ex.count(sFormat2);
        float exp = 0.0f;
        Assert.assertEquals(f, exp);
    }

    @Test
    public void testCountToRound() { // округление при делении
        float f = ex.count(sToRound);
        float exp = 3.327f;
        Assert.assertEquals(f, exp);
    }

    @AfterTest
    void tearDown(){
        ex = null;
    }
}
