package localization;

import java.util.Objects;

public class HomePageGermanToSpanishJavaAction implements HomePageGermanAction<HomePageSpanish> {

	@Override
	public HomePageSpanish run(HomePageGerman from) {
		Objects.requireNonNull(from, "The page has to exist.");
		Objects.requireNonNull(from.getBrowser(), "The browser has to exist.");
		return from.getBrowser().<HomePageSpanish> to(HomePageSpanish.class);
	}

}
