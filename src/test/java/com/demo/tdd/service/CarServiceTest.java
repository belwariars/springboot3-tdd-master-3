package com.demo.tdd.service;

import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.domain.Car;
import com.demo.tdd.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @Before
    public void setUp() throws Exception {
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarDetails_returnCarInfo() {
       given(carRepository.findByName("prius")).willReturn( new Car("prius","hybrid"));

       Car car = carService.getCarDetails("prius");

       assertThat(car.getName()).isEqualTo("prius");
       assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarDetails_whenCarNotFound() throws Exception{
        given(carRepository.findByName("prius")).willReturn(null);

        carService.getCarDetails("prius");
    }

}
