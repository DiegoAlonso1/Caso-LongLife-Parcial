package com.acme.longlife.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveGreenLeafResource {
    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 120)
    private String scenario;

    @NotNull
    private String tip;

    public String getTitle() {
        return title;
    }

    public SaveGreenLeafResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public SaveGreenLeafResource setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public SaveGreenLeafResource setTip(String tip) {
        this.tip = tip;
        return this;
    }
}
