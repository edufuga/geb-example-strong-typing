package basic

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import geb.Browser
import geb.Module
import geb.Page
import geb.content.NavigableSupport
import geb.content.PageContentTemplate
import geb.download.DefaultDownloadSupport
import geb.download.DownloadSupport
import geb.frame.DefaultFrameSupport
import geb.frame.FrameSupport
import geb.interaction.DefaultInteractionsSupport
import geb.interaction.InteractionsSupport
import geb.js.*
import geb.navigator.Navigator
import geb.navigator.factory.NavigatorFactory
import geb.waiting.DefaultWaitingSupport
import geb.waiting.Wait
import geb.waiting.WaitingSupport
import groovy.lang.MetaClass
import groovy.transform.TypeChecked

@TypeChecked
abstract class TypedModule extends Module {

	protected Browser browser = super.browser
	protected Navigator navigator
	protected DownloadSupport downloadSupport
	protected WaitingSupport waitingSupport
	protected FrameSupport frameSupport
	protected InteractionsSupport interactionsSupport
	protected AlertAndConfirmSupport alertAndConfirmSupport
	protected JavascriptInterface js
	/**
	 * Module without "content" closure and therefore
	 * without Content Definition DSL.
	 */
	@Override
	void init(Browser browser, NavigatorFactory navigatorFactory) {
		this.browser = browser
		this.navigator = navigatorFactory.base
		this.downloadSupport = new DefaultDownloadSupport(browser)
		this.waitingSupport = new DefaultWaitingSupport(browser.config)
		this.frameSupport = new DefaultFrameSupport(browser)
		this.js = browser.js
		this.alertAndConfirmSupport = new DefaultAlertAndConfirmSupport({ this.js }, browser.config)
		this.interactionsSupport = new DefaultInteractionsSupport(browser)
		initialized()
	}

	protected Navigator getInitializedNavigator() {
		if (navigator == null) {
			throw uninitializedException()
		}
		navigator
	}

	
	boolean asBoolean() {
		getInitializedNavigator().asBoolean()
	}

	@Override
	Navigator $(Map<String, Object> predicates) {
		getInitializedNavigator().$(predicates)
	}

	@Override
	Navigator $(Map<String, Object> predicates, int index) {
		getInitializedNavigator().$(predicates, index)
	}

	@Override
	Navigator $(Map<String, Object> predicates, Range<Integer> range) {
		getInitializedNavigator().$(predicates, range)
	}

	@Override
	Navigator $(Map<String, Object> predicates, String selector) {
		getInitializedNavigator().$(predicates, selector)
	}

	@Override
	Navigator $(Map<String, Object> predicates, String selector, int index) {
		getInitializedNavigator().$(predicates, selector, index)
	}

	@Override
	Navigator $(Map<String, Object> predicates, String selector, Range<Integer> range) {
		getInitializedNavigator().$(predicates, selector, range)
	}

	@Override
	Navigator $(String selector) {
		getInitializedNavigator().$(selector)
	}

	@Override
	Navigator $(String selector, int index) {
		getInitializedNavigator().$(selector, index)
	}

	@Override
	Navigator $(String selector, Range<Integer> range) {
		getInitializedNavigator().$(selector, range)
	}

	@Override
	Navigator $(Map<String, Object> predicates, By bySelector) {
		getInitializedNavigator().$(predicates, bySelector)
	}

	@Override
	Navigator $(Map<String, Object> predicates, By bySelector, int index) {
		getInitializedNavigator().$(predicates, bySelector, index)
	}

	@Override
	Navigator $(Map<String, Object> predicates, By bySelector, Range<Integer> range) {
		getInitializedNavigator().$(predicates, bySelector, range)
	}

	@Override
	Navigator $(By bySelector) {
		getInitializedNavigator().$(bySelector)
	}

	@Override
	Navigator $(By bySelector, int index) {
		getInitializedNavigator().$(bySelector, index)
	}

