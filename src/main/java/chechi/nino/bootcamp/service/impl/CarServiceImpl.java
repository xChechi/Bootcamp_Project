package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.CarConverter;
import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.exception.CarNotFoundException;
import chechi.nino.bootcamp.repository.CarRepository;
import chechi.nino.bootcamp.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public List<CarResponse> getAllCars() {

        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carConverter::toCarResponse)
                .toList();
    }

    @Override
    public CarResponse getCarById(Integer id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));

        return carConverter.toCarResponse(car);
    }
}
