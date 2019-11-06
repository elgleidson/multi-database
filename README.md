# multi-database

A simple multi-database application using spring boot.

This application has 2 databases:
* **multi_db1**, a PostgreSQL database used by _Foo_ and _FooRepository_ classes (and _foo_ table)
* **multi_db2**, a MySQL database used by _Bar_ and _BarRepository_ classes (and _bar_ table)

The migration is done by Liquibase.
