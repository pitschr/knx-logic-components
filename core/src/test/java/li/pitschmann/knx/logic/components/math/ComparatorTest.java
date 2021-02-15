package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Comparator} logic component
 */
// @formatter:off
class ComparatorTest extends AbstractTest {

    @Test
    @DisplayName("COMPARATOR-Logic: Initialization state")
    void testInitialization() {
        // Logic with
        // * 2x Static Input
        // * 3x Static Output
        assertLogic(Comparator.class)
                .numberOfInputConnectors(2)
                .numberOfOutputConnectors(3)
                .input("x", 0)
                .input("y", 0)
                .output("equals", false)
                .output("greater", false)
                .output("less", false);
    }

    @Test
    @DisplayName("COMPARATOR-Logic: x == y")
    void testEquals() {
        // -33 == -33
        LogicScenario
                .given(Comparator.class)
                    .input("x", -33L)
                    .input("y", -33L)
                .then()
                    .output("equals", true)
                    .output("greater", false)
                    .output("less", false)
                .verify();

        // 0 == 0
        LogicScenario
                .given(Comparator.class)
                    .input("x", 0L)
                    .input("y", 0L)
                .then()
                    .output("equals", true)
                    .output("greater", false)
                    .output("less", false)
                .verify();

        // 4711 == 4711
        LogicScenario
                 .given(Comparator.class)
                    .input("x", 4711L)
                    .input("y", 4711L)
                .then()
                    .output("equals", true)
                    .output("greater", false)
                    .output("less", false)
                .verify();
    }

    @Test
    @DisplayName("COMPARATOR-Logic: x > y")
    void testGreaterThan() {
        // -13 > -19
        LogicScenario
                .given(Comparator.class)
                    .input("x", -13L)
                    .input("y", -19L)
                .then()
                    .output("equals", false)
                    .output("greater", true)
                    .output("less", false)
                .verify();

        // 7 > -3
        LogicScenario
                .given(Comparator.class)
                    .input("x", 7L)
                    .input("y", -3L)
                .then()
                    .output("equals", false)
                    .output("greater", true)
                    .output("less", false)
                .verify();

        // 23 > 7
        LogicScenario
                 .given(Comparator.class)
                    .input("x", 23L)
                    .input("y", 7L)
                .then()
                    .output("equals", false)
                    .output("greater", true)
                    .output("less", false)
                .verify();
    }

    @Test
    @DisplayName("COMPARATOR-Logic: x < y")
    void testLessThan() {
        // -31 < -11
        LogicScenario
                .given(Comparator.class)
                    .input("x", -31L)
                    .input("y", -11L)
                .then()
                    .output("equals", false)
                    .output("greater", false)
                    .output("less", true)
                .verify();

        // -1 < 3
        LogicScenario
                 .given(Comparator.class)
                    .input("x", -1L)
                    .input("y", 3L)
                .then()
                    .output("equals", false)
                    .output("greater", false)
                    .output("less", true)
                .verify();

         // 21 < 37
        LogicScenario
                 .given(Comparator.class)
                    .input("x", 21L)
                    .input("y", 37L)
                .then()
                    .output("equals", false)
                    .output("greater", false)
                    .output("less", true)
                .verify();
    }

    @Test
    @DisplayName("COMPARATOR-Logic: Compare Double with Int")
    void testCompareDoubleAndInt() {
        // equals
        LogicScenario
                .given(Comparator.class)
                    .input("x", 4733.0d)
                    .input("y", 4733)
                .then()
                    .output("equals", true)
                    .output("greater", false)
                    .output("less", false)
                .verify();

        // greater than
        LogicScenario
                .given(Comparator.class)
                    .input("x", 92844.003d)
                    .input("y", 92844)
                .then()
                    .output("equals", false)
                    .output("greater", true)
                    .output("less", false)
                .verify();

        // less than
        LogicScenario
                .given(Comparator.class)
                    .input("x", 7832)
                    .input("y", 7832.001d)
                .then()
                    .output("equals", false)
                    .output("greater", false)
                    .output("less", true)
                .verify();
    }


    @Test
    @DisplayName("COMPARATOR-Logic: Compare Float with Long")
    void testCompareFloatAndLong() {
        // equals
        LogicScenario
                .given(Comparator.class)
                    .input("x", 112L)
                    .input("y", 112.0f)
                .then()
                    .output("equals", true)
                    .output("greater", false)
                    .output("less", false)
                .verify();

        // greater than
        LogicScenario
                .given(Comparator.class)
                    .input("x", 877.003f)
                    .input("y", 877)
                .then()
                    .output("equals", false)
                    .output("greater", true)
                    .output("less", false)
                .verify();

        // less than
        LogicScenario
                .given(Comparator.class)
                    .input("x", 232)
                    .input("y", 232.001f)
                .then()
                    .output("equals", false)
                    .output("greater", false)
                    .output("less", true)
                .verify();
    }
}
