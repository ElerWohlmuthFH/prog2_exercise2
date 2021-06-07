import org.junit.Test;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.State;

import static org.junit.Assert.*;

public class TrafficLightTest {

    @Test
    public void SingletonTest() {
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        TrafficLightCtrl ctrl1 = TrafficLightCtrl.getInstance();

        assertTrue(ctrl.equals(ctrl1));

    }
    @Test
    public void SingletonTest2() {
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        TrafficLightCtrl ctrl2 = TrafficLightCtrl.getInstance();

        State currentState = ctrl.getCurrentState();
        State currentState2 = ctrl2.getCurrentState();

        assertTrue(currentState.getColor().equals(currentState2.getColor()));

        ctrl.nextState();

        assertTrue(currentState.getColor().equals(currentState2.getColor()));

    }

    @Test
    public void Singleton3(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        assertNotNull(ctrl);
    }

    @Test
    public void current_next() {

        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance(); //if current = green, next ist yellow
        ctrl.initStates();

        String currentColor = ctrl.getCurrentState().getColor();

        String expectedColor = "green";
        assertTrue(expectedColor.equals(currentColor));

        ctrl.nextState();
        currentColor = ctrl.getCurrentState().getColor();

        expectedColor = "yellow";
        assertTrue(expectedColor.equals(currentColor));
    }
}
