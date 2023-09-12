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

        Car car3 = Car.builder()
                .model("Audi R8")
                .year("2021")
                .carType(CarType.SPORT)
                .build();

        Image image7 = Image.builder()
                .imagePath("sport/audi/audi_r8_1.jpg")
                .car(car3)
                .build();

        Image image8 = Image.builder()
                .imagePath("sport/audi/audi_r8_2.jpg")
                .car(car3)
                .build();

        Image image9 = Image.builder()
                .imagePath("sport/audi/audi_r8_3.jpg")
                .car(car3)
                .build();

        car3.getImages().add(image7);
        car3.getImages().add(image8);
        car3.getImages().add(image9);

        Car car4 = Car.builder()
                .model("Mercedes S class")
                .year("2021")
                .carType(CarType.SEDAN)
                .build();

        Image image10 = Image.builder()
                .imagePath("sedan/mercedes/mercedes_s_1.jpg")
                .car(car4)
                .build();

        Image image11 = Image.builder()
                .imagePath("sedan/mercedes/mercedes_s_2.jpg")
                .car(car4)
                .build();

        Image image12 = Image.builder()
                .imagePath("sedan/mercedes/mercedes_s_3.jpg")
                .car(car4)
                .build();

        car4.getImages().add(image10);
        car4.getImages().add(image11);
        car4.getImages().add(image12);

        Car car5 = Car.builder()
                .model("Audi A8")
                .year("2021")
                .carType(CarType.SEDAN)
                .build();

        Image image13 = Image.builder()
                .imagePath("sedan/audi/audi_a8_1.jpg")
                .car(car5)
                .build();

        Image image14 = Image.builder()
                .imagePath("sedan/audi/audi_a8_2.jpg")
                .car(car5)
                .build();

        Image image15 = Image.builder()
                .imagePath("sedan/audi/audi_a8_3.jpg")
                .car(car5)
                .build();

        car5.getImages().add(image13);
        car5.getImages().add(image14);
        car5.getImages().add(image15);

        Car car6 = Car.builder()
                .model("BMW 5 Series")
                .year("2020")
                .carType(CarType.SEDAN)
                .build();

        Image image16 = Image.builder()
                .imagePath("sedan/bmw/bmw_5_1.jpg")
                .car(car6)
                .build();

        Image image17 = Image.builder()
                .imagePath("sedan/bmw/bmw_5_2.jpg")
                .car(car6)
                .build();

        Image image18 = Image.builder()
                .imagePath("sedan/bmw/bmw_5_3.jpg")
                .car(car6)
                .build();

        car6.getImages().add(image16);
        car6.getImages().add(image17);
        car6.getImages().add(image18);

        Car car7 = Car.builder()
                .model("VW Passat B9")
                .year("2021")
                .carType(CarType.SEDAN)
                .build();

        Image image19 = Image.builder()
                .imagePath("sedan/vw/vw_passat_1.jpg")
                .car(car7)
                .build();

        Image image20 = Image.builder()
                .imagePath("sedan/vw/vw_passat_2.jpg")
                .car(car7)
                .build();

        Image image21 = Image.builder()
                .imagePath("sedan/vw/vw_passat_3.jpg")
                .car(car7)
                .build();

        car7.getImages().add(image19);
        car7.getImages().add(image20);
        car7.getImages().add(image21);

        Car car8 = Car.builder()
                .model("Mercedes V Class")
                .year("2021")
                .carType(CarType.VAN)
                .build();

        Image image22 = Image.builder()
                .imagePath("van/mercedes/mercedes_v_1.jpg")
                .car(car8)
                .build();

        Image image23 = Image.builder()
                .imagePath("van/mercedes/mercedes_v_2.jpg")
                .car(car8)
                .build();

        Image image24 = Image.builder()
                .imagePath("van/mercedes/mercedes_v_3.jpg")
                .car(car8)
                .build();

        car8.getImages().add(image22);
        car8.getImages().add(image23);
        car8.getImages().add(image24);

        Car car9 = Car.builder()
                .model("VW R Line")
                .year("2020")
                .carType(CarType.VAN)
                .build();

        Image image25 = Image.builder()
                .imagePath("van/vw/vw_r_1.jpg")
                .car(car9)
                .build();

        Image image26 = Image.builder()
                .imagePath("van/vw/vw_r_2.jpg")
                .car(car9)
                .build();

        Image image27 = Image.builder()
                .imagePath("van/vw/vw_r_3.jpg")
                .car(car9)
                .build();

        car9.getImages().add(image25);
        car9.getImages().add(image26);
        car9.getImages().add(image27);

        Car car10 = Car.builder()
                .model("BMW")
                .year("2020")
                .carType(CarType.VAN)
                .build();

        Image image28 = Image.builder()
                .imagePath("van/bmw/bmw_1.jpg")
                .car(car10)
                .build();

        Image image29 = Image.builder()
                .imagePath("van/bmw/bmw_2.jpg")
                .car(car10)
                .build();

        Image image30 = Image.builder()
                .imagePath("van/bmw/bmw_3.jpg")
                .car(car10)
                .build();

        car10.getImages().add(image28);
        car10.getImages().add(image29);
        car10.getImages().add(image30);

        Car car11 = Car.builder()
                .model("Hyundai H-1")
                .year("2020")
                .carType(CarType.VAN)
                .build();

        Image image31 = Image.builder()
                .imagePath("van/hyundai/hyundai_h_1.jpg")
                .car(car11)
                .build();

        Image image32 = Image.builder()
                .imagePath("van/hyundai/hyundai_h_2.jpg")
                .car(car11)
                .build();

        Image image33 = Image.builder()
                .imagePath("van/hyundai/hyundai_h_3.jpg")
                .car(car11)
                .build();

        car11.getImages().add(image31);
        car11.getImages().add(image32);
        car11.getImages().add(image33);

        carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10, car11));
    }

    private byte[] loadImageData(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/" + imagePath));

        // Compress the image data using JPEG format
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);

        return Base64.getEncoder().encode(outputStream.toByteArray());
    }

}
