package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.car.CarResponse;

import java.util.List;

public interface CarService {

    List<CarResponse> getAllCars ();

    CarResponse getCarById (Integer id);
}
