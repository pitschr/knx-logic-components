package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Counter} logic component
 */
// @formatter:off
class CounterTest extends AbstractTest {

    @Test
    @DisplayName("COUNTER-Logic: Test default settings")
    void testComponent() {
        // Logic with
        // * 4x Static Inputs
        // * 1x Static Output
        assertLogic(Counter.class)
                .numberOfInputConnectors(4)
                .numberOfOutputConnectors(1)
                .input("text", "")
                .input("pattern", "")
                .input("insensitive", false)
                .input("regExp", false)
                .output("count", 0);
    }

    @Test
    @DisplayName("COUNTER-Logic: empty text")
    void testEmptyText() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "")
                    .input("pattern", "")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("count", 0)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count all characters")
    void testCountAllCharacters() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "Hello World")
                    .input("pattern", "")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("count", 11)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count with case-sensitive")
    void testCountCaseSensitivity() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "ABABABABabab")
                    .input("pattern", "AB")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("count", 4)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count with case-insensitive")
    void testCountCaseInsensitivity() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "ABABABABabab")
                    .input("pattern", "AB")
                    .input("insensitive", true)
                    .input("regExp", false)
                .then()
                    .output("count", 6)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count with regular expression, match for 'a', '3', '4', '5' and '6'")
    void testCountRegularExpression() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "ABCabc01234567")
                    .input("pattern", "[a3-6]")
                    .input("insensitive", false)
                    .input("regExp", true)
                    // ABCabc01234567
                    // ...^.....^^^^.
                .then()
                    .output("count", 5)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count with regular expression, match for digits")
    void testCountRegularExpressionDigits() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "ABCabc01234567")
                    .input("pattern", "\\d")
                    .input("insensitive", false)
                    .input("regExp", true)
                    // ABCabc01234567
                    // ......^^^^^^^^
                .then()
                    .output("count", 8)
                .verify();
    }

    @Test
    @DisplayName("COUNTER-Logic: count with regular expression, no match")
    void testCountRegularExpressionNoMatch() {
        LogicScenario
                .given(Counter.class)
                    .input("text", "ABCabc")
                    .input("pattern", "x")
                    .input("insensitive", false)
                    .input("regExp", true)
                    // ABCabc
                    // ......
                .then()
                    .output("count", 0)
                .verify();
    }

}
