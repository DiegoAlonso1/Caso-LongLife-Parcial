package com.acme.longlife.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "green_leaves")
public class GreenLeaf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 120)
    private String scenario;

    @NotNull
    private String tip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "big_tree_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BigTree bigTree;



    public GreenLeaf setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public GreenLeaf setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public GreenLeaf setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public GreenLeaf setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public BigTree getBigTree() {
        return bigTree;
    }

    public GreenLeaf setBigTree(BigTree bigTree) {
        this.bigTree = bigTree;
        return this;
    }
}
