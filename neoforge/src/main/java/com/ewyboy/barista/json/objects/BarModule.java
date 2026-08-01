package com.ewyboy.barista.json.objects;

public class BarModule {

    private String name;
    private boolean display;
    private String context;

    public BarModule(String moduleType, boolean display) {
        this.name = moduleType.toLowerCase();
        this.display = display;
        this.context = null;
    }

    public BarModule(String moduleType, boolean display, String context) {
        this.name = moduleType.toLowerCase();
        this.display = display;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ModuleObject{" +
            "name='" + name + '\'' +
            ", display=" + display +
        '}';
    }
}
