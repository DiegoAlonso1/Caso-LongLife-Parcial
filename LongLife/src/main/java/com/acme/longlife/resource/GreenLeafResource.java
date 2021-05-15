package com.acme.longlife.resource;

public class GreenLeafResource {
    private Long id;
    private String title;
    private String scenario;
    private String tip;

    public Long getId() {
        return id;
    }

    public GreenLeafResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GreenLeafResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public GreenLeafResource setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public GreenLeafResource setTip(String tip) {
        this.tip = tip;
        return this;
    }
}
