package li.pitschmann.knx.logic.components.bitwise;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;

/**
 * <strong>XOR/XNOR</strong> Logic<br>
 * Output is {@code true} only if there is an <u>odd</u> number of input with {@code true}.<br>
 * Negation is the opposite value of <i>output</i>.
 * <ul>
 * <li>If <i>no inputs</i>, <i>output</i> will be {@code false}, <i>outputNegation</i> will be {@code true}</li>
 * <li>If number of <i>inputs</i> with {@code true} is <u>odd</u>, then <i>output</i> will be {@code true}, <i>outputNegation</i> will be {@code false}</li>
 * <li>If number of <i>inputs</i> with {@code true} is <u>even</u>, then <i>output</i> will be {@code false}, <i>outputNegation</i> will be {@code true}</li>
 * </ul>
 *
 * <u>Examples (XOR):</u><br>
 * <table>
 * <td style="padding:10px">
 * {@code 00 = 0}<br>{@code 01 = 1}<br>{@code 10 = 1}<br>{@code 11 = 0}
 * </td>
 * <td style="padding:10px">
 * {@code 000 = 0}<br>{@code 001 = 1}<br>{@code 010 = 1}<br>
 * {@code 011 = 0}<br>{@code 100 = 1}<br>{@code 101 = 0}<br>
 * {@code 110 = 0}<br>{@code 111 = 1}
 * </td>
 * <td style="padding:10px">
 * {@code 0000 = 0}<br>{@code 0001 = 1}<br>{@code 0010 = 1}<br>{@code 0011 = 0}<br>
 * {@code 0100 = 1}<br>{@code 0101 = 0}<br>{@code 0110 = 0}<br>{@code 0111 = 1}<br>
 * {@code 1000 = 1}<br>{@code 1001 = 0}<br>{@code 1010 = 0}<br>{@code 1011 = 1}<br>
 * {@code 1100 = 0}<br>{@code 1101 = 1}<br>{@code 1110 = 1}<br>{@code 1111 = 0}<br>
 * </td>
 * </table>
 *
 * @author PITSCHR
 */
public final class Xor implements Logic {
    @Input(min = 2)
    private List<Boolean> inputs;

    @Output
    private boolean output;

    @Output
    private boolean outputNegation = true;

    @Override
    public void logic() {
        var countTrue = 0;
        for (final var input : this.inputs) {
            if (Boolean.TRUE.equals(input)) {
                countTrue++;
            }
        }

        this.output = countTrue % 2 == 1;
        this.outputNegation = !this.output;
    }
}
