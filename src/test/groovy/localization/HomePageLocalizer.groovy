package localization

import groovy.transform.TypeChecked

@TypeChecked
interface HomePageLocalizer {

	/**
	 * Main text of the dictionary header.
	 *
	 * @return Main title.
	 */
	String getMainTitle()

	/**
	 * Sub title of the dictionary.
	 * 
	 * @return Sub title.
	 */
	String getSubTitle()
}
