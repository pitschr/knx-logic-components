package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

/**
 * <strong>Increment</strong> Logic<br>
 * Increments the <i>input</i> number by the given <i>step</i>
 * and forward it to <i>output</i>.
 *
 * <ul>
 *     <li>a <i>positive step value</i> then output is <i>incremented</i></li>
 *     <li>a <i>negative step value</i> then output is <i>decremented</i></li>
 *     <li>a <i>zero step value</i> then output is <i>same (=no effect)</i></li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Increment implements Logic {
    @Input
    private int input;

    @Input
    private int steps = 1;

    @Output
    private int output;

    @Override
    public void logic() {
        this.output = this.input + steps;
    }
}
