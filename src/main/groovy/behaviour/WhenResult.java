package behaviour;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhenResult<EndPage> {

	private EndPage endPage;

	public WhenResult(EndPage endPage) {
		this.endPage = endPage;
	}

	public EndPage getEndPage() {
		return this.endPage;
	}

	public <NextEndPage> WhenResult<NextEndPage> when(Function<EndPage, NextEndPage> whenAction) {
		NextEndPage nextEndPage = whenAction.apply(endPage);
		return new WhenResult<NextEndPage>(nextEndPage);
	}

	public Boolean then(Predicate<EndPage> thenAction) {
		return thenAction.test(endPage);
	}
}
