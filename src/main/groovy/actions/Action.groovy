package actions

@FunctionalInterface
public interface Action<I, O> {
	public O run(I from)
}
