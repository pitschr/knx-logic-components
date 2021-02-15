package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;

/**
 * <strong>AND/NAND</strong> Logic<br>
 * Output is {@code true} only if all inputs are {@code true}.<br>
 * Negation is the opposite value of <i>output</i>.
 * <ul>
 * <li>If <i>no inputs</i>, <i>output</i> will be {@code false}, <i>negation</i> will be {@code true}</li>
 * <li>If all <i>inputs</i> are {@code true} then <i>output</i> will be {@code true}, <i>negation</i> will be {@code false}</li>
 * <li>Otherwise <i>output</i> will be {@code false} and <i>negation</i> will be {@code true}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class And implements Logic {
    @Input(min = 2)
    private List<Boolean> inputs;

    @Output
    private boolean output;

    @Output
    private boolean outputNegation = true;

    @Override
    public void logic() {
        boolean result = false;
        if (!this.inputs.isEmpty()) {
            result = true;
            for (final var input : this.inputs) {
                if (!Boolean.TRUE.equals(input)) {
                    result = false;
                    break;
                }
            }
        }

        this.output = result;
        this.outputNegation = !this.output;
    }

}
