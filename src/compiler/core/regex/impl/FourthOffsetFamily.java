package compiler.core.regex.impl;

import static compiler.core.regex.RegexConstants.NAME;
import static compiler.core.regex.RegexConstants.BASIC_OPERATION_TYPE;

import compiler.core.regex.RegexConstants;
import compiler.core.regex.RegexFamilyInterface;
import compiler.core.regex.RegexUtility;

import java.util.regex.Pattern;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class FourthOffsetFamily implements RegexFamilyInterface {

    /**
     * Represents all the {@link Pattern patterns} associated with the fourth offset family.
     * This family starts from the opcode 56 to the 83 (both inclusive)
     */
    private static final Pattern[] INSTRUCTION_FORMAT  = new Pattern[] {
            Pattern.compile(NAME + " R\\d+,\\[\\d+\\]"), // OP R5, [20]
            Pattern.compile(NAME + " \\[R\\d+\\],\\[\\d+\\]"), // OP [R5], [48]
            Pattern.compile(NAME + " \\[\\d+\\],R\\d+"), // OP [5], R2
            Pattern.compile(NAME + " \\[\\d+\\],\\[R\\d+\\]"), // OP [25], [R5]
    };

    @Override
    public int calculate(final String line, final String operationName) {
        return RegexUtility.calculateOpcode(line, operationName, INSTRUCTION_FORMAT, RegexConstants.BASIC_OPERATION_TYPE, 56);

    }
}
