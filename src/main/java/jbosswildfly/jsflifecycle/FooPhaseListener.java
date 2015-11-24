package jbosswildfly.jsflifecycle;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class FooPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	public FooPhaseListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("afterPhase: " + event.getPhaseId() + " Source: " + event.getSource());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("beforePhase: " + event.getPhaseId() + " Source: " + event.getSource());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
