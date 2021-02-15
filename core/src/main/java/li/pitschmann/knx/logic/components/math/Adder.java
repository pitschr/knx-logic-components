package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;

/**
 * <strong>Logic Adder</strong><br>
 * Calculates all number from <i>values</i> input field and
 * output the summary to <i>sum</i> output field.
 *
 * <ul>
 *  <li>If <i>no inputs</i>, <i>sum</i> will be {@code 0}</li>
 *  <li>If <i>inputs</i> are {@code [0, 1, 1]} then <i>sum</i> will be {@code 2}</li>
 *  <li>If <i>inputs</i> are {@code [1, 2, 5, 11]} then <i>sum</i> will be {@code 19}</li>
 *  <li>If <i>inputs</i> are {@code [-5, 7, -3, 11]} then <i>sum</i> will be {@code 10}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Adder implements Logic {
    @Input(min = 2)
    private List<Integer> inputs;

    @Output
    private int sum;

    @Override
    public void logic() {
        var l = 0;
        for (final var input : inputs) {
            l += input;
        }
        this.sum = l;
    }
}
