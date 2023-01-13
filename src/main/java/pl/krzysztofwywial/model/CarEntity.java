package pl.krzysztofwywial.model;

import pl.krzysztofwywial.enums.CarType;

import javax.persistence.*;

@Entity
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYearOfProduction() {
            return yearOfProduction;
        }

        public void setYearOfProduction(int yearOfProduction) {
            this.yearOfProduction = yearOfProduction;
        }

        public CarType getType() {
            return type;
        }

        public void setType(CarType type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImagePath() {
            if (image == null || id == null) {
                return null;
            }
                return "/images/" + id + "/" + image;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "CarEntity{" +
                    "id=" + id +
                    ", brand='" + brand + '\'' +
                    ", model='" + model + '\'' +
                    ", yearOfProduction=" + yearOfProduction +
                    ", type=" + type +
                    ", image='" + image + '\'' +
                    ", available=" + available +
                    '}';
        }

        @Override
        public int compareTo(CarEntity c) {
            return this.brand.compareToIgnoreCase(c.brand);
        }
    }
