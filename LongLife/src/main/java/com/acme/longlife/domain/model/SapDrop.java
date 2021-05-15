package com.acme.longlife.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "sap_drops")
public class SapDrop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    private String summary;

    @NotNull
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "big_tree_id", nullable = false)
    @JsonIgnore
    private BigTree bigTree;



    public Long getId() {
        return id;
    }

    public SapDrop setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SapDrop setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public SapDrop setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SapDrop setContent(String content) {
        this.content = content;
        return this;
    }

    public BigTree getBigTree() {
        return bigTree;
    }

    public SapDrop setBigTree(BigTree bigTree) {
        this.bigTree = bigTree;
        return this;
    }
}
