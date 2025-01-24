package org.klimefimov.restservice.dto;

import java.io.Serial;
import java.io.Serializable;

public class CustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 500491044934077L;

    private final int id;
    private final String name;

    public CustomerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
