package li.pitschmann.knx.logic.components.generic;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;
import java.util.Objects;

/**
 * <strong>EQUAL/NEQUAL</strong> Logic<br>
 * <i>Output</i> is {@code true} only if all <i>inputs</i> have the same value.
 * Negotiation is the opposite value of <i>output</i>.
 * <ul>
 * <li>If <i>no inputs</i>, then <i>output</i> is {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If all <i>inputs</i> are {@code true} then <i>output</i> will be {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If all <i>inputs</i> are {@code false} then <i>output</i> will be {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If there are mixed <i>inputs</i> ({@code true} and {@code false}) then <i>output</i> will be {@code false}, <i>outputNegation</i> will be {@code true}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Equal<T> implements Logic {
    @Input(min = 2)
    private List<T> inputs;

    @Output
    private boolean output = true; // true because all inputs are "null"

    @Output
    private boolean outputNegation = false;

    @Override
    public void logic() {
        boolean equals = true;
        if (!this.inputs.isEmpty()) {
            final var firstInput = this.inputs.get(0);
            for (final var input : this.inputs) {
                if (!Objects.equals(firstInput, input)) {
                    equals = false;
                    break;
                }
            }
        }

        this.output = equals;
        this.outputNegation = !this.output;
    }
}
