package jbosswildfly.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FooInner {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int x = 99;
		Foo foo = new Foo();
		System.out.println(foo.bar);
		if (x == 99) {
			class Bar {
				int x;

				private Bar() {
				}
			}
			Bar b = new Bar();
			System.out.println(b);
		}
	}

	private class Foo {
		private String bar = "woof";
		private Foo() {
			System.out.println("it worked");
		}
	}
}
