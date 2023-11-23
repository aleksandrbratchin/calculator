package ru.bratchin.calculator.service.api;

public interface CalculatorMessageService<T extends Number> {

    String welcome();
    String plus(T a, T b);
    String multiply(T a, T b);
    String divide(T a, T b);
    String minus(T a, T b);

}
