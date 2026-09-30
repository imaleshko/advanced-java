package org.maleshko;

public class Light extends Component {

    public Light(Mediator mediator) {
        super(mediator);
    }

    public void turnOn() {
        System.out.println("Світло ввімкнено");
    }

    public void turnOff() {
        System.out.println("Світло вимкнено");
        mediator.notify(this, "turn off");
    }
}
