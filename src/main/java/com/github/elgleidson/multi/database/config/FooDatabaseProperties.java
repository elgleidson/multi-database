package com.github.elgleidson.multi.database.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.db1")
public class FooDatabaseProperties extends AppDatabaseProperties {

}
