package org.maleshko;

public class Sensor extends Component {

    public Sensor(Mediator mediator) {
        super(mediator);
    }

    public void detect() {
        mediator.notify(this, "detect");
    }
}
