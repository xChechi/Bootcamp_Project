package chechi.nino.bootcamp.initiliazer;

import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.car.CarType;
import chechi.nino.bootcamp.entity.car.Image;
import chechi.nino.bootcamp.repository.CarRepository;
import chechi.nino.bootcamp.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

@Component
@AllArgsConstructor
public class CarDataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {

        if (carRepository.count() == 0) {
            initializeCars();
        }

    }

    private void initializeCars() throws IOException {

        byte[] imageData1 = loadImageData("sport/ferrari/1.jpg");
        byte[] imageData2 = loadImageData("sport/ferrari/2.jpg");
        byte[] imageData3 = loadImageData("sport/ferrari/3.jpg");

        Car car1 = Car.builder()
                .model("Ferrari F8")
                .year("2021")
                .carType(CarType.SPORT)
                .build();

        Image image1 = Image.builder()
                .imagePath("ferrari_f8_1.jpg")
                .imageData(imageData1)
                .car(car1)
                .build();

        Image image2 = Image.builder()
                .imagePath("ferrari_f8_2.jpg")
                .imageData(imageData2)
                .car(car1)
                .build();

        Image image3 = Image.builder()
                .imagePath("ferrari_f8_3.jpg")
                .imageData(imageData3)
                .car(car1)
                .build();

        car1.getImages().add(image1);
        car1.getImages().add(image2);
        car1.getImages().add(image3);


/*

        Car car2 = Car.builder()
                .model("Lamborghini Urus")
                .year("2020")
                .carType(CarType.SPORT)
                .build();

        Image image4 = Image.builder()
                .imagePath("sport/lamborghini/lambo_1.jpg")
                .car(car2)
                .build();

        Image image5 = Image.builder()
                .imagePath("sport/lamborghini/lambo_2.jpg")
                .car(car2)
                .build();

        Image image6 = Image.builder()
                .imagePath("sport/lamborghini/lambo_3.jpg")
                .car(car2)
                .build();

        car2.getImages().add(image4);
        car2.getImages().add(image5);
        car2.getImages().add(image6);
*/
        //imageRepository.saveAll(Arrays.asList(image1, image2, image3));
        carRepository.save(car1);
    }

    private byte[] loadImageData(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/" + imagePath));

        // Compress the image data using JPEG format
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);

        return Base64.getEncoder().encode(outputStream.toByteArray());
    }

}
