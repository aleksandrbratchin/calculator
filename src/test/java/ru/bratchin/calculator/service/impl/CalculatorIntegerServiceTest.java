package ru.bratchin.calculator.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bratchin.calculator.exception.DivisionByZeroException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculatorIntegerServiceTest {

    private final CalculatorIntegerService calculatorIntegerService = new CalculatorIntegerService();

    @Nested
    class Success {
        @ParameterizedTest(name = "[{index}]: {0} + {1} = {2}")
        @CsvSource(
                value = {
                        "2, 2, 4",
                        "-2, -2, -4",
                        "-2, 2, 0",
                        "2, -2, 0",
                        "0, 0, 0",
                }
        )
        void plus(int a, int b, int result) {

            int actualSolution = calculatorIntegerService.plus(a, b);

            assertThat(actualSolution).isEqualTo(result);
        }

        @ParameterizedTest(name = "[{index}]: {0} * {1} = {2}")
        @CsvSource(
                value = {
                        "2, 2, 4",
                        "-2, -2, 4",
                        "-2, 2, -4",
                        "2, -2, -4",
                        "0, 0, 0",
                }
        )
        void multiply(int a, int b, int result) {

            int actualSolution = calculatorIntegerService.multiply(a, b);

            assertThat(actualSolution).isEqualTo(result);
        }

        @ParameterizedTest(name = "[{index}]: {0} - {1} = {2}")
        @CsvSource(
                value = {
                        "2, 2, 0",
                        "-2, -2, 0",
                        "-2, 2, -4",
                        "2, -2, 4",
                        "0, 0, 0",
                }
        )
        void minus(int a, int b, int result) {

            int actualSolution = calculatorIntegerService.minus(a, b);

            assertThat(actualSolution).isEqualTo(result);
        }

        @ParameterizedTest(name = "[{index}]: {0} / {1} = {2}")
        @CsvSource(
                value = {
                        "2, 2, 1",
                        "-2, -2, 1",
                        "-2, 2, -1",
                        "2, -2, -1",
                        "0, 1, 0",
                }
        )
        void divide(int a, int b, int result) {

            int actualSolution = calculatorIntegerService.divide(a, b);

            assertThat(actualSolution).isEqualTo(result);
        }
    }

    @Nested
    class Error {

        @Nested
        class Overflow {
            @Test
            void plus() {

                Throwable thrown = catchThrowable(() -> calculatorIntegerService.plus(Integer.MAX_VALUE, 1));

                assertThat(thrown).isInstanceOf(ArithmeticException.class);
            }

            @Test
            void multiply() {

                Throwable thrown = catchThrowable(() -> calculatorIntegerService.multiply(Integer.MAX_VALUE, 2));

                assertThat(thrown).isInstanceOf(ArithmeticException.class);
            }

            @Test
            void minus() {

                Throwable thrown = catchThrowable(() -> calculatorIntegerService.minus(Integer.MIN_VALUE, 1));

                assertThat(thrown).isInstanceOf(ArithmeticException.class);
            }
        }

        @Nested
        class DivisionByZero {
            @Test
            void divide() {

                Throwable thrown = catchThrowable(() -> calculatorIntegerService.divide(1, 0));

                assertThat(thrown).isInstanceOf(DivisionByZeroException.class);
            }
        }

    }

}