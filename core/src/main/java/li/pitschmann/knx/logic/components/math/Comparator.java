package li.pitschmann.knx.logic.components.math;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

/**
 * <strong>Logic Comparator</strong><br>
 * Compares two number values (x and y) and returns:
 * <ul>
 *     <li>{@code equals=true} if {@code x} and {@code y} equals</li>
 *     <li>{@code greater=true} if {@code x} is greater than {@code y}</li>
 *     <li>{@code less=true} if {@code x} is less than {@code y}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Comparator implements Logic {
    @Input
    private Number x;

    @Input
    private Number y;

    @Output
    private boolean greater;

    @Output
    private boolean equals;

    @Output
    private boolean less;

    @Override
    public void logic() {
        final int value;
        if (x instanceof Double || y instanceof Double) {
            value = Double.compare(x.doubleValue(), y.doubleValue());
        } else if (x instanceof Float || y instanceof Float) {
            value = Float.compare(x.floatValue(), y.floatValue());
        } else {
            value = Long.compare(x.longValue(), y.longValue());
        }

        equals = value == 0;
        greater = value > 0;
        less = value < 0;
    }
}
