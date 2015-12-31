package jbosswildfly;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

	public LoggingInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		String cla$$ = ctx.getMethod().getDeclaringClass().getName();
		String method = ctx.getMethod().getName();
		System.out.println("Entering class=" + cla$$ + " method=" + method
				+ " parameters=" + ctx.getParameters());
		try {
			Object result = ctx.proceed();
			System.out.println("Exiting class=" + cla$$ + " method=" + method
					+ " parameters=" + result);
			return result;
		} catch (Exception e) {
			System.out.println("Throwing class=" + cla$$ + " method=" + method
					+ " parameters=" + e);
			throw e;
		}

	}
}
