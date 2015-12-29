package jbosswildfly;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

	public LoggingInterceptor() {
		// TODO Auto-generated constructor stub
	}

	//@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		System.out.println("Bean intercepted: " + context);
		return context.proceed();
	}
}
