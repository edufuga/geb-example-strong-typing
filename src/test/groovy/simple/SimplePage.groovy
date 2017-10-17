package simple
import basic.TypedPage
import geb.Browser
import geb.Page
import geb.content.PageContentTemplate
import groovy.lang.Delegate
import groovy.lang.MetaClass
import groovy.transform.TypeChecked

@TypeChecked
class SimplePage extends TypedPage {

	static url = "http://de.pons.com/"

	ContentBuilder builder

	@Delegate
	SimpleElements elements

	// FIXME: This should also be extracted in some form of abstract class or trait.
	// There should be a method for obtaining the elements (content definitions).
	@Override
	Page init(Browser browser) {
		super.init(browser)
		builder = ContentBuilder.newInstance().withBrowser(browser).withPage(this)
		elements = new SimpleElements(browser, this)
		this
	}

}
