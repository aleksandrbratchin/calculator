package ru.bratchin.calculator.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorMessageIntegerServiceTest {

    private final CalculatorMessageIntegerService messageService = new CalculatorMessageIntegerService(new CalculatorIntegerService());

    @Nested
    class Success {

        String regex = "^\\d+ [+*/-] \\d+ = \\d+$";

        @Test
        void welcome() {

            String message = messageService.welcome();

            assertThat(message).contains("Добро пожаловать");
        }

        @Test
        void plus() {

            String message = messageService.plus(2, 2);

            assertThat(message).matches(regex).contains(" + ");
        }

        @Test
        void multiply() {

            String message = messageService.multiply(2, 2);

            assertThat(message).matches(regex).contains(" * ");
        }

        @Test
        void minus() {

            String message = messageService.minus(2, 2);

            assertThat(message).matches(regex).contains(" - ");
        }

        @Test
        void divide() {

            String message = messageService.divide(2, 2);

            assertThat(message).matches(regex).contains(" / ");
        }

    }


}