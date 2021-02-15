package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;

/**
 * <strong>OR/NOR</strong> Logic<br>
 * Output is {@code true} only if at least <u>one</u> input is {@code true}.<br>
 * Negation is the opposite value of <i>output</i>.
 * <ul>
 * <li>If <i>no inputs</i>, <i>output</i> will be {@code false}, <i>outputNegation</i> will be {@code true}</li>
 * <li>If at least one <i>input</i> is {@code true} then <i>output</i> will be {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If all <i>inputs</i> are {@code true} then <i>output</i> will be {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If all <i>inputs</i> are {@code false} then <i>output</i> will be {@code false}, <i>outputNegation</i> will be {@code true}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Or implements Logic {
    @Input(min = 2)
    private List<Boolean> inputs;

    @Output
    private boolean output;

    @Output
    private boolean outputNegation = true;

    @Override
    public void logic() {
        boolean result = false;
        for (final var input : this.inputs) {
            if (Boolean.TRUE.equals(input)) {
                result = true;
                break;
            }
        }

        this.output = result;
        this.outputNegation = !this.output;
    }
}
