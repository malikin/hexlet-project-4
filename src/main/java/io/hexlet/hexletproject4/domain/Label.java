package io.hexlet.hexletproject4.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToMany
    @JoinTable(name = "label_task",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<>();
}
