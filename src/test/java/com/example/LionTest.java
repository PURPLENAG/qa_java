package com.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class LionTest {
    Feline feline = Mockito.mock(Feline.class);

    @Parameters({
            "Самец, true",
            "Самка, false"
    })
    @Test
    public void shouldReturnCorrectHasManeWhenSetExistingSex(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals(expected, lion.hasMane); //hasMane по-хорошему д.б. private, но т.к. я имею к нему напрямую доступ - проверяю и его.
        assertEquals(expected, lion.doesHaveMane());
    }

    @Parameters({
            "САМЕЦ",
            "СамкА",
            "самец",
            "Сам",
            "С",
            "Саме",
            "Котик",
            "цемаС",
            "Самец*",
            "7Самка",
            "Самец Самка",
            "СамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамкаСамецСамка",
            "Сaмeц",
            "Male",
            "Samka",
            "      ",
            "!",
            "%%?)__+№__",
            "90832",
            "null"


    })
    @Test
    public void shouldThrowExceptionWhenSetNotExistingSex(String sex) {
        assertThrows(Exception.class, () -> {
            new Lion(sex, feline);
        });
    }

    @Test
    public void shouldBeCorrectExceptionTextWhenThrow(){
        var sex = "Male";
        var expected = "Используйте допустимые значения пола животного - самец или самка";
        var exception = assertThrows(Exception.class, () -> {
            new Lion(sex, feline);
        });
        assertEquals(expected,exception.getMessage());
    }

    @Test
    public void shouldReturn1WhenCallsGetKittensWithoutParams() throws Exception {
        Lion lion = new Lion("Самка", feline);
        when(feline.getKittens()).thenReturn(1);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void shouldReturnCorrectListOfPredatorsFoodWhenCallsGetFood() throws Exception {
        Lion lion = new Lion("Самка", feline);
        var expected = List.of("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expected);
        assertEquals(expected, lion.getFood());
    }
}