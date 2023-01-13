package pl.krzysztofwywial.enums;
public enum CarType {
    HATCHBACK ("Hatchback"),
    KOMBI ("Kombi"),
    COUPE ("Coupe"),
    SEDAN ("Sedan"),
    SUV ("SUV"),
    VAN ("VAN");

    private  final String description;

    CarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
