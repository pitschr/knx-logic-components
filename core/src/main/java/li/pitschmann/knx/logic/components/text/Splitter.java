package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;
import java.util.StringTokenizer;

/**
 * <strong>Logic Splitter</strong><br>
 * Splits <i>text</i> by <i>delimiter</i>.
 * <p>
 * <u>Examples:</u>
 * <ul>
 *  <li>text = {@code A B C}, delimiter = {@code " "}, size of outputs=3 will create {@code [A, B, C]} and <i>leftOver</i> is empty</li>
 *  <li>text = {@code A B C}, delimiter = {@code " "}, size of outputs=5 will create {@code [A, B, C, "", ""]} and <i>leftOver</i> is empty</li>
 *  <li>text = {@code A B C D E}, delimiter = {@code " "}, size of outputs=3 will create {@code [A, B, C]} and <i>leftOver</i> is {@code "D E"}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Splitter implements Logic {
    @Input
    private String text;

    @Input
    private String delimiter = " ";

    @Output
    private List<String> outputs;

    @Output
    private String leftOver;

    @Override
    public void logic() {
        final var tokenizer = new StringTokenizer(text, delimiter);

        for (var i = 0; i < outputs.size(); i++) {
            if (tokenizer.hasMoreTokens()) {
                outputs.set(i, tokenizer.nextToken());
            } else {
                outputs.set(i, ""); // set empty string if no more tokens are available
            }
        }

        // append leftOver if there are more split strings than outputs
        if (tokenizer.hasMoreTokens()) {
            final var sb = new StringBuilder(text.length());
            do {
                sb.append(delimiter).append(tokenizer.nextToken());
            } while (tokenizer.hasMoreTokens());
            leftOver = sb.substring(1); // remove first delimiter
        } else {
            leftOver = ""; // no more tokens available
        }
    }
}
