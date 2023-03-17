package org.sid.avertisingservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"org.sid.avertisingservice.service"})
@Import({JpaConfig.class})
public class InfrastructureTestConfig {
}
