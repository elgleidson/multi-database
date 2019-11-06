package com.github.elgleidson.multi.database.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.db2")
public class BarDatabaseProperties extends AppDatabaseProperties {

}
