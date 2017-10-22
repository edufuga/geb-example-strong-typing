package behaviour;

import java.util.function.Function;
import java.util.function.Predicate;

import basic.TypedPage;

public class GivenResult<StartPage extends TypedPage> {

	private StartPage startPage;

	public GivenResult(StartPage startPage) {
		this.startPage = startPage;
	}

	public StartPage getStartPage() {
		return startPage;
	}

	public <EndPage extends TypedPage> WhenResult<EndPage> when(Function<StartPage, EndPage> whenAction) {
		EndPage endPage = whenAction.apply(startPage);
		return new WhenResult<EndPage>(endPage);
	}

	public Boolean then(Predicate<StartPage> thenAction) {
		return thenAction.test(startPage);
	}

}
