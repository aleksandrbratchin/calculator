package ru.bratchin.calculator.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bratchin.calculator.exception.DivisionByZeroException;
import ru.bratchin.calculator.service.api.CalculatorMessageService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorMessageService<Integer> service;

    public CalculatorController(@Qualifier("calculatorMessageIntegerService") CalculatorMessageService<Integer> service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok(service.welcome());
    }

    @GetMapping("/plus")
    public ResponseEntity<?> plus(
            @RequestParam Integer num1,
            @RequestParam Integer num2
    ) {
        return ResponseEntity.ok(service.plus(num1, num2));
    }

    @GetMapping("/minus")
    public ResponseEntity<?> minus(
            @RequestParam Integer num1,
            @RequestParam Integer num2
    ) {
        return ResponseEntity.ok(service.minus(num1, num2));
    }

    @GetMapping("/multiply")
    public ResponseEntity<?> multiply(
            @RequestParam Integer num1,
            @RequestParam Integer num2
    ) {
        return ResponseEntity.ok(service.multiply(num1, num2));
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divide(
            @RequestParam Integer num1,
            @RequestParam Integer num2
    ) {
        return ResponseEntity.ok(service.divide(num1, num2));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> arithmeticException(ArithmeticException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DivisionByZeroException.class)
    public ResponseEntity<?> arithmeticException(DivisionByZeroException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
