package behaviour;

import java.util.function.Function;
import java.util.function.Predicate;

public class GivenResult<StartPage> {

	private StartPage startPage;

	public GivenResult(StartPage startPage) {
		this.startPage = startPage;
	}

	public StartPage getStartPage() {
		return startPage;
	}

	public <EndPage> WhenResult<EndPage> when(Function<StartPage, EndPage> whenAction) {
		EndPage endPage = whenAction.apply(startPage);
		return new WhenResult<EndPage>(endPage);
	}

	public Boolean then(Predicate<StartPage> thenAction) {
		return thenAction.test(startPage);
	}

}
