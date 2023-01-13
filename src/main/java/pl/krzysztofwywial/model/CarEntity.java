package pl.krzysztofwywial.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.krzysztofwywial.enums.CarType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
    @Table(name = "CARS")
    public class CarEntity implements Comparable<CarEntity> {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "brand")
        private String brand;

        @Column(name = "model")
        private String model;

        @Column(name = "year_of_production")
        private int yearOfProduction;

        @Column(name = "type")
        private CarType type;

        @Column(name = "image")
        private String image;

        @Column(name = "available")
        private boolean available;


        public String getImagePath() {
            if (image == null || id == null) {
                return null;
            }
                return "/images/" + id + "/" + image;
        }

        @Override
        public int compareTo(CarEntity c) {
            return this.brand.compareToIgnoreCase(c.brand);
        }
    }
