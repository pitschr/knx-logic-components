package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.regex.Pattern;

/**
 * <strong>Logic Replacer</strong><br>
 * Replaces each substring of {@code text} by that matches the {@code pattern}
 * with the given {@code replacement}
 * <p>
 * <u>Examples:</u>
 * <ul>
 *  <li>text = {@code Hello}, pattern = {@code "H"}, replacement = {@code "B"} will return output = {@code "Bello"}</li>
 *  <li>text = {@code Hello}, pattern = {@code "h"}, replacement = {@code "B"}, insensitive = {@code true} will return output = {@code "Bello"}</li>
 *  <li>text = {@code Woooorld}, pattern = {@code "o+"}, replacement = {@code "o"}, regExp = {@code true} will return output = {@code "World"}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Replacer implements Logic {
    @Input
    private String text;

    /**
     * If pattern is empty then it means that no character should be replaced
     */
    @Input
    private String pattern;

    /**
     * The replacement text that is used to replace the matching patterns
     */
    @Input
    private String replacement;

    /**
     * Defines if the pattern is considered as case-insensitive
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
    private String outputText;

    @Override
    public void logic() {
        // ensure that output is empty if text is empty
        if (text.isEmpty()) {
            outputText = "";
        }
        // output is same as text if pattern is empty
        else if (pattern.isEmpty()) {
            outputText = text;
        }
        // else do replacement
        else {
            final var patternTmp = regExp ? pattern : Pattern.quote(pattern);
            final var magicFlags = insensitive ? Pattern.CASE_INSENSITIVE : 0;

            outputText = Pattern.compile(patternTmp, magicFlags).matcher(text).replaceAll(replacement);
        }
    }
}
