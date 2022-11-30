package pl.krzysztofwywial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.krzysztofwywial.exception.RecordNotFoundException;
import pl.krzysztofwywial.model.CarEntity;
import pl.krzysztofwywial.repository.CarRepository;

import javax.annotation.PostConstruct;
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


    public List<CarEntity> getAllCars() {
        List<CarEntity> result = (List<CarEntity>) repository.findAll();
        result.sort(CarEntity::compareTo);
        return result;
    }

    public CarEntity getCarById(Long id) throws RecordNotFoundException {
        Optional<CarEntity> car = Optional.empty();

        for (CarEntity entity : repository.findAll()) {
            if (entity.getId() == id) {
                car = Optional.of(entity);
                break;
            }
        }
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RecordNotFoundException("No car record was found for given ID");
        }
    }

    public CarEntity createOrUpdateCar(CarEntity entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);
            return entity;
        } else {
            Optional<CarEntity> car = repository.findById(entity.getId());

            if (car.isPresent()) {
                CarEntity newEntity = car.get();
                newEntity.setBrand(entity.getBrand());
                newEntity.setModel(entity.getModel());
                newEntity.setYear_of_production(entity.getYear_of_production());
                newEntity.setType(entity.getType());
                newEntity.setAvailable(entity.isAvailable());

                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        }
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
        String uploadDiectory = "images/" + carSaved.getId();
        saveFile(uploadDiectory, fileName, multipartFile);
    }

    private void saveFile(String uploadDiectory, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDiectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        } else {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save file: " + fileName, e);
            }
        }
    }
}
