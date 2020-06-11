package ru.shmakovofficial.perpod.controllers;

import ru.shmakovofficial.perpod.entities.Employer;
import ru.shmakovofficial.perpod.entities.Teacher;

class SelectReturn {
    public final Long value;
    public final String text;

    public SelectReturn(Employer e) {
        value = e.getId();
        text = e.getName() + " (" + e.getCity() + ")";
    }

    public SelectReturn(Teacher t) {
        value = t.getId();
        text = t.getLastName() + " " + t.getFirstName() + " " + t.getMiddleName();
    }
}
