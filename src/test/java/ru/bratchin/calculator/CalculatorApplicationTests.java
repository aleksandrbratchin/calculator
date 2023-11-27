package ru.bratchin.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//Тестовый класс добавлен ТОЛЬКО для покрытия вызова main(), не охваченного тестами приложения.

@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
		CalculatorApplication.main(new String[]{});
	}

}
