package org.maleshko;

public class Main {
    void main() {
        WarehouseMediator warehouseMediator = new WarehouseMediator();

        Sensor sensor = new Sensor(warehouseMediator);
        Light light = new Light(warehouseMediator);
        Alarm alarm = new Alarm(warehouseMediator);

        warehouseMediator.registerComponents(sensor, light, alarm);

        sensor.detect();
        light.turnOff();

        alarm.turnOn();
        sensor.detect();

        alarm.turnOff();
        sensor.detect();
    }
}
