package ch.vorburger.rover;

import ch.vorburger.raspberry.mc33926.TwoMotorsProvider;
import ch.vorburger.raspberry.motors.TwoMotors;
import ch.vorburger.raspberry.turtle.AsyncTurtle;

public class Main {

    public static void main(String[] args) throws Exception {
        TwoMotors twoMotors = new TwoMotorsProvider().get();
        AsyncTurtle turtle = new AsyncTurtle(twoMotors);
        turtle.turnRight(90);
        // The sleep() here isn't great, but does the trick for now..
        // TODO later ideally Motors/Turtle API should have a waitToFinish() method?
        Thread.sleep(3000);
        twoMotors.disable();
    }

}
