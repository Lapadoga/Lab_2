package com.company;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest  {
    Calculator number;
    @org.junit.jupiter.api.Test
    void computing() throws Exception {
        try {
            number = new Calculator("(3+7)*2-4*3+7");
            assertEquals(15, number.computing());
            number = new Calculator("11+2*4-(13-11)*4");
            assertEquals(11, number.computing());
            number = new Calculator("6+2*3+(8-4)-7");
            assertEquals(9, number.computing());
            number = new Calculator("4*(6-5)+12-2*8");
            assertEquals(0, number.computing());
            number = new Calculator("2*(5-2)+3*(10-8)");
            assertEquals(12, number.computing());
            number = new Calculator("5*(8-6)+6*(9-8)");
            assertEquals(16, number.computing());
            number = new Calculator("18-(2+2)*2+12+3");
            assertEquals(25, number.computing());
            number = new Calculator("(5+2+2)*2-11-3*2");
            assertEquals(1, number.computing());
            number = new Calculator("1024/128*15/10");
            assertEquals(12, number.computing());
            number = new Calculator("245/7-224/16+35*11");
            assertEquals(406, number.computing());
            number = new Calculator("15*(5408-5382/26+799)");
            assertEquals(90000, number.computing());
            number = new Calculator("-67+375*(-80+2)");
            assertEquals(-29317.0, number.computing());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }






    }
}