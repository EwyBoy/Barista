package com.ewyboy.barista.json.objects;

import java.util.List;

public class BarConfig {

    private List<BarModule> moduleList;

    public BarConfig(List<BarModule> moduleList) {
        this.moduleList = moduleList;
    }

    public List<BarModule> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<BarModule> moduleList) {
        this.moduleList = moduleList;
    }

}