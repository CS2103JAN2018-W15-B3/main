package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TimeTableLink;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TIMETABLE_LINK = "http://modsn.us/MYwiD";
    public static final String DEFAULT_DETAIL = "Likes tennis";
    public static final String DEFAULT_GROUPS = "CS1010";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private TimeTableLink link;
    private Detail detail;
    private Set<Group> groups;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        link = new TimeTableLink(DEFAULT_TIMETABLE_LINK);
        detail = new Detail(DEFAULT_DETAIL);
        groups = SampleDataUtil.getGroupSet(DEFAULT_GROUPS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        link = personToCopy.getTimeTableLink();
        detail = personToCopy.getDetail();
        groups = new HashSet<>(personToCopy.getGroups());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withGroups(String ... groups) {
        this.groups = SampleDataUtil.getGroupSet(groups);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code TimeTableLink} of the {@code Person} that we are building.
     */
    public PersonBuilder withTimeTableLink(String link) {
        this.link = new TimeTableLink(link);
        return this;
    }

    /**
     * Sets the {@code Detail} of the {@code Person} that we are building.
     */
    public PersonBuilder withDetail(String detail) {
        this.detail = new Detail(detail);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, link, detail, groups);
    }

}
