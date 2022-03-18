package com.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(JUnitParamsRunner.class)
public class FelineTest {

    @Test
    public void shouldReturnFelinesFamilyWhenCallsGetFamily() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }
    @Test
    public void shouldReturnAllFamiliesWhenCallsGetFamily() {
        Animal animal = new Animal();
        assertEquals("Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи", animal.getFamily());
    }

    @Test
    public void shouldReturn1WhenCallsGetKittensWithoutParams() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void shouldReturnNumberWhenCallsGetKittensWithNumberOfKittens() {
        Feline feline = new Feline();
        assertEquals(165, feline.getKittens(165));
    }

    @Test
    public void shouldReturnCorrectListOfPredatorsFoodWhenCallsEatMeat() throws Exception {
        Feline feline = new Feline();
        var expected = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expected, feline.eatMeat());
    }

    @Parameters
    @Test
    public void shouldReturnListOfFoodWhenSetExistingAnimalKind(String animalKind, List<String> food) throws Exception {
        Feline feline = new Feline();
        assertEquals(food, feline.getFood(animalKind));
    }
    private Object parametersForShouldReturnListOfFoodWhenSetExistingAnimalKind() {
        return new Object[]{
                new Object[]{"Хищник", List.of("Животные", "Птицы", "Рыба")},
                new Object[]{"Травоядное", List.of("Трава", "Различные растения")}
        };
    }

    @Test
    public void shouldThrowExceptionWhenSetNotExistingAnimalKind() {
       var expected = "Неизвестный вид животного, используйте значение Травоядное или Хищник";
       var exception = assertThrows(Exception.class, () -> {
            new Feline().getFood("Насекомое");
        });
        assertEquals(expected, exception.getMessage());
    }
}