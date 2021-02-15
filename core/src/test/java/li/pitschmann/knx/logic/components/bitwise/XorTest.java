package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.core.utils.BitGenerator;
import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Xor} logic component
 */
// @formatter:off
final class XorTest extends AbstractTest {

    @Test
    @DisplayName("XOR-Logic: Initialization state")
    void testInitialization() {
        assertLogic(Xor.class)
                .numberOfInputConnectors(1)
                .numberOfOutputConnectors(2)
                .numberOfInputPins(2) // min occurrence = 2
                .numberOfOutputPins(2)
                .inputs("inputs", false, false)     // Dynamic Inputs
                .output("output", false)            // Static Output: XOR
                .output("outputNegation", true);    // Static Output: XNOR
    }

    @Test
    @DisplayName("XOR-Logic: Test with 'true' only signals")
    void testAllTrue() {
        // 2 Inputs
        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, true)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();

        // 3 Inputs
        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, true, true)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                .verify();

        // 4 Inputs
        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, true, true, true)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();
    }

    @Test
    @DisplayName("XOR-Logic: Test with 'true' and 'false' signals. Output is 'true'")
    void testTrueAndFalseAndResultIsTrue() {
        // Output: true
        // Output Negation: false
        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, false)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                  .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", false, true)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", false, false, true)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", false, true, false)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, false, false)
                .then()
                    .output("output", true)
                    .output("outputNegation", false)
                .verify();
    }

    @Test
    @DisplayName("XOR-Logic: Test with 'true' and 'false' signals. Output is 'false'")
    void testTrueAndFalseAndResultIsFalse() {
        // Output: false
        // Output Negation: true
        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, true)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", false, false)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", false, true, true)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, true, false)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();

        LogicScenario
                .given(Xor.class)
                    .inputs("inputs", true, false, true)
                .then()
                    .output("output", false)
                    .output("outputNegation", true)
                .verify();
    }

    @Test
    @DisplayName("XOR-Logic: Test with 'false' only signals")
    void testAllFalse() {
        for (int xBit = 2; xBit < 5; xBit++) {
            LogicScenario
                    .given(Xor.class)
                        .inputs("inputs", BitGenerator.falseOnly(xBit))
                    .then()
                        .output("output", false)
                        .output("outputNegation", true)
                    .verify();
        }
    }
}
