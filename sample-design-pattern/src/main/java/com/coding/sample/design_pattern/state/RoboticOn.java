package com.coding.sample.design_pattern.state;

public class RoboticOn implements RoboticState {

    private final Robot robot;

    public RoboticOn(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void walk() {
        System.out.println("Walking...");
    }

    @Override
    public void cook() {
        System.out.println("Cooking...");
        robot.setRoboticState(robot.getRoboticCook());
    }

    @Override
    public void off() {
        robot.setRoboticState(robot.getRoboticOff());
        System.out.println("Robot is switched off");
    }

}
