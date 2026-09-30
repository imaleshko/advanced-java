package org.maleshko;

public class Alarm extends Component {

    public Alarm(Mediator mediator) {
        super(mediator);
    }

    public void turnOn() {
        System.out.println("Сигналізацію ввімкнено");
        mediator.notify(this, "turn on");
    }

    public void turnOff() {
        System.out.println("Сигналізацію вимкнено");
        mediator.notify(this, "turn off");
    }

    public void goOff() {
        System.out.println("Сигналізація активована");
    }
}
