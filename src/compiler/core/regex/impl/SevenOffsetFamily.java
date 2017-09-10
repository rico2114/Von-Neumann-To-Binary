package compiler.core.regex.impl;

import compiler.core.regex.RegexConstants;
import compiler.core.regex.RegexFamilyInterface;
import compiler.core.regex.RegexUtility;

import java.util.regex.Pattern;

import static compiler.core.regex.RegexConstants.NAME;


/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class SevenOffsetFamily implements RegexFamilyInterface {

    /**
     * Represents all the {@link Pattern patterns} associated with the seven offset family.
     * This family starts from the opcode 0 to the 48 (both inclusive)
     */
    private static final Pattern[] INSTRUCTION_FORMAT  = new Pattern[] {
            Pattern.compile(NAME + " R\\d+,R\\d+"), // OP R5, R6
            Pattern.compile(NAME + " R\\d+,\\d+"), // OP R5, 48
            Pattern.compile(NAME + " \\[R\\d+\\],R\\d+"), // OP [R5], R2
            Pattern.compile(NAME + " \\[R\\d+\\+\\d\\],R\\d+"), // OP [R5+4], R4
            Pattern.compile(NAME + " R\\d+,\\[R\\d+\\]"), // OP R5, [R4]
            Pattern.compile(NAME + " R\\d+,\\[R\\d+\\+\\d+\\]"), // OP R5, [R1+48]
            Pattern.compile(NAME + " \\[R\\d+\\],\\[R\\d+\\]"), // OP [R5], [R1]
    };

    @Override
    public int calculate(final String line, final String operationName) {
        return RegexUtility.calculateOpcode(line, operationName, INSTRUCTION_FORMAT, RegexConstants.BASIC_OPERATION_TYPE, 0);
    }
}
