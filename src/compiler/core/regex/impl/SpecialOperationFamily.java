package compiler.core.regex.impl;

import compiler.core.regex.RegexFamilyInterface;

import java.util.regex.Pattern;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class SpecialOperationFamily implements RegexFamilyInterface {

    /**
     * Represents all the {@link Pattern patterns} associated with the special operation type family.
     * This family starts from the opcode 52 to the 55 (both inclusive)
     */
    private static final Pattern[] OPERATION_TYPE = new Pattern[] {
            Pattern.compile("BRM"),
            Pattern.compile("BRI"),
            Pattern.compile("BRME"),
            Pattern.compile("SHL"),
            Pattern.compile("SHR"),
            Pattern.compile("NOP"),
            Pattern.compile("JUMP"),
    };

    @Override
    public int calculate(final String line, final String operationName) {
        int operationIndex = -1;
        for (int i = 0; i < OPERATION_TYPE.length && operationIndex == -1; i ++) {
            if (OPERATION_TYPE[i].matcher(operationName).matches()) {
                operationIndex = i;
            }
        }
        if (operationIndex == -1) {
            return -1;
        }
        return 49 + operationIndex;
    }
}
