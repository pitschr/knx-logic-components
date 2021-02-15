package li.pitschmann.knx.logic.components.generic;

import li.pitschmann.knx.core.utils.Sleeper;
import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

/**
 * Logic component: DELAY
 * <p>
 * To pass any input signal to another output (one-to-one)
 * with a delay. The delay is defined in milliseconds.
 * <p>
 * Note: the delay is not 100% accurate due underlying
 * operating system and its timers and schedulers.
 * <p>
 * If the delay is zero or a negative value then it is
 * effectively undefined and means "no delay".
 *
 * @author PITSCHR
 */
public final class Delay<T> implements Logic {
    private static final int DEFAULT_DELAY_IN_MILLISECONDS = 1000;   // 1 second

    @Input
    private T input;

    @Input
    private int delayInMilliseconds = DEFAULT_DELAY_IN_MILLISECONDS;

    @Output
    private T output;

    @Override
    public void logic() {
        if (delayInMilliseconds > 0) {
            Sleeper.milliseconds(delayInMilliseconds);
        }
        this.output = this.input;
    }
}
