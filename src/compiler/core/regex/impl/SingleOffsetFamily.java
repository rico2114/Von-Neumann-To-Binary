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
public class SingleOffsetFamily implements RegexFamilyInterface {

    /**
     * Represents all the {@link Pattern patterns} associated with the single offset family.
     * This family starts from the opcode 84 to the 90 (both inclusive)
     */
    private static final Pattern[] INSTRUCTION_FORMAT  = new Pattern[] {
            Pattern.compile(NAME + " \\[" + RegexConstants.REGISTER_CHAIN + "\\+" + RegexConstants.IMMEDIATE_CHAIN + "\\],\\[" + RegexConstants.REGISTER_CHAIN  +"\\]"), // OP [R5 + 2], [R4]
    };

    /**
     * Represents all the {@link Pattern patterns} associated with the special operation type family.
     * This family uses a different order for their operations.
     */
    private static final Pattern[] OPERATION_TYPE = new Pattern[] {
            Pattern.compile("MOV"),
            Pattern.compile("ADD"),
            Pattern.compile("SUB"),
            Pattern.compile("MUL"),
            Pattern.compile("DIV"),
            Pattern.compile("AND"),
            Pattern.compile("OR"),
    };

    @Override
    public int calculate(final String line, final String operationName) {
        return RegexUtility.calculateOpcode(line, operationName, INSTRUCTION_FORMAT, OPERATION_TYPE, 84);
    }
}
