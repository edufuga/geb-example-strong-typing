package behaviour;

import java.util.function.Function;
import java.util.function.Supplier;

import geb.Browser;

public class Behaviour {

	private Browser browser;

	public Behaviour(Browser browser) {
		this.browser = browser;
	}

	public <StartPage> GivenResult<StartPage> given(Function<Browser, StartPage> givenAction) {
		StartPage startPage = givenAction.apply(browser);
		return new GivenResult<StartPage>(startPage);
	}

	public static <StartPage> GivenResult<StartPage> given(Supplier<StartPage> pageSupplier) {
		StartPage startPage = pageSupplier.get();
		return new GivenResult<StartPage>(startPage);
	}
}
