package pl.krzysztofwywial.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CarType {
    HATCHBACK ("Hatchback"),
    KOMBI ("Kombi"),
    COUPE ("Coupe"),
    SEDAN ("Sedan"),
    SUV ("SUV"),
    VAN ("VAN");

    private  final String description;

}
