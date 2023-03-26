package com.galinazabelina.core.model.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Account.class, Operation.class, User.class})
public class EntityConfig {
}
