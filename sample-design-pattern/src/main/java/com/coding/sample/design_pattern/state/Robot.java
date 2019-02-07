package com.coding.sample.design_pattern.state;

public class Robot implements RoboticState {

    private RoboticState roboticOn;
    private RoboticState roboticCook;
    private RoboticState roboticOff;

    private RoboticState roboticState;

    public Robot() {
        this.roboticOn = new RoboticOn(this);
        this.roboticCook = new RoboticCook(this);
        this.roboticOff = new RoboticOff(this);
        this.roboticState = roboticOn;
    }

    @Override
    public void walk() {
        roboticState.walk();
    }

    @Override
    public void cook() {
        roboticState.cook();
    }

    @Override
    public void off() {
        roboticState.off();
    }

    public RoboticState getRoboticOn() {
        return roboticOn;
    }

    public RoboticState getRoboticCook() {
        return roboticCook;
    }

    public RoboticState getRoboticOff() {
        return roboticOff;
    }

    public void setRoboticState(RoboticState roboticState) {
        this.roboticState = roboticState;
    }

}
