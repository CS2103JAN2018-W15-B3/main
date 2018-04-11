//@@author jas5469
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Finds and groups all persons in Fastis whose name contains any of the argument keywords to a specific group.
 * Keyword matching is case sensitive.
 */
public class AddMembersToGroupCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "addGroupMember";
    public static final String COMMAND_ALIAS = "aGM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds person whose names contain any of "
            + "the specified keywords (case-sensitive) and adds them to group list them.\n"
            + "Parameters: INDEX (must be a positive integer) g/GroupName(Must exist) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUP + "CS1010";

    public static final String MESSAGE_NO_SUCH_GROUP = "No such group exist.";
    public static final String MESSAGE_ADD_PERSON_TO_GROUP_SUCCESS = "%1$s added to group %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Person already in Group";
    public static final String MESSAGE_GROUP_NOT_FOUND = "No such Group in Fastis";
    public static final String MESSAGE_DUPLICATE_GROUP = "Group already in Group";
    public static final String MESSAGE_INVALID_INDEX = "Index is invalid";

    private Index index;
    private Person personToAdd;
    private Group groupToAdd;
    private Group groupAdded;

    public AddMembersToGroupCommand(Index index, Group groupToAdd) {
        requireNonNull(index);
        this.index = index;
        this.groupToAdd = groupToAdd;
    }


    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        List<Group> groupList = model.getFilteredGroupList();

        if (!groupList.contains(groupToAdd)) {
            throw new CommandException(MESSAGE_NO_SUCH_GROUP);
        } else {
            for (Group group : groupList) {
                if (groupToAdd.getInformation().equals(group.getInformation())) {
                    groupToAdd = group;
                    try {
                        groupAdded = group;
                        groupAdded.getPersonList().add(personToAdd);
                        model.updateGroup(groupToAdd, groupAdded);
                    } catch (DuplicatePersonException e) {
                        throw new CommandException(MESSAGE_DUPLICATE_PERSON);
                    } catch (DuplicateGroupException e) {
                        throw new CommandException(MESSAGE_DUPLICATE_GROUP);
                    } catch (GroupNotFoundException e) {
                        throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
                    }
                }
            }
            return new CommandResult(String.format(MESSAGE_ADD_PERSON_TO_GROUP_SUCCESS, personToAdd.getName(),
                    groupToAdd.getInformation().toString()));
        }
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        personToAdd = lastShownList.get(index.getZeroBased());

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMembersToGroupCommand // instanceof handles nulls
                && personToAdd.equals(((AddMembersToGroupCommand) other).personToAdd));
    }


}
