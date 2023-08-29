package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.car.CarResponse;
import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.car.CarType;
import chechi.nino.bootcamp.entity.car.Image;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarConverter {

    public CarResponse toCarResponse (Car car) {

        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setModel(car.getModel());
        response.setYear(car.getYear());
        response.setCarType(car.getCarType());
        response.setImages(car.getImages());

        CarType carType = car.getCarType();
        response.setSeats(carType.getSeats());
        response.setDailyCharge(carType.getDailyCharge());

        return response;
    }
}
