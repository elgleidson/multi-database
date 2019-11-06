package com.github.elgleidson.multi.database.domain.db1;

import javax.persistence.*;

@Entity
@Table(name = "foo")
public class Foo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_foo")
    @SequenceGenerator(name = "seq_foo", sequenceName = "seq_foo", allocationSize = 1)
    private Long id;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Foo [id=" + id + ", description=" + description + "]";
    }

}
