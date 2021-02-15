package li.pitschmann.knx.logic.components.generic;

import li.pitschmann.knx.core.utils.BitGenerator;
import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Equal} logic component
 */
// @formatter:off
final class EqualTest extends AbstractTest {

    @Test
    @DisplayName("EQUAL-Logic: Test Component")
    void testComponent() {
        // Logic with
        // * 1x Dynamic Input
        // * 2x Static Outputs (EQUAL, NEQUAL)
        assertLogic(Equal.class)
                .numberOfInputConnectors(1)
                .numberOfOutputConnectors(2)
                .numberOfInputPins(2) // min occurrence = 2
                .numberOfOutputPins(2)
                // Dynamic Inputs
                .inputs("inputs", null, null)
                // Static Output: EQUAL
                .output("output", true)
                // Static Output: NEQUAL
                .output("outputNegation", false);

    }

    @Test
    @DisplayName("EQUAL-Logic: Test with 'true' only signals")
    void testAllTrue() {
        for (int xBit = 2; xBit < 5; xBit++) {
            LogicScenario
                    .given(Equal.class)
                        .inputs("inputs", BitGenerator.trueOnly(xBit))
                    .then()
                        .output("output",true)
                        .output("outputNegation",false)
                    .verify();
        }
    }

    @Test
    @DisplayName("EQUAL-Logic: Test with 'true' and 'false' signals")
    void testTrueAndFalse() {
        for (int xBit = 2; xBit < 5; xBit++) {
            // all combinations of false/trues for given bit
            for (final var testDataBit : BitGenerator.falseAndTrueOnly(xBit)) {
                LogicScenario
                        .given(Equal.class)
                            .inputs("inputs",testDataBit)
                        .then()
                            .output("output",false)
                            .output("outputNegation",true)
                        .verify();
            }
        }
    }

    @Test
    @DisplayName("EQUAL-Logic: Test with 'false' only signals")
    void testAllFalse() {
        for (int xBit = 2; xBit < 5; xBit++) {
            LogicScenario
                    .given(Equal.class)
                        .inputs("inputs",BitGenerator.falseOnly(xBit))
                    .then()
                        .output("output",true)
                        .output("outputNegation",false)
                    .verify();
        }
    }
}
