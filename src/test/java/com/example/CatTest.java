package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    Cat cat;

    @Mock
    Feline feline;

    @Before
    public void before() {
        cat = new Cat(feline);
    }

    @Test
    public void shouldReturnCorrectSoundWhenCallsGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void shouldReturnCorrectListOfPredatorsFoodWhenCallsGetFood() throws Exception {
        var expected = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expected);
        assertEquals(expected, cat.getFood());
    }
}