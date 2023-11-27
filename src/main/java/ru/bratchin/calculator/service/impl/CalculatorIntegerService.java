package ru.bratchin.calculator.service.impl;

import org.springframework.stereotype.Service;
import ru.bratchin.calculator.exception.DivisionByZeroException;
import ru.bratchin.calculator.service.api.CalculatorService;

@Service
public class CalculatorIntegerService implements CalculatorService<Integer> {

    @Override
    public Integer plus(Integer a, Integer b) {
        return Math.addExact(a, b);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return Math.multiplyExact(a, b);
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0) throw new DivisionByZeroException();
        return a / b;
    }

    @Override
    public Integer minus(Integer a, Integer b) {
        return Math.subtractExact(a, b);
    }

}
