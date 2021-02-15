package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.components.LogicComponentImpl;
import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Splitter} logic component
 */
// @formatter:off
class SplitterTest extends AbstractTest {

    @Test
    @DisplayName("SPLITTER-Logic: Test default settings")
    void testComponent() {
        assertLogic(Splitter.class)
                .numberOfInputConnectors(2)
                .numberOfOutputConnectors(2)
                .input("text", "")
                .input("delimiter", " ")      // one space
                .outputs("outputs", "")     // min occurrence = 2
                .output("leftOver", "");      // empty
    }

    @Test
    @DisplayName("SPLITTER-Logic: default delimiter, no leftOver")
    void testWithDefaultDelimiter() {
        LogicScenario
                .given(Splitter.class)
                    .input("text", "A B C")
                    .input("delimiter", " ")
                .then()
                    .outputs("outputs", "A", "B", "C")
                    .output("leftOver", "")
                .verify();
    }

    @Test
    @DisplayName("SPLITTER-Logic: delimiter=';' and leftOver='D E F'")
    void testWithSemicolonDelimiterAndLeftOver() {
        LogicScenario
                .given(Splitter.class)
                    .input("text", "A;B;C;D;E;F")
                    .input("delimiter", ";")
                .then()
                    .outputs("outputs", "A", "B", "C")
                    .output("leftOver", "D;E;F")
                .verify();
    }

    @Test
    @DisplayName("SPLITTER-Logic: default delimiter, no leftOver, pin size larger than split string")
    void testWithDefaultDelimiterAndLessSplitStrings() {
        final var splitter = new LogicComponentImpl(new Splitter());
        final var dynamicConnector = dynamicConnector(splitter, "outputs");
        // try to increase output pin size to 5
        dynamicConnector.tryIncrease(5);
        // fill all pins with "dummy" -> it should be overwritten by
        // the concrete value if available, otherwise it should be empty
        dynamicConnector.getPinStream().forEach(pin -> pin.setValue("dummy"));

        LogicScenario
                .given(splitter)
                    .input("text", "A B C")
                    .input("delimiter", " ")
                .then()
                    .outputs("outputs", "A", "B", "C", "", "")
                    .output("leftOver", "")
                .verify();
    }
}
