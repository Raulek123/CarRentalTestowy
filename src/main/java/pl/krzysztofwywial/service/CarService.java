package pl.krzysztofwywial.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.krzysztofwywial.config.ImageConfig;
import pl.krzysztofwywial.exception.RecordNotFoundException;
import pl.krzysztofwywial.model.CarEntity;
import pl.krzysztofwywial.repository.CarRepository;



import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    @Autowired
    private ImageConfig imagePath;

    public List<CarEntity> getAllCars() {
        List<CarEntity> result = (List<CarEntity>) repository.findAll();
        result.sort(CarEntity::compareTo);
        return result;
    }

    public CarEntity getCarById(Long id) throws RecordNotFoundException {
        Optional<CarEntity> car = repository.findById(id);

        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RecordNotFoundException("No car record was found for given ID");
        }
    }

    public void createOrUpdateCar(CarEntity entity) {
        repository.save(entity);
    }

    public void deleteCarById(Long id) throws RecordNotFoundException {
        Optional<CarEntity> car = repository.findById(id);

        if (car.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No car record was found for given ID");
        }
    }

    public void saveImage(CarEntity car,
                          @RequestParam("image") MultipartFile multipartFile) throws Exception {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        car.setImage(fileName);

        CarEntity carSaved = repository.save(car);
        String uploadDiectory = imagePath.getImage() + carSaved.getId();
        saveFile(uploadDiectory, fileName, multipartFile);
    }

    private void saveFile(String uploadDiectory, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDiectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }

    private final CarEntity carEntity = new CarEntity();
    public String getImagePath() {
        if (carEntity.getImage() == null || carEntity.getId() == null) {
            return null;
        }
        return "/images/" + carEntity.getId() + "/" + carEntity.getImage();
    }
}

