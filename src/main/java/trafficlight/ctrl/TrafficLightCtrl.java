package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

import java.util.Observable;

public class TrafficLightCtrl extends Observable {

    private static TrafficLightCtrl instance;

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        //TODO useful to update the current state

        notify(currentState);

    }

     public State getCurrentState() {
        return currentState;
    }

    public static TrafficLightCtrl getInstance() { //Singleton implementation of TrafficLightCtrl
        if (instance == null) {
            instance = new TrafficLightCtrl();
        }
        return instance;
    }

    public void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                currentState = greenState;
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                currentState = redState;

                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                currentState = yellowState;
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    return redState;
                } else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

//    public State getGreenState() {
//        return greenState;
//    }
//
//    public State getRedState() {
//        return redState;
//    }
//
//    public State getYellowState() {
//        return yellowState;
//    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
        notify(currentState);
    }

    private void notify(State state){
        setChanged();
        notifyObservers(state);
    }

    public void stop() {
        doRun = false;
    }
}