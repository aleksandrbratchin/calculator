package ru.bratchin.calculator.controller;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class Success {

        private final String regex = "^\\d+ [+*/-] \\d+ = \\d+$";

        @Test
        void welcome() throws Exception {
            mockMvc.perform(get("/calculator")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString("калькулятор")
                    ))
                    .andExpect(content().string(
                            startsWith("Добро пожаловать")
                    ))
                    .andExpect(content().string(
                            matchesPattern("^.*!$")
                    ));
        }


        @Test
        void plus() throws Exception {
            mockMvc.perform(get("/calculator/plus")
                            .param("num1", "2")
                            .param("num2", "2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString(" + ")
                    ))
                    .andExpect(content().string(
                            matchesPattern(regex)
                    ));
        }

        @Test
        void minus() throws Exception {
            mockMvc.perform(get("/calculator/minus")
                            .param("num1", "2")
                            .param("num2", "2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString(" - ")
                    ))
                    .andExpect(content().string(
                            matchesPattern(regex)
                    ));
        }

        @Test
        void multiply() throws Exception {
            mockMvc.perform(get("/calculator/multiply")
                            .param("num1", "2")
                            .param("num2", "2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString(" * ")
                    ))
                    .andExpect(content().string(
                            matchesPattern(regex)
                    ));
        }

        @Test
        void divide() throws Exception {
            mockMvc.perform(get("/calculator/divide")
                            .param("num1", "2")
                            .param("num2", "2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(
                            containsString(" / ")
                    ))
                    .andExpect(content().string(
                            matchesPattern(regex)
                    ));
        }
    }

    @Nested
    class Error {

        @Nested
        class NoParameter {

            @Test
            void plus() throws Exception {
                mockMvc.perform(get("/calculator/plus")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void minus() throws Exception {
                mockMvc.perform(get("/calculator/minus")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void multiply() throws Exception {
                mockMvc.perform(get("/calculator/multiply")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void divide() throws Exception {
                mockMvc.perform(get("/calculator/divide")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

        }

        @Nested
        class OneParameter {

            @Test
            void plusNoNum1() throws Exception {
                mockMvc.perform(get("/calculator/plus")
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num1")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void plusNoNum2() throws Exception {
                mockMvc.perform(get("/calculator/plus")
                                .param("num1", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num2")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void minusNoNum1() throws Exception {
                mockMvc.perform(get("/calculator/minus")
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num1")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void minusNoNum2() throws Exception {
                mockMvc.perform(get("/calculator/minus")
                                .param("num1", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num2")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void multiplyNoNum1() throws Exception {
                mockMvc.perform(get("/calculator/multiply")
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num1")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void multiplyNoNum2() throws Exception {
                mockMvc.perform(get("/calculator/multiply")
                                .param("num1", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num2")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void divideNoNum1() throws Exception {
                mockMvc.perform(get("/calculator/divide")
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num1")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

            @Test
            void divideNoNum2() throws Exception {
                mockMvc.perform(get("/calculator/divide")
                                .param("num1", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(status().reason(startsWith("Required parameter")))
                        .andExpect(status().reason(containsString("num2")))
                        .andExpect(status().reason(endsWith("is not present.")));
            }

        }

        @Nested
        class DivisionByZero {
            @Test
            void divisionByZero() throws Exception {
                mockMvc.perform(get("/calculator/divide")
                                .param("num1", "2")
                                .param("num2", "0")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string((startsWith("Попытка деления на 0!"))));
            }
        }

        @Nested
        class Overflow {

            private final String MAX = String.valueOf(Integer.MAX_VALUE);
            private final String MIN = String.valueOf(Integer.MIN_VALUE);

            @Test
            void plus() throws Exception {
                mockMvc.perform(get("/calculator/plus")
                                .param("num1", MAX)
                                .param("num2", "1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string((startsWith("integer overflow"))));
            }

            @Test
            void multiply() throws Exception {
                mockMvc.perform(get("/calculator/multiply")
                                .param("num1", MAX)
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string((startsWith("integer overflow"))));
            }

            @Test
            void minus() throws Exception {
                mockMvc.perform(get("/calculator/minus")
                                .param("num1", MIN)
                                .param("num2", "2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string((startsWith("integer overflow"))));
            }
        }

    }

}