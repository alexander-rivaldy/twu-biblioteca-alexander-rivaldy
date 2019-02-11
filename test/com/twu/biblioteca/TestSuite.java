package com.twu.biblioteca;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        BookTest.class,
        CustomerTest.class,
        LibraryTest.class,
        LoginSystemTest.class,
        NewMainMenuTest.class,
        MovieTest.class,
        MenuIOTest.class
})
public class TestSuite {

}
