package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Adder} logic component
 */
// @formatter:off
class AdderTest extends AbstractTest {

    @Test
    @DisplayName("ADDER-Logic: Initialization state")
    void testInitialization() {
        // Logic with
        // * 1x Dynamic Input
        // * 1x Static Output
        assertLogic(Adder.class)
                .numberOfInputConnectors(1)
                .numberOfOutputConnectors(1)
                .inputs("inputs", 0, 0) // min occurrence = 2
                .output("sum", 0);
    }

    @Test
    @DisplayName("ADDER-Logic: Add [0, 0, 0] = 0")
    void testWithZeros() {
        LogicScenario
                .given(Adder.class)
                    .inputs("inputs", 0, 0, 0)
                .then()
                    .output("sum", 0)
                .verify();
    }

    @Test
    @DisplayName("ADDER-Logic: Add [1, 2, 5, 11] = 19")
    void testWithPositive() {
        LogicScenario
                .given(Adder.class)
                    .inputs("inputs", 1, 2, 5, 11)
                .then()
                    .output("sum", 19)
                .verify();
    }

    @Test
    @DisplayName("ADDER-Logic: Add [-5, 7, -3, 11] = 10")
    void testWithPositiveAndNegative() {
        LogicScenario
                .given(Adder.class)
                    .inputs("inputs", -5, 7, -3, 11)
                .then()
                    .output("sum", 10)
                .verify();
    }

    @Test
    @DisplayName("ADDER-Logic: Add [-57, -12, -77, -33] = 179")
    void testWithNegative() {
        LogicScenario
                .given(Adder.class)
                    .inputs("inputs", -57, -12, -77, -33)
                .then()
                    .output("sum", -179)
                .verify();
    }
}
