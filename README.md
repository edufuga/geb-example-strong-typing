# Simple example of a strongly typed Geb project

This is a simple example of a test automation project using
[Geb](http://www.gebish.org/) as the browser automation framework and
[Spock](http://www.gebish.org/) or [JUnit](http://junit.org/junit4/) as
the testing framework.

The tests open an [online dictionary](http://pons.com) and look up a
single word or change the home page between different languages. These
are just simple examples for illustrating the proof of concept of
having strongly typed Geb code.

The idea being the proof of concept is to write tests in a more type
safe manner than is currently possible with Geb and Groovy. Geb is a
Groovy framework that makes heavy use of Groovy's dynamic programming
features; the content definition DSL of Geb is based, for example, on
calling unknown methods with a Closure as parameter in order to define
the content of a Page. A consequence of this is that the content
definitions (the elements on a certain Page relevant for the automation
code) are unknown to the compiler and the IDE. The advantage of more
concise code is constrasted by the lack of autocompletion or refactoring
support in IDEs such as [Eclipse](https://eclipse.org/).

An attempt to make the content definition DSL of Geb type safe using
Groovy annotations like `@DelegatesTo` or own type checking extensions
does not seem possible. The reason is that the DSL of Geb does not use
a priori known method names with a Closure as a parameter, but unknown
ones.

Instead of this, the current proof of concept consists on turning the
main classes of Geb, `Page` and `Module`, into typed checked, abstract
classes. These are the classes `TypedPage` and `TypedModule`. These
abstract subclasses are the basis of the project. They *remove* the
Content DSL of Geb (this is acomplished by making the private delegate
`PageContentSupport` of `Page` unreachable in `TypedPage` and similarly
for `TypedModule`). This means that the elements of a page are not
declared inside of the static `content` Closure; they are declared in
form of methods of the page that return a `Navigator` or, alternatively,
as attributes (fields) of the type `Closure<Navigator>` that, like
methods, return an instance of `Navigator` when they are called. In both
cases the content definition is not evaluated directly with the
initialisation of the Page but once the method or closure is called.

An advantage of declaring content like this is that the content
definitions using the JQuery-like syntax of Geb (the different overloads
of the `$` method, for example) are *understood* by the IDE and checked
by the compiler, if using the `@TypeChecked` or `@CompileStatic`
annotation. The price to pay for this is more verbose code compared to
the content DSL of Geb.

Other outcomes of this approach are, for example, that content
definitions are automatically inherited, simply because they are normal
methods or attributes of the page class. The content definitions within
the `content` Closure provided by the content DSL of Geb "simulate"
inheritance iterating from the subclass to the `Page` superclass and
overwritting the content definitions; this is now not necessary and it
also allows for better reusing content definitions.

The type safety of the content definitions is something that the DSL of
Geb is currently unable of. Support for autocompletion and refactoring
within the IDE is perceived here as more important over concise syntax.

An example of a typed Page and a typed Module can be found in the
classes `PageWithElementMethods` and `ModuleWithElementMethods`. The
corresponding test is `PageWithElementMethodsSpec`.

A more elaborate example is the abstract generic class `HomePage` and
its subclasses `HomePageGerman` and `HomePageSpanish`. Here `HomePage`
defines the *common content* independent of the localization (language).
Language *specifica* like text is provided by implementations of the
`HomePageLocalizer` interface. Additional content definitions specific
to each localized version of the page would go in the corresponding
subclasse (e.g. `HomePageGerman` could declare content not available in
`HomePageSpanish`).

Additionally the subclasses of `HomePage` illustrate the concept of an
`Action`. The idea here is to extend the *logic* of a Page (provided in
form of methods of the page) with other *user actions*. In order words:
A page not only provides own methods for "obvious" actions logically
belonging to the page (this is, of course, a subjective interpretation)
but also an extension point for a posteriori defined navigation actions.
One such navigation would be simply changing from one page to another.
This is what happens in `HomePageSpanishToGermanAction`, for example.
This action is not something that belongs logically to the page class,
and this way the page logic can be complemented by other actions, making
the page code not unnecessarily bloated.

Another advantage of `Action`s is that they can be *chained*. Every page
should provide an own `run(Action<CurrentPage, OtherPage> action)`
method that applies the `action` to the current page (i.e. `this`) and,
after performing some logic, ends in a generally different page (an
instance of `OtherPage`, which is a subclass of `TypedPage`). A very
simple example of chaining actions could look like this (JUnit test
method in Java):

```java
@Test
public void thereAndBackAgain() {
	browser.to(HomePageGerman.class)
		.run(new HomePageGermanToSpanishGroovyAction())
		.run(new HomePageSpanishToGermanAction());
}
```
Here `HomePageSpanishToGermanAction` is a Java class, whereas
`HomePageGermanToSpanishGroovyAction` is a Groovy class. Both can be
mixed and both implement the `Action` interface.

The advantage of using JUnit in this example is that (at least in
Eclipse) the support is better than with Spock. Compare `HomePageTest`
with `HomePageSpec` for a concrete example; what happens there is: Calls
to the `run` method of a certain page with the *wrong* argument (action)
are not detected as erroneous in a Spock/Groovy test within Eclipse. In
JUnit/Java the error is detected properly.
