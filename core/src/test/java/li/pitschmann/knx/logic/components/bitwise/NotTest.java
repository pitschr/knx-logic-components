package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Not} logic component
 */
// @formatter:off
final class NotTest extends AbstractTest {

    @Test
    @DisplayName("NOT-Logic: Initialization state")
    void testInitialization() {
        assertLogic(Not.class)
                .numberOfInputConnectors(1)
                .numberOfOutputConnectors(1)
                .numberOfInputPins(1)
                .numberOfOutputPins(1)
                .input("input", false)      // Static Input
                .output("output", true);    // Static Output: NOT
    }

    @Test
    @DisplayName("NOT-Logic: Test with 'true' signal")
    void testTrue() {
        LogicScenario
                .given(Not.class)
                    .input("input", true)
                .then()
                    .output("output", false)
                .verify();
    }

    @Test
    @DisplayName("NOT-Logic: Test with 'false' signal")
    void testFalse() {
        LogicScenario
                .given(Not.class)
                    .input("input", false)
                .then()
                    .output("output", true)
                .verify();
    }

}
