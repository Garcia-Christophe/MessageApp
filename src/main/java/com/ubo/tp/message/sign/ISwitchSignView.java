package main.java.com.ubo.tp.message.sign;

public interface ISwitchSignView {

	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(ISwitchSignViewObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(ISwitchSignViewObserver observer);

	void switchToSignInView();

	void switchToSignUpView();

}
