//@@author nhatquang3112
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TODO;

import org.junit.Test;

import seedu.address.logic.commands.CheckToDoCommand;

/**
 * As we are only doing white-node testing, our test cases do not cover path variations
 * outside of the CheckToDoCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the CheckToDoCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CheckToDoCommandParserTest {

    private CheckToDoCommandParser parser = new CheckToDoCommandParser();

    @Test
    public void parse_validArgs_returnsCheckToDoCommand() {
        assertParseSuccess(parser, "1", new CheckToDoCommand(INDEX_FIRST_TODO));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckToDoCommand.MESSAGE_USAGE));
    }
}
