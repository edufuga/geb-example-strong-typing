package basic

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import geb.AtVerificationResult
import geb.Browser
import geb.Module
import geb.Page
import geb.content.NavigableSupport
import geb.download.DefaultDownloadSupport
import geb.download.DownloadSupport
import geb.error.UndefinedAtCheckerException
import geb.error.UnexpectedPageException
import geb.frame.DefaultFrameSupport
import geb.frame.FrameSupport
import geb.interaction.DefaultInteractionsSupport
import geb.interaction.InteractionsSupport
import geb.js.AlertAndConfirmSupport
import geb.js.DefaultAlertAndConfirmSupport
import geb.navigator.Navigator
import geb.url.UrlFragment
import geb.waiting.DefaultWaitingSupport
import geb.waiting.Wait
import geb.waiting.WaitTimeoutException
import geb.waiting.WaitingSupport
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.lang.Closure
import groovy.lang.Delegate
import groovy.transform.TypeCheckingMode

@TypeChecked
abstract class TypedPage extends Page {

	protected Browser browser = super.getBrowser()
	protected NavigableSupport navigableSupport
	protected DownloadSupport downloadSupport
	protected WaitingSupport waitingSupport
	protected FrameSupport frameSupport
	protected InteractionsSupport interactionsSupport
	protected AlertAndConfirmSupport alertAndConfirmSupport

	protected static String url = ""

	protected static Closure<Boolean> at = {return true}

	protected static atCheckWaiting = false

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
	Browser getBrowser() {
		browser
	}

	@Override
	abstract String getPageUrl()

	@Override
	static Closure getAt() {
		return at
	}

	@Override
	String getPageUrl(String path) {
		String pageUrl = getPageUrl()
		path ? (pageUrl ? pageUrl + path : path) : pageUrl
	}
	
	@Override
	Browser getInitializedBrowser() {
		if (browser == null) {
			throw uninitializedException()
		}
		browser
	}

	/**
	 * Checks if the browser is not at an unexpected page and then executes this page's "at checker".
	 *
	 * @return whether the at checker succeeded or not.
	 * @see #verifyAtSafely(boolean)
	 * @throws AssertionError if this page's "at checker" doesn't pass (with implicit assertions enabled)
	 * @throws UnexpectedPageException when at an unexpected page
	 */
	@Override
	boolean verifyAt() {
		def verificationResult = getAtVerificationResult(true)
		if (!verificationResult) {
			getInitializedBrowser().checkIfAtAnUnexpectedPage(getClass())
			verificationResult.rethrowAnyErrors()
		}
		verificationResult
	}

	/**
	 * Executes this page's "at checker", suppressing any AssertionError that is thrown
	 * and returning false.
	 *
	 * @return whether the at checker succeeded or not.
	 * @see #verifyAt()
	 */
	@Override
	boolean verifyAtSafely(boolean honourGlobalAtCheckWaiting = true) {
		getAtVerificationResult(honourGlobalAtCheckWaiting)
	}

	/**
	 * Executes this page's "at checker" and captures the result wrapping up any AssertionError that might have been thrown.
	 *
	 * @return at verification result with any AssertionError that might have been thrown wrapped up
	 * @see AtVerificationResult
	 */
	@Override
	AtVerificationResult getAtVerificationResult(boolean honourGlobalAtCheckWaiting = true) {
		Throwable caughtException = null
		boolean atResult = false
		try {
			atResult = verifyThisPageAtOnly(honourGlobalAtCheckWaiting)
		} catch (AssertionError e) {
			caughtException = e
		}
		new AtVerificationResult(atResult, caughtException)
	}

	/**
	 * Executes this page's "at checker".
	 *
	 * @return whether the at checker succeeded or not.
	 * @throws AssertionError if this page's "at checker" doesn't pass (with implicit assertions enabled)
	 */
	protected boolean verifyThisPageAtOnly(boolean honourGlobalAtCheckWaiting) {
		Closure verifier = (Closure) getAt().clone()
		if (verifier) {
			verifier.delegate = this
			verifier.resolveStrategy = Closure.DELEGATE_FIRST
			def atCheckWaiting = getEffectiveAtCheckWaiting(honourGlobalAtCheckWaiting)
			if (atCheckWaiting) {
				atCheckWaiting.waitFor(verifier)
			} else {
				verifier()
			}
		} else {
			// FIXME
			// throw new UndefinedAtCheckerException()
			false
		}
	}

	@Override
	protected Wait getGlobalAtCheckWaiting(boolean honourGlobalAtCheckWaiting) {
		honourGlobalAtCheckWaiting ? getInitializedBrowser().config.atCheckWaiting : null
	}

	@Override
	protected Wait getEffectiveAtCheckWaiting(boolean honourGlobalAtCheckWaiting) {
		atCheckWaiting != null ? pageLevelAtCheckWaiting : getGlobalAtCheckWaiting(honourGlobalAtCheckWaiting)
	}

	@Override
	protected Wait getPageLevelAtCheckWaiting() {
		def atCheckWaitingValue = atCheckWaiting
		getInitializedBrowser().config.getWaitForParam(atCheckWaitingValue)
	}

	/**
	 * Page without "content" closure and therefore
	 * without Content Definition DSL.
	 */
	@Override
	Page init(Browser browser) {
		println "Initializing the page $this."
		this.browser = browser
		navigableSupport = new NavigableSupport(browser.navigatorFactory)
		downloadSupport = new DefaultDownloadSupport(browser)
		waitingSupport = new DefaultWaitingSupport(browser.config)
		frameSupport = new DefaultFrameSupport(browser)
		interactionsSupport = new DefaultInteractionsSupport(browser)
		alertAndConfirmSupport = new DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
		this.navigatorCache = [:]
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

	@Override
	def methodMissing(String name, args) {

	}

	@Override
	def propertyMissing(String name) {
		
	}

	@Override
	def propertyMissing(String name, val) {
		
	}

	protected Map<Integer, Navigator> navigatorCache = [:]

	// FIXME: Likely the caching does not work with different closure parameters.
	// TODO: Add required.
	/**
	 * Explicit waiting and caching support for a content definition.
	 * 
	 * <p>
	 * <strong>
	 * The closure has to be the same at every method call if its Navigator has to be cached.
	 * </strong>
	 * </p>
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * {@code
	 * private final Closure<Navigator> _searchBox = {$("input#q")}
	 *  
	 * Navigator getSearchBox() {
	 *    getDefinition(_searchBox, true, true)
	 * }
	 * </pre>
	 * </p>
	 * 
	 * @param definition Content Definition
	 * @return Navigator corresponding to the definition
	 */
	protected Navigator getDefinition(Closure<Navigator> definition, boolean waiting = false, boolean cache = false) {
		Navigator result

		if (cache) {
			Navigator element = navigatorCache[definition.hashCode()]
			if (element) {
				return element
			}
			else {
				navigatorCache[definition.hashCode()] = getDefinition(definition, waiting, false)
			}
		}

		if (waiting) {
			try {
				result = waitFor(definition)
			}
			catch(WaitTimeoutException we) {
			}
		}
		else {
			result = definition()
		}

		result
	}

}
