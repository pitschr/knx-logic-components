package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Joiner} logic component
 */
// @formatter:off
class JoinerTest extends AbstractTest {

    @Test
    @DisplayName("JOINER-Logic: Test default settings")
    void testComponent() {
        assertLogic(Joiner.class)
                .numberOfInputConnectors(6)
                .numberOfOutputConnectors(1)
                .inputs("inputs", "", "") // min occurrence = 2
                .input("delimiter", " ")    // one space
                .input("prefix", "")        // empty
                .input("suffix", "")        // empty
                .input("trim", true)        // leading and tailing spaces will be trimmed
                .input("skipEmpty", true)   // if empty strings should be skipped
                .output("text", "");
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, no prefix, no suffix")
    void testWithDefaultDelimiter() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "Hello", "World")
                    .input("delimiter", " ")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "Hello World")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: delimiter='/', no prefix, no suffix")
    void testWithOtherDelimiter() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "A", "B", "C", "D")
                    .input("delimiter", "/")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "A/B/C/D")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, prefix='Lorem Ipsum', no suffix")
    void testWithPrefixOnly() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "Dolor", "Sit", "Amet")
                    .input("delimiter", " ")
                    .input("prefix", "Lorem Ipsum ")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "Lorem Ipsum Dolor Sit Amet")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, no prefix, suffix='!'")
    void testWithSuffixOnly() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "Hello", "World")
                    .input("delimiter", " ")
                    .input("prefix", "")
                    .input("suffix", "!")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "Hello World!")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, prefix='<', suffix='>'")
    void testWithPrefixAndSuffix() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "A", "B", "C")
                    .input("delimiter", " ")
                    .input("prefix", "<")
                    .input("suffix", ">")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "<A B C>")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, trim=false")
    void testWithTrimDisabled() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "   ", "B", "     ", null)
                    .input("delimiter", "+")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", false)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "   +B+     ")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: default delimiter, trim=true")
    void testWithTrimEnabled() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "   ", "B", "     ", null)
                    .input("delimiter", " ")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "B")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: delimiter=';', skipEmpty=false")
    void testWithSkipEmptyDisabled() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "A", null, "B", "", null, "C")
                    .input("delimiter", ";")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", false)
                .then()
                    .output("text", "A;;B;;;C")
                .verify();
    }

    @Test
    @DisplayName("JOINER-Logic: delimiter=';', skipEmpty=true")
    void testWithSkipEmptyEnabled() {
        LogicScenario
                .given(Joiner.class)
                    .inputs("inputs", "A", null, "B", "", null, "C")
                    .input("delimiter", ";")
                    .input("prefix", "")
                    .input("suffix", "")
                    .input("trim", true)
                    .input("skipEmpty", true)
                .then()
                    .output("text", "A;B;C")
                .verify();
    }
}
