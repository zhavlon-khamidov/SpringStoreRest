package kg.alatoo.storeapi.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private UUID id;
    private String name;
    private Double price;
    private String description;
}
