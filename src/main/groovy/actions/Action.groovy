package actions

public interface Action<I, O> {
	public O run(I from)
}
