package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

class ExampleTesterInner {
  public String foo() { return ""; }
}

@RunWith(MockitoJUnitRunner.class)
public class ExampleTester {
  @Mock ExampleTesterInner foo;

  @Test
  public void testAssertEquals() {
    when(foo.foo()).thenReturn("foo");

    assertEquals(foo.foo(), "foo");
  }
}
