package compiler.core.regex;

import java.util.regex.Pattern;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class RegexConstants {

    /**
     * Covers every possible Von Neumann instruction name
     */
    public static final String NAME = "[a-zA-Z]{2,4}";

    /**
     * Represents the chain used for the register validation
     */
    public static final String REGISTER_CHAIN = "R\\d{1,2}";

    /**
     * Represents the chain used for the immediate validation
     */
    public static final String IMMEDIATE_CHAIN = "\\d{1,5}";

    /**
     * Represents all the default basic operations
     */
    public static final Pattern[] BASIC_OPERATION_TYPE = new Pattern[] {
            Pattern.compile("ADD"),
            Pattern.compile("SUB"),
            Pattern.compile("AND"),
            Pattern.compile("OR"),
            Pattern.compile("MUL"),
            Pattern.compile("DIV"),
            Pattern.compile("MOV"),
    };

}
