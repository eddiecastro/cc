package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.PersonDuplicate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeopleServiceImplTest {

    private static final String FREQUENCY_TEST_EMAIL = "aaAbd@d.co";

    private List<Person> peopleList;

    @InjectMocks
    private PeopleServiceImpl systemUnderTest;

    @Before
    public void init() {
        peopleList = new ArrayList<>();
        systemUnderTest = new PeopleServiceImpl();

        initMocks(this);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_emptyList() {
        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertNotNull(result);
        assertTrue(result.keySet().isEmpty());
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_captialAndNonCapitalAreUnique() {
        initializeGetEmailCharacterFrequencyCountFromPeopleList(FREQUENCY_TEST_EMAIL);

        int expectedACount = 2;
        int expectedCapitalACount = 1;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("a"), expectedACount);
        assertEquals((int)result.get("A"), expectedCapitalACount);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_symbolsAreCounted() {
        initializeGetEmailCharacterFrequencyCountFromPeopleList(FREQUENCY_TEST_EMAIL);

        int expectedAtSymbolCount = 1;
        int expectedPeriodCount = 1;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("@"), expectedAtSymbolCount);
        assertEquals((int)result.get("."), expectedPeriodCount);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_bothSidesOfAtSymbolAreCounted() {
        initializeGetEmailCharacterFrequencyCountFromPeopleList(FREQUENCY_TEST_EMAIL);

        int expectedCCount = 1;
        int expectedDCount = 2;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("c"), expectedCCount);
        assertEquals((int)result.get("d"), expectedDCount);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_exactSameEmail() {
        String email1 = "abc@gmail.com";
        String email2 = "abc@gmail.com";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 1);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_secondEmailHasExtraCharacterInMiddle() {
        String email1 = "abc@gmail.com";
        String email2 = "abcd@gmail.com";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 1);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_secondEmailHasExtraCharacterAtBeginning() {
        String email1 = "abc@gmail.com";
        String email2 = "bcabc@gmail.com";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 1);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_secondEmailHasExtraCharacterAtEnd() {
        String email1 = "abc@gmail.com";
        String email2 = "abcd@gmail.comst";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 1);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_differentCharacterSpread() {
        String email1 = "abc@gmail.com";
        String email2 = "avbc@gmalil.com";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 1);
    }

    @Test
    public void testGetPossibleDuplicatesFromList_tooManyDifferentCharacters() {
        String email1 = "abc@gmail.com";
        String email2 = "aabbcc@ggmail.com";
        initializeGetPossibleDuplicatesFromList(email1, email2);

        List<PersonDuplicate> possibleDuplicates = systemUnderTest.getPossibleDuplicatesFromList(peopleList);

        assertEquals(possibleDuplicates.size(), 0);
    }

    private void initializeGetEmailCharacterFrequencyCountFromPeopleList(String email) {
        Person person = mock(Person.class);
        when(person.getEmailAddress()).thenReturn(email);
        peopleList.add(person);
    }

    private void initializeGetPossibleDuplicatesFromList(String email1, String email2) {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);
        when(person1.getEmailAddress()).thenReturn(email1);
        when(person2.getEmailAddress()).thenReturn(email2);
        peopleList.add(person1);
        peopleList.add(person2);
    }
}
