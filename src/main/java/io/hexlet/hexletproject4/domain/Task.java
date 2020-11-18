package io.hexlet.hexletproject4.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="status_id", nullable=false)
    private Status status;

    @ManyToMany(mappedBy = "tasks")
    private Set<Label> labels = new HashSet<>();
}
