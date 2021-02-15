package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Replacer} logic component
 */
// @formatter:off
class ReplacerTest extends AbstractTest {

    @Test
    @DisplayName("REPLACER-Logic: Test default settings")
    void testComponent() {
        assertLogic(Replacer.class)
                .numberOfInputConnectors(5)
                .numberOfOutputConnectors(1)
                .input("text", "")
                .input("pattern", "")
                .input("replacement", "")
                .input("insensitive", false)
                .input("regExp", false)
                .output("outputText", "");
    }

    @Test
    @DisplayName("REPLACER-Logic: empty input text")
    void replaceEmptyInputText() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "")
                    .input("pattern", "")
                    .input("replacement", "")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("outputText", "")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: empty pattern (=no replacement)")
    void replaceEmptyPattern() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "Hello World")
                    .input("pattern", "")
                    .input("replacement", "")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("outputText", "Hello World")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: empty replacement (=character removal)")
    void replaceEmptyReplacement() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "foobar")
                    .input("pattern", "ob")
                    .input("replacement", "")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("outputText", "foar")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: normal replacement (single char)")
    void replaceSingleChar() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "Lorem Mipsum")
                    .input("pattern", "m")
                    .input("replacement", "X")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("outputText", "LoreX MipsuX")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: normal replacement (multiple chars)")
    void replaceMultiChars() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "Hello World!")
                    .input("pattern", "World")
                    .input("replacement", "Earth")
                    .input("insensitive", false)
                    .input("regExp", false)
                .then()
                    .output("outputText", "Hello Earth!")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: normal replacement, case-insensitive")
    void replaceCaseInsensitive() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "XaxbXC")
                    .input("pattern", "x")
                    .input("replacement", "*")
                    .input("insensitive", true)
                    .input("regExp", false)
                .then()
                    .output("outputText", "*a*b*C")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: regular-expression replacement (simple)")
    void replaceRegExp() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "abc0123def")
                    .input("pattern", "\\d+")
                    .input("replacement", "*")
                    .input("insensitive", false)
                    .input("regExp", true)
                .then()
                    // abc0123def
                    // abc*def
                    .output("outputText", "abc*def")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: regular-expression replacement, case-insensitive (simple)")
    void replaceRegExpCaseInsensitive() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "Hello 123 ABC abc 456 World")
                    .input("pattern", "[a-z ]")
                    .input("replacement", "*")
                    .input("insensitive", true)
                    .input("regExp", true)
                .then()
                    // Hello 123 ABC abc 456 World
                    // ******123*********456******
                    .output("outputText", "******123*********456******")
                .verify();
    }

    @Test
    @DisplayName("REPLACER-Logic: regular-expression replacement with groups (complex)")
    void replaceRegExpComplex() {
        LogicScenario
                .given(Replacer.class)
                    .input("text", "Lorem Ipsum Dolor Sit Amet")
                    .input("pattern", "^.*(Ipsum [^ ]+).*Sit (.*)$")
                    .input("replacement", "Text: '$1' and '$2'")
                    .input("insensitive", false)
                    .input("regExp", true)
                .then()
                    .output("outputText", "Text: 'Ipsum Dolor' and 'Amet'")
                .verify();
    }
}
