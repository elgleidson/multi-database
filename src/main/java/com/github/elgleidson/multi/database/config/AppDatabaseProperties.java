package com.github.elgleidson.multi.database.config;

import java.util.HashMap;
import java.util.Map;

public class AppDatabaseProperties {

    private Database database = new Database();
    private Jpa jpa = new Jpa();
    private Liquibase liquibase = new Liquibase();

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Jpa getJpa() {
        return jpa;
    }

    public void setJpa(Jpa jpa) {
        this.jpa = jpa;
    }

    public Liquibase getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(Liquibase liquibase) {
        this.liquibase = liquibase;
    }

    public static class Database {
        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Jpa {
        private org.springframework.orm.jpa.vendor.Database database;
        private boolean showSql = false;
        private Map<String, String> properties = new HashMap<>();

        public org.springframework.orm.jpa.vendor.Database getDatabase() {
            return database;
        }

        public void setDatabase(org.springframework.orm.jpa.vendor.Database database) {
            this.database = database;
        }

        public boolean isShowSql() {
            return showSql;
        }

        public void setShowSql(boolean showSql) {
            this.showSql = showSql;
        }

        public Map<String, String> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }
    }

    public static class Liquibase {
        private String changeLog;

        public String getChangeLog() {
            return changeLog;
        }

        public void setChangeLog(String changeLog) {
            this.changeLog = changeLog;
        }
    }

}
