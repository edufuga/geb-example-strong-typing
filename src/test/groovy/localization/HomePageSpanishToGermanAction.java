package localization;

import java.util.Objects;

import actions.Action;

public class HomePageSpanishToGermanAction implements Action<HomePageSpanish, HomePageGerman> {

	@Override
	public HomePageGerman run(HomePageSpanish from) {
		Objects.requireNonNull(from, "The page has to exist.");
		Objects.requireNonNull(from.getBrowser(), "The browser has to exist.");
		return from.getBrowser().<HomePageGerman> to(HomePageGerman.class);
	}

}
