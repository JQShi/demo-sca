package org.ben.service.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(value = "order")
@Component
@Data
public class OrderProperties {

    private String timeout;

    private String autoConfirm;

    private String dbUrl;

}
