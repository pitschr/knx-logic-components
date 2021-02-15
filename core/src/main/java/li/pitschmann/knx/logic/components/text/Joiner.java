package li.pitschmann.knx.logic.components.text;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.annotations.Input;
import li.pitschmann.knx.logic.annotations.Output;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <strong>Logic Joiner</strong><br>
 * Joins a list of text (<i>texts</i>) and adds <i>delimiter</i> string.
 * It offers the possibility to add <i>prefix</i> and <i>suffix</i>.
 * <p>
 * <u>Examples:</u>
 * <ul>
 *  <li>inputs = {@code [A, B, C]}, delimiter = {@code " "}, prefix = {@code ""}, suffix = {@code ""}, {@code trim=true} will create {@code "A B C"}</li>
 *  <li>inputs = {@code [A, B, C]}, delimiter = {@code ""}, prefix = {@code ""}, suffix = {@code ""}, {@code trim=true} will create {@code "ABC"}</li>
 *  <li>inputs = {@code [A, B, C]}, delimiter = {@code "+"}, prefix = {@code "<"}, suffix = {@code ">"}, {@code trim=true} will create {@code "<A+B+C>"}</li>
 *  <li>inputs = {@code [null, B, null]}, delimiter = {@code " "}, prefix = {@code ""}, suffix = {@code ""}, {@code trim=false} will create {@code " B "}</li>
 *  <li>inputs = {@code [null, B, null]}, delimiter = {@code " "}, prefix = {@code ""}, suffix = {@code ""}, {@code trim=true} will create {@code "B"}</li>
 * </ul>
 *
 * @author PITSCHR
 */
public final class Joiner implements Logic {
    @Input(min = 2)
    private List<String> inputs;

    @Input
    private String delimiter = " ";

    @Input
    private String prefix;

    @Input
    private String suffix;

    @Input
    private boolean trim = true;

    @Input
    private boolean skipEmpty = true;

    @Output
    private String text;

    @Override
    public void logic() {
        var joinedTmp = inputs
                .stream()
                .map(s -> Objects.requireNonNullElse(s, ""))
                .filter(s -> !skipEmpty || !s.isEmpty())
                .collect(Collectors.joining(delimiter, prefix, suffix));

        text = trim ? joinedTmp.trim() : joinedTmp;
    }
}
