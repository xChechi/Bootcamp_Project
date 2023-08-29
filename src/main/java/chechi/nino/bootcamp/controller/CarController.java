package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponse>> findAllCars () {

        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findCarById (@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getCarById(id));
    }
}
