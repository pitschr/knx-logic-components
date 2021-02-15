package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Increment} logic component
 */
// @formatter:off
class IncrementTest extends AbstractTest {

    @Test
    @DisplayName("INCREMENT-Logic: Initialization state")
    void testInitialization() {
        // Logic with
        // * 2x Static Inputs
        // * 1x Static Output
        assertLogic(Increment.class)
                .numberOfInputConnectors(2)
                .numberOfOutputConnectors(1)
                .input("input", 0)
                .input("steps", 1)
                .output("output", 0);
    }

    @Test
    @DisplayName("INCREMENT-Logic (default): 2 + 1 = 3")
    void testWithDefaultSteps() {
        LogicScenario
                .given(Increment.class)
                    .input("input", 2)
                    .input("steps", 1)
                .then()
                    .output("output", 3)
                .verify();
    }

    @Test
    @DisplayName("INCREMENT-Logic (zero): 7 + 0 = 7")
    void testWithZeroSteps() {
        LogicScenario
                .given(Increment.class)
                    .input("input", 7)
                    .input("steps", 0)
                .then()
                    .output("output", 7)
                .verify();
    }

    @Test
    @DisplayName("INCREMENT-Logic (increment): 13 + 7 = 20")
    void testWithPositiveSteps() {
        LogicScenario
                .given(Increment.class)
                    .input("input", 13)
                    .input("steps", 7)
                .then()
                    .output("output", 20)
                .verify();
    }

    @Test
    @DisplayName("INCREMENT-Logic (increment): -9 + 8 = -1")
    void testWithPositiveStepsAndNegativeInput() {
        LogicScenario
                .given(Increment.class)
                    .input("input", -9)
                    .input("steps", 8)
                .then()
                    .output("output", -1)
                .verify();
    }

    @Test
    @DisplayName("INCREMENT-Logic (decrement): 19 + -14 = 5")
    void testWithNegativeSteps() {
        LogicScenario
                .given(Increment.class)
                    .input("input", 19)
                    .input("steps", -14)
                .then()
                    .output("output", 5)
                .verify();
    }

    @Test
    @DisplayName("INCREMENT-Logic (decrement): 23 + -50 = 5")
    void testWithNegativeStepsAndNegativeOutput() {
        LogicScenario
                .given(Increment.class)
                    .input("input", 23)
                    .input("steps", -50)
                .then()
                    .output("output", -27)
                .verify();
    }
}