	@Override
	Navigator $(By bySelector, Range<Integer> range) {
		getInitializedNavigator().$(bySelector, range)
	}

	@Override
	<T extends Module> T module(Class<T> moduleClass) {
		getInitializedNavigator().module(moduleClass)
	}

	@Override
	<T extends Module> T module(T module) {
		getInitializedNavigator().module(module)
	}

	@Override
	<T extends Module> List<T> moduleList(Class<T> moduleClass) {
		getInitializedNavigator().moduleList(moduleClass)
	}

	@Override
	<T extends Module> List<T> moduleList(Closure<T> moduleFactory) {
		getInitializedNavigator().moduleList(moduleFactory)
	}

	@Override
	String css(String propertyName) {
		getInitializedNavigator().css(propertyName)
	}

	@Override
	Navigator unique() {
		getInitializedNavigator().unique()
	}

	@Override
	int getY() {
		getInitializedNavigator().getY()
	}

	@Override
	int getX() {
		getInitializedNavigator().getX()
	}

	@Override
	int getWidth() {
		getInitializedNavigator().getWidth()
	}

	@Override
	int getHeight() {
		getInitializedNavigator().getHeight()
	}

	@Override
	JQueryAdapter getJquery() {
		getInitializedNavigator().getJquery()
	}

	@Override
	Navigator verifyNotEmpty() {
		getInitializedNavigator().verifyNotEmpty()
	}

	@Override
	Navigator findAll(Closure predicate) {
		getInitializedNavigator().findAll(predicate)
	}

	@Override
	Iterator<Navigator> iterator() {
		getInitializedNavigator().iterator()
	}

	@Override
	Collection<WebElement> allElements() {
		getInitializedNavigator().allElements()
	}

	@Override
	WebElement lastElement() {
		getInitializedNavigator().lastElement()
	}

	@Override
	WebElement singleElement() {
		getInitializedNavigator().singleElement()
	}

	@Override
	WebElement firstElement() {
		getInitializedNavigator().firstElement()
	}

	@Override
	Navigator tail() {
		getInitializedNavigator().tail()
	}

	@Override
	Navigator last() {
		getInitializedNavigator().last()
	}

	@Override
	Navigator first() {
		getInitializedNavigator().first()
	}

	@Override
	Navigator head() {
		getInitializedNavigator().head()
	}

	@Override
	boolean isEmpty() {
		getInitializedNavigator().isEmpty()
	}

	@Override
	int size() {
		getInitializedNavigator().size()
	}

	@Override
	Navigator click(List potentialPages, Wait wait = null) {
		getInitializedNavigator().click(potentialPages, wait)
	}

	@Override
	Navigator click(Page pageInstance, Wait wait = null) {
		getInitializedNavigator().click(pageInstance, wait)
	}

	@Override
	Navigator click(Class<? extends Page> pageClass, Wait wait = null) {
		getInitializedNavigator().click(pageClass, wait)
	}

	@Override
	Navigator click() {
		getInitializedNavigator().click()
	}

	@Override
	Navigator leftShift(value) {
		getInitializedNavigator() << value
	}

	@Override
	Navigator value(value) {
		getInitializedNavigator().value(value)
	}

	@Override
	def value() {
		getInitializedNavigator().value()
	}

	@Override
	List<String> classes() {
		getInitializedNavigator().classes()
	}

	@Override
	String attr(String name) {
		getInitializedNavigator().attr(name)
	}

	@Override
	String getAttribute(String name) {
		getInitializedNavigator().getAttribute(name)
	}

	@Override
	String text() {
		getInitializedNavigator().text()
	}

	@Override
	String tag() {
		getInitializedNavigator().tag()
	}

	@Override
	boolean isDisplayed() {
		getInitializedNavigator().isDisplayed()
	}

	@Override
	boolean is(String tag) {
		getInitializedNavigator().is(tag)
	}

