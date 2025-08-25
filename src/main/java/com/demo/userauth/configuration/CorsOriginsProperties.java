package com.demo.userauth.configuration;

import com.demo.userauth.common.constants.SecurityConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Cors - Allowed Origin Properties
 */
@Component
@ConfigurationProperties(prefix = SecurityConstants.CONFIG_CORS_PREFIX)
@Getter
@Setter
public class CorsOriginsProperties {

    private List<String> allowedOrigins;

}
