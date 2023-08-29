package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.repository.CarRepository;
import chechi.nino.bootcamp.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<CarResponse> getAllCars() {
        return null;
    }

    @Override
    public CarResponse getCarById(Integer id) {
        return null;
    }
}
