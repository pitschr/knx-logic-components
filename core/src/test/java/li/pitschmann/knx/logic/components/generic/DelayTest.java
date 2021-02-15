package li.pitschmann.knx.logic.components.generic;

import li.pitschmann.knx.core.utils.Stopwatch;
import li.pitschmann.knx.logic.test.AbstractTest;
import li.pitschmann.knx.logic.test.logic.LogicScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the {@link Delay} logic component
 */
// @formatter:off
class DelayTest extends AbstractTest {

    @Test
    @DisplayName("DELAY-Logic: Initialization state")
    void testInitialization() {
        // Logic with
        // * 2x Static Input
        // * 1x Static Output
        assertLogic(Delay.class)
                .numberOfInputConnectors(2)
                .numberOfOutputConnectors(1)
                .input("input", null)
                .input("delayInMilliseconds", 1000)
                .output("output", null);
    }

    @Test
    @DisplayName("DELAY-Logic: 3-seconds delay")
    void testDelay() {
        final var sw = Stopwatch.createStarted();

        LogicScenario
                .given(Delay.class)
                    .input("input", 4711)
                    .input("delayInMilliseconds", 3000)
                .then()
                    .output("output", 4711)
                .verify();

        assertThat(sw.elapsed()).isGreaterThan(Duration.ofMillis(3000));
    }

    @Test
    @DisplayName("DELAY-Logic: No Delay")
    void testNoDelay() {
        final var sw = Stopwatch.createStarted();

        LogicScenario
                .given(Delay.class)
                    .input("input", "foobar")
                    .input("delayInMilliseconds", 0)
                .then()
                    .output("output", "foobar")
                .verify();

        assertThat(sw.elapsed()).isLessThan(Duration.ofMillis(1000));
    }

    @Test
    @DisplayName("DELAY-Logic: No Delay because of negative value")
    void testNegativeDelay() {
        final var sw = Stopwatch.createStarted();

        final var obj = new Object();
        LogicScenario
                .given(Delay.class)
                    .input("input", obj)
                    .input("delayInMilliseconds", -19)
                .then()
                    .output("output", obj)
                .verify();

        assertThat(sw.elapsed()).isLessThan(Duration.ofMillis(1000));
    }
}
