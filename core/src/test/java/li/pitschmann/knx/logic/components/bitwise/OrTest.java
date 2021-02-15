package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.core.utils.BitGenerator;
import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Or} logic component
 */
// @formatter:off
final class OrTest extends AbstractTest {

    @Test
    @DisplayName("OR-Logic: Initialization state")
    void testInitialization() {
        assertLogic(Or.class)
                .numberOfInputConnectors(1)
                .numberOfOutputConnectors(2)
                .numberOfInputPins(2) // min occurrence = 2
                .numberOfOutputPins(2)
                .inputs("inputs", false, false)     // Dynamic Inputs
                .output("output", false)            // Static Output: OR
                .output("outputNegation", true);    // Static Output: NOR
    }

    @Test
    @DisplayName("OR-Logic: Test with 'true' only signals")
    void testAllTrue() {
        for (int xBit = 2; xBit < 5; xBit++) {
            LogicScenario
                    .given(Or.class)
                        .inputs("inputs", BitGenerator.trueOnly(xBit))
                    .then()
                        .output("output", true)
                        .output("outputNegation", false)
                    .verify();
        }
    }

    @Test
    @DisplayName("OR-Logic: Test with 'true' and 'false' signals")
    void testTrueAndFalse() {
        for (int xBit = 2; xBit < 5; xBit++) {
            // all combinations of false/trues for given bit
            for (final var testDataBit : BitGenerator.falseAndTrueOnly(xBit)) {
                LogicScenario
                        .given(Or.class)
                            .inputs("inputs", testDataBit)
                        .then()
                            .output("output", true)
                            .output("outputNegation", false)
                        .verify();
            }
        }
    }

    @Test
    @DisplayName("OR-Logic: Test with 'false' only signals")
    void testAllFalse() {
        for (int xBit = 2; xBit < 5; xBit++) {
            LogicScenario
                    .given(Or.class)
                        .inputs("inputs", BitGenerator.falseOnly(xBit))
                    .then()
                        .output("output", false)
                        .output("outputNegation", true)
                    .verify();
        }
    }
}
