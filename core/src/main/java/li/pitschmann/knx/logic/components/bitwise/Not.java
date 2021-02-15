package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

/**
 * <strong>NOT</strong> Logic<br>
 * Simple negation of <i>input</i>.
 * <ul>
 * <li>If <i>input</i> is {@code true}, then <i>output</i> is {@code false}</li>
 * <li>If <i>input</i> is {@code false}, then <i>output</i> is {@code true}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Not implements Logic {
    @Input
    private boolean input = false;

    @Output
    private boolean output = true;

    @Override
    public void logic() {
        this.output = !this.input;
    }
}