	@Override
	boolean hasClass(String className) {
		getInitializedNavigator().hasClass(className)
	}

	@Override
	Navigator siblings(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().siblings(attributes, selector)
	}

	@Override
	Navigator siblings(Map<String, Object> attributes) {
		getInitializedNavigator().siblings(attributes)
	}

	@Override
	Navigator siblings() {
		getInitializedNavigator().siblings()
	}

	@Override
	Navigator children(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().children(attributes, selector)
	}

	@Override
	Navigator children(Map<String, Object> attributes) {
		getInitializedNavigator().children(attributes)
	}

	@Override
	Navigator children() {
		getInitializedNavigator().children()
	}

	@Override
	Navigator closest(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().closest(attributes, selector)
	}

	@Override
	Navigator closest(Map<String, Object> attributes) {
		getInitializedNavigator().closest(attributes)
	}

	@Override
	Navigator parentsUntil(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().parentsUntil(attributes, selector)
	}

	@Override
	Navigator parentsUntil(Map<String, Object> attributes) {
		getInitializedNavigator().parentsUntil(attributes)
	}

	@Override
	Navigator parents(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().parents(attributes, selector)
	}

	@Override
	Navigator parents(Map<String, Object> attributes) {
		getInitializedNavigator().parents(attributes)
	}

	@Override
	Navigator parents() {
		getInitializedNavigator().parents()
	}

	@Override
	Navigator parent(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().parent(attributes, selector)
	}

	@Override
	Navigator parent(Map<String, Object> attributes) {
		getInitializedNavigator().parent(attributes)
	}

	@Override
	Navigator parent() {
		getInitializedNavigator().parent()
	}

	@Override
	Navigator prevUntil(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().prevUntil(attributes, selector)
	}

	@Override
	Navigator prevUntil(Map<String, Object> attributes) {
		getInitializedNavigator().prevUntil(attributes)
	}

	@Override
	Navigator prevAll(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().prevAll(attributes, selector)
	}

	@Override
	Navigator prevAll(Map<String, Object> attributes) {
		getInitializedNavigator().prevAll(attributes)
	}

	@Override
	Navigator prevAll() {
		getInitializedNavigator().prevAll()
	}

	@Override
	Navigator previous(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().previous(attributes, selector)
	}

	@Override
	Navigator previous(Map<String, Object> attributes) {
		getInitializedNavigator().previous(attributes)
	}

	@Override
	Navigator previous() {
		getInitializedNavigator().previous()
	}

	@Override
	Navigator nextUntil(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().nextUntil(attributes, selector)
	}

	@Override
	Navigator nextUntil(Map<String, Object> attributes) {
		getInitializedNavigator().nextUntil(attributes)
	}

	@Override
	Navigator nextAll(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().nextAll(attributes, selector)
	}

	@Override
	Navigator nextAll(Map<String, Object> attributes) {
		getInitializedNavigator().nextAll(attributes)
	}

	@Override
	Navigator nextAll() {
		getInitializedNavigator().nextAll()
	}

	@Override
	Navigator next(Map<String, Object> attributes = [:], String selector) {
		getInitializedNavigator().next(attributes, selector)
	}

	@Override
	Navigator next(Map<String, Object> attributes) {
		getInitializedNavigator().next(attributes)
	}

	@Override
	Navigator next() {
		getInitializedNavigator().next()
	}

	@Override
	Navigator plus(Navigator navigator) {
		getInitializedNavigator() + navigator
	}

	@Override
	Navigator remove(int index) {
		getInitializedNavigator().remove(index)
	}

	@Override
	Navigator add(Collection<WebElement> elements) {
		getInitializedNavigator().add(elements)
	}

	@Override
	Navigator add(WebElement[] elements) {
		getInitializedNavigator().add(elements)
	}

	@Override
	Navigator add(By bySelector) {
		getInitializedNavigator().add(bySelector)
	}

	@Override
	Navigator add(String selector) {
		getInitializedNavigator().add(selector)
	}

