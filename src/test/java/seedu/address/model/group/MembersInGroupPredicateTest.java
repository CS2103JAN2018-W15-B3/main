//@@author jas5469
package seedu.address.model.group;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.TypicalGroups;
import seedu.address.testutil.TypicalPersons;

public class MembersInGroupPredicateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    private Group groupTest = TypicalGroups.GROUP_F;

    @Test
    public void test_groupContainsMember_returnsTrue() {
        // One keyword
        MembersInGroupPredicate predicate = new MembersInGroupPredicate(groupTest);
        assertTrue(predicate.test(TypicalPersons.ALICE));
    }

    @Test
    public void test_groupDoesNotContainsMember_returnsFalse() {
        // One keyword
        MembersInGroupPredicate predicate = new MembersInGroupPredicate(groupTest);
        assertFalse(predicate.test(TypicalPersons.CARL));
    }

}