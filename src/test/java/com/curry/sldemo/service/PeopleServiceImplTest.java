package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeopleServiceImplTest {

    private static final String TEST_EMAIL = "aaAbd@d.co";

    private List<Person> peopleList;

    @Mock
    private Person person;

    @InjectMocks
    private PeopleServiceImpl systemUnderTest;

    @Before
    public void init() {
        peopleList = new ArrayList<>();
        systemUnderTest = new PeopleServiceImpl();

        initMocks(this);
        when(person.getEmailAddress()).thenReturn(TEST_EMAIL);
        peopleList.add(person);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_emptyList() {
        List<Person> peopleList = new ArrayList<>();
        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertNotNull(result);
        assertTrue(result.keySet().isEmpty());
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_captialAndNonCapitalAreUnique() {
        int expectedACount = 2;
        int expectedCapitalACount = 1;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("a"), expectedACount);
        assertEquals((int)result.get("A"), expectedCapitalACount);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_symbolsAreCounted() {
        int expectedAtSymbolCount = 1;
        int expectedPeriodCount = 1;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("@"), expectedAtSymbolCount);
        assertEquals((int)result.get("."), expectedPeriodCount);
    }

    @Test
    public void testGetEmailCharacterFrequencyCountFromPeopleList_bothSidesOfAtSymbolAreCounted() {
        int expectedCCount = 1;
        int expectedDCount = 2;

        Map<String, Integer> result = systemUnderTest.getEmailCharacterFrequencyCountFromPeopleList(peopleList);

        assertEquals((int)result.get("c"), expectedCCount);
        assertEquals((int)result.get("d"), expectedDCount);
    }
}