	@Override
	Navigator getAt(Collection indexes) {
		getInitializedNavigator().getAt(indexes)
	}

	@Override
	Navigator getAt(Range range) {
		getInitializedNavigator()[range]
	}

	@Override
	Navigator getAt(int index) {
		getInitializedNavigator()[index]
	}

	@Override
	Navigator eq(int index) {
		getInitializedNavigator().eq(index)
	}

	@Override
	Navigator not(Map<String, Object> predicates = [:], String selector) {
		getInitializedNavigator().not(predicates, selector)
	}

	@Override
	Navigator not(Map<String, Object> predicates) {
		getInitializedNavigator().not(predicates)
	}

	@Override
	Navigator filter(Map<String, Object> predicates = [:], String selector) {
		getInitializedNavigator().filter(predicates, selector)
	}

	@Override
	Navigator filter(Map<String, Object> predicates) {
		getInitializedNavigator().filter(predicates)
	}

	@Override
	Navigator has(Map<String, Object> predicates = [:], By bySelector) {
		getInitializedNavigator().has(predicates, bySelector)
	}

	@Override
	Navigator has(Map<String, Object> predicates) {
		getInitializedNavigator().has(predicates)
	}

	@Override
	Navigator has(Map<String, Object> predicates = [:], String selector) {
		getInitializedNavigator().has(predicates, selector)
	}

	@Override
	Navigator hasNot(Map<String, Object> predicates) {
		getInitializedNavigator().hasNot(predicates)
	}

	@Override
	Navigator hasNot(Map<String, Object> predicates = [:], String selector) {
		getInitializedNavigator().hasNot(predicates, selector)
	}

	@Override
	Navigator hasNot(Map<String, Object> predicates = [:], By bySelector) {
		getInitializedNavigator().hasNot(predicates, bySelector)
	}

	@Override
	Navigator find(Map<String, Object> predicates) {
		getInitializedNavigator().find(predicates)
	}

	@Override
	Navigator find(Map<String, Object> predicates, int index) {
		getInitializedNavigator().find(predicates, index)
	}

	@Override
	Navigator find(Map<String, Object> predicates, Range<Integer> range) {
		getInitializedNavigator().find(predicates, range)
	}

	@Override
	Navigator find(Map<String, Object> predicates, String selector) {
		getInitializedNavigator().find(predicates, selector)
	}

	@Override
	Navigator find(Map<String, Object> predicates, String selector, int index) {
		getInitializedNavigator().find(predicates, selector, index)
	}

	@Override
	Navigator find(Map<String, Object> predicates, String selector, Range<Integer> range) {
		getInitializedNavigator().find(predicates, selector, range)
	}

	@Override
	Navigator find(String selector) {
		getInitializedNavigator().find(selector)
	}

	@Override
	Navigator find(String selector, int index) {
		getInitializedNavigator().find(selector, index)
	}

	@Override
	Navigator find(String selector, Range<Integer> range) {
		getInitializedNavigator().find(selector, range)
	}

	@Override
	Navigator find(Map<String, Object> predicates, By bySelector) {
		getInitializedNavigator().find(predicates, bySelector)
	}

	@Override
	Navigator find(Map<String, Object> predicates, By bySelector, int index) {
		getInitializedNavigator().find(predicates, bySelector, index)
	}

	@Override
	Navigator find(Map<String, Object> predicates, By bySelector, Range<Integer> range) {
		getInitializedNavigator().find(predicates, bySelector, range)
	}

	@Override
	Navigator find(By bySelector) {
		getInitializedNavigator().find(bySelector)
	}

	@Override
	Navigator find(By bySelector, int index) {
		getInitializedNavigator().find(bySelector, index)
	}

	@Override
	Navigator find(By bySelector, Range<Integer> range) {
		getInitializedNavigator().find(bySelector, range)
	}

	@Override
	boolean isFocused() {
		getInitializedNavigator().focused
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

}
