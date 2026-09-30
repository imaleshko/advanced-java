package org.maleshko;

public class WarehouseMediator implements Mediator {
    private Sensor sensor;
    private Light light;
    private Alarm alarm;

    private boolean isAlarmOn;
    private boolean isLightOn;

    public void registerComponents(Sensor sensor, Light light, Alarm alarm) {
        this.sensor = sensor;
        this.light = light;
        this.alarm = alarm;
    }

    @Override
    public void notify(Component sender, String event) {
        switch (sender) {
            case Sensor _ -> {
                if (event.equals("detect") && isAlarmOn) {
                    alarm.goOff();
                } else if (event.equals("detect") && !isLightOn) {
                    light.turnOn();
                    isLightOn = true;
                }

            }
            case Light _ -> {
                if (event.equals("turn off")) {
                    isLightOn = false;
                }
            }
            case Alarm _ -> {
                if (event.equals("turn on")) {
                    isAlarmOn = true;
                } else if (event.equals("turn off")) {
                    isAlarmOn = false;
                }
            }
            default -> System.out.println("Відбулася дивна ситуація");
        }

    }
}
