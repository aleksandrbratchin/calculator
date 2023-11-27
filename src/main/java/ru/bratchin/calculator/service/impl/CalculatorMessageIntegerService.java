package ru.bratchin.calculator.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bratchin.calculator.service.api.CalculatorMessageService;
import ru.bratchin.calculator.service.api.CalculatorService;

@Service
public class CalculatorMessageIntegerService implements CalculatorMessageService<Integer> {

    private final CalculatorService<Integer> calculatorService;

    public CalculatorMessageIntegerService(@Qualifier("calculatorIntegerService") CalculatorService<Integer> calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public String welcome() {
        return "Добро пожаловать в калькулятор!";
    }

    @Override
    public String plus(Integer a, Integer b) {
        return a + " + " + b + " = " + calculatorService.plus(a, b);
    }

    @Override
    public String multiply(Integer a, Integer b) {
        return a + " * " + b + " = " + calculatorService.multiply(a, b);
    }

    @Override
    public String divide(Integer a, Integer b) {
        return a + " / " + b + " = " + calculatorService.divide(a, b);
    }

    @Override
    public String minus(Integer a, Integer b) {
        return a + " - " + b + " = " + calculatorService.minus(a, b);
    }

}
