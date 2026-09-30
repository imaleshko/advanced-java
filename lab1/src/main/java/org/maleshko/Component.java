package org.maleshko;

public abstract class Component {
    protected final Mediator mediator;

    public Component(Mediator mediator) {
        this.mediator = mediator;
    }
}
