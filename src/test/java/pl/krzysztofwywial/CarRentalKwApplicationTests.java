package pl.krzysztofwywial;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztofwywial.exception.RecordNotFoundException;
import pl.krzysztofwywial.service.CarService;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class CarRentalKwApplicationTests {

    @Test
    void carServiceGetCarByIdAssertThrowsRecordNotFoundException() {
        CarService service = new CarService();
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            service.getCarById(1L);
        });

        String exectedMessage = "No car record was found for given ID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(exectedMessage));
    }

    @Test
    public void carServiceDeleteCarByIdAssertThrowsRecordNotFoundException() {
        CarService service = new CarService();
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            service.deleteCarById(1L);
        });
        String exectedMessage = "No car record was found for given ID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(exectedMessage));
    }
}
