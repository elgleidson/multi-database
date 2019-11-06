package com.github.elgleidson.multi.database.domain.db2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bar")
public class Bar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        return "Bar [id=" + id + ", description=" + description + "]";
    }

}
