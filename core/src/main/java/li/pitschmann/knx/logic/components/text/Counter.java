package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.regex.Pattern;

/**
 * <strong>Logic Counter</strong><br>
 * Counts the occurrence of <i>pattern</i> for given <i>text</i> by <i>delimiter</i>. It offers the
 * possibility to add <i>prefix</i> and <i>suffix</i>.
 * <p>
 * <u>Examples:</u>
 * <ul>
 *  <li>text = {@code "Aaaabb"}, pattern = {@code ""}, insensitive = {@code false}, regExp = {@code false} returns count = {@code 6}</li>
 *  <li>text = {@code "Aaaabb"}, pattern = {@code "a"}, insensitive = {@code false}, regExp = {@code false} returns count = {@code 3}</li>
 *  <li>text = {@code "Aaaabb"}, pattern = {@code "a"}, insensitive = {@code true}, regExp = {@code false} returns count = {@code 4}</li>
 *  <li>text = {@code "Aaaabb"}, pattern = {@code "[ab]"}, insensitive = {@code false}, regExp = {@code true} returns count = {@code 5}</li>
 *
 * </ul>
 *
 * @author PITSCHR
 */
public final class Counter implements Logic {
    @Input
    private String text;

    /**
     * If pattern is empty then it means that every character should be counted
     */
    @Input
    private String pattern;

    /**
     * Defines if the occurrence should be calculated with case-insensitive in mind.
     * <p>
     * Example: text={@code Aabaa}, pattern={@code 'A'} would return {@code 4}
     * if case-insensitive, and {@code 1} if case-sensitive.
     * </p>
     */
    @Input
    private boolean insensitive;

    /**
     * Defines if the pattern is considered as regular expression.
     * <p>
     * Example: {@code [123]} means all '1', '2' and '3' characters are counted if using regular expression.
     * </p>
     */
    @Input
    private boolean regExp;

    @Output
    private int count;

    @Override
    public void logic() {
        // if empty then simply return 0
        if (text.isEmpty()) {
            count = 0;
        }
        // count all characters if pattern is not defined
        else if (pattern.isEmpty()) {
            count = text.length();
        }
        // start with counting using regular expression or in normal way
        else {
            var tmpText = insensitive ? text.toLowerCase() : text;
            var tmpPattern = insensitive ? pattern.toLowerCase() : pattern;

            var tmpCount = 0;
            // counting using regular expression
            if (regExp) {
                final var matcher = Pattern.compile(tmpPattern).matcher(tmpText);
                while (matcher.find()) {
                    tmpCount++;
                }
            }
            // normal counting
            else {
                var fromIndex = -1;
                for (; ; ) {
                    if ((fromIndex = tmpText.indexOf(tmpPattern, fromIndex + 1)) != -1) {
                        tmpCount++;
                    } else {
                        break;
                    }
                }
            }
            count = tmpCount;
        }
    }
}
