package chechi.nino.bootcamp.initiliazer;

import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.car.CarType;
import chechi.nino.bootcamp.entity.car.Image;
import chechi.nino.bootcamp.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CarDataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;

    public CarDataInitializer(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (carRepository.count() == 0) {
            initializeCars();
        }
    }

    private void initializeCars() {

        Car car1 = Car.builder()
                .model("Ferrari F8")
                .year("2021")
                .carType(CarType.SPORT)
                .build();

        Image image1 = Image.builder()
                .imagePath("/images/sport/ferrari/ferrari_f8_1.jpg")
                .car(car1)
                .build();

        Image image2 = Image.builder()
                .imagePath("/images/sport/ferrari/ferrari_f8_2.jpg")
                .car(car1)
                .build();

        Image image3 = Image.builder()
                .imagePath("/images/sport/ferrari/ferrari_f8_3.jpg")
                .car(car1)
                .build();
    }
}
