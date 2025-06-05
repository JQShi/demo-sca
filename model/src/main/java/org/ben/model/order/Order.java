package org.ben.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ben.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private BigDecimal amount;

    private Long userId;

    private List<Product> productList;

}
