/*
 *
 *  * Business.java
 *  *
 *  * Created by Rafsan Ahmad on 05/23/26, 3:23 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Stream;

import java.util.List;

class Business {

    private final String name;
    private final List<Person> employees;

    public Business(String name, List<Person> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Person> getEmployees() {
        return employees;
    }
}
