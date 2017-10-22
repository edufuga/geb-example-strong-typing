package behaviour;

import java.util.function.Predicate;

import basic.TypedPage;

public class WhenResult<EndPage extends TypedPage> {

	private EndPage endPage;

	public WhenResult(EndPage endPage) {
		this.endPage = endPage;
	}

	public EndPage getEndPage() {
		return this.endPage;
	}

	public Boolean then(Predicate<EndPage> thenAction) {
		return thenAction.test(endPage);
	}
}
