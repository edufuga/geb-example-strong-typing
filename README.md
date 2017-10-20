# Simple example of a strongly typed Geb project

This is a simple example of a test automation project using
[Geb](http://www.gebish.org/) as the browser automation framework and
[Spock](http://www.gebish.org/) or [JUnit](http://junit.org/junit4/) as
the testing framework.

The tests open an [online dictionary](http://pons.com) and look up a
single word or change the home page between different languages. Those
are just simply examples for illustrating the proof of concept of
having strongly typed Geb code.

The idea being the proof of concept is to write tests in a more typesafe
manner than is currently possible with Geb and Groovy. Geb is a Groovy
framework that makes heavy use of Groovy's dynamic programming features;
the content definition DSL of Geb is based, for example, on calling
unknown methods with a Closure as parameter in order to define the
content of a Page. A consequence of this is that the content definitions
(the elements on a certain Page relevant for the automation code) are
unknown to the compiler and the IDE. The advantage of more concise code
is constrasted by the lack of autocompletion or refactoring support in
IDEs such as [Eclipse](https://eclipse.org/).

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
