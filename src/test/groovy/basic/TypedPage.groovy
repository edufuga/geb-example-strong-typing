package basic
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import geb.Browser
import geb.Module
import geb.Page
import geb.content.NavigableSupport
import geb.download.DefaultDownloadSupport
import geb.download.DownloadSupport
import geb.frame.DefaultFrameSupport
import geb.frame.FrameSupport
import geb.interaction.DefaultInteractionsSupport
import geb.interaction.InteractionsSupport
import geb.js.AlertAndConfirmSupport
import geb.js.DefaultAlertAndConfirmSupport
import geb.navigator.Navigator
import geb.url.UrlFragment
import geb.waiting.DefaultWaitingSupport
import geb.waiting.WaitingSupport
import groovy.transform.TypeChecked
import groovy.lang.Closure
import groovy.lang.Delegate
import groovy.transform.TypeCheckingMode
import simple.ContentBuilder

@TypeChecked
class TypedPage extends Page {

	protected Browser browser = super.getBrowser()
	protected NavigableSupport navigableSupport
	protected DownloadSupport downloadSupport
	protected WaitingSupport waitingSupport
	protected FrameSupport frameSupport
	protected InteractionsSupport interactionsSupport
	protected AlertAndConfirmSupport alertAndConfirmSupport

	@TypeChecked(TypeCheckingMode.SKIP)
	@Override
	void to(Map params, UrlFragment fragment = null, Object[] args) {
		def path = convertToPath(*args)
		if (path == null) {
			path = ""
		}
		getInitializedBrowser().go(params, getPageUrl(path), fragment ?: getPageFragment())
		getInitializedBrowser().page(this)
	}
	
	@Override
	Browser getInitializedBrowser() {
		if (browser == null) {
			throw uninitializedException()
		}
		browser
	}

	/**
	 * Page without "content" closure and therefore
	 * without Content Definition DSL.
	 */
	@Override
	Page init(Browser browser) {
		this.browser = browser
		navigableSupport = new NavigableSupport(browser.navigatorFactory)
		downloadSupport = new DefaultDownloadSupport(browser)
		waitingSupport = new DefaultWaitingSupport(browser.config)
		frameSupport = new DefaultFrameSupport(browser)
		interactionsSupport = new DefaultInteractionsSupport(browser)
		alertAndConfirmSupport = new DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
		this
	}

	// Copy-pasted from Page to avoid private navigatorFactory of Page.

	Navigator find() {
		navigableSupport.find()
	}

	Navigator $() {
		navigableSupport.$()
	}

	Navigator find(int index) {
		navigableSupport.find(index)
	}

	Navigator find(Range<Integer> range) {
		navigableSupport.find(range)
	}

	Navigator $(int index) {
		navigableSupport.$(index)
	}

	Navigator $(Range<Integer> range) {
		navigableSupport.$(range)
	}

	Navigator find(String selector) {
		navigableSupport.find(selector)
	}

	Navigator $(String selector) {
		navigableSupport.$(selector)
	}

	Navigator find(String selector, int index) {
		navigableSupport.find(selector, index)
	}

	Navigator find(String selector, Range<Integer> range) {
		navigableSupport.find(selector, range)
	}

	Navigator $(String selector, int index) {
		navigableSupport.$(selector, index)
	}

	Navigator $(String selector, Range<Integer> range) {
		navigableSupport.$(selector, range)
	}

	Navigator find(Map<String, Object> attributes) {
		navigableSupport.find(attributes)
	}

	Navigator $(Map<String, Object> attributes) {
		navigableSupport.$(attributes)
	}

	Navigator find(Map<String, Object> attributes, int index) {
		navigableSupport.find(attributes, index)
	}

	Navigator find(Map<String, Object> attributes, Range<Integer> range) {
		navigableSupport.find(attributes, range)
	}

	Navigator $(Map<String, Object> attributes, int index) {
		navigableSupport.$(attributes, index)
	}

	Navigator $(Map<String, Object> attributes, Range<Integer> range) {
		navigableSupport.$(attributes, range)
	}

	Navigator find(Map<String, Object> attributes, String selector) {
		navigableSupport.find(attributes, selector)
	}

	Navigator $(Map<String, Object> attributes, String selector) {
		navigableSupport.$(attributes, selector)
	}

	Navigator find(Map<String, Object> attributes, String selector, int index) {
		navigableSupport.$(attributes, selector, index)
	}

	Navigator find(Map<String, Object> attributes, String selector, Range<Integer> range) {
		navigableSupport.find(attributes, selector, range)
	}

	Navigator $(Map<String, Object> attributes, String selector, int index) {
		navigableSupport.$(attributes, selector, index)
	}

	Navigator $(Map<String, Object> attributes, String selector, Range<Integer> range) {
		navigableSupport.$(attributes, selector, range)
	}

	Navigator $(Map<String, Object> attributes, By bySelector) {
		navigableSupport.find(attributes, bySelector)
	}

	Navigator find(Map<String, Object> attributes, By bySelector) {
		navigableSupport.find(attributes, bySelector)
	}

	Navigator $(Map<String, Object> attributes, By bySelector, int index) {
		navigableSupport.find(attributes, bySelector, index)
	}

	Navigator find(Map<String, Object> attributes, By bySelector, int index) {
		navigableSupport.find(attributes, bySelector, index)
	}

	Navigator $(Map<String, Object> attributes, By bySelector, Range<Integer> range) {
		navigableSupport.find(attributes, bySelector, range)
	}

	Navigator find(Map<String, Object> attributes, By bySelector, Range<Integer> range) {
		navigableSupport.find(attributes, bySelector, range)
	}

	Navigator $(By bySelector) {
		navigableSupport.find(bySelector)
	}

	Navigator find(By bySelector) {
		navigableSupport.find(bySelector)
	}

	Navigator $(By bySelector, int index) {
		navigableSupport.find(bySelector, index)
	}

	Navigator find(By bySelector, int index) {
		navigableSupport.find(bySelector, index)
	}

	Navigator $(By bySelector, Range<Integer> range) {
		navigableSupport.find(bySelector, range)
	}

	Navigator find(By bySelector, Range<Integer> range) {
		navigableSupport.find(bySelector, range)
	}

	Navigator $(Navigator[] navigators) {
		navigableSupport.$(navigators)
	}

	Navigator $(WebElement[] elements) {
		navigableSupport.$(elements)
	}

	// Copy-Paste from Page for waiting support
	
	@Override
	<T extends Module> T module(Class<T> moduleClass) {
		navigableSupport.module(moduleClass)
	}

	@Override
	<T extends Module> T module(T module) {
		navigableSupport.module(module)
	}

	@Override
	def <T> T waitFor(Map params = [:], String waitPreset, Closure<T> block) {
		waitingSupport.waitFor(params, waitPreset, block)
	}

	@Override
	def <T> T waitFor(Map params = [:], Closure<T> block) {
		waitingSupport.waitFor(params, block)
	}

	@Override
	def <T> T waitFor(Map params = [:], Double timeout, Closure<T> block) {
		waitingSupport.waitFor(params, timeout, block)
	}

	@Override
	def <T> T waitFor(Map params = [:], Double timeout, Double interval, Closure<T> block) {
		waitingSupport.waitFor(params, timeout, interval, block)
	}

	// Explicit waiting support for definition
	// TODO: Add cache(?) and required.
	Navigator getDefinition(Closure<Navigator> definition, boolean waiting = false) {
		if (waiting) {
			return waitFor(definition)
		}
		return definition()
	}


}
