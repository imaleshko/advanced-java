package org.maleshko;

public interface Mediator {
    void notify(Component sender, String event);
}
