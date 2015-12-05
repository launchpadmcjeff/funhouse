package jbosswildfly.view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DateHack {

	private Date a;
	private Date b;
	private Date c;
	private Date d;
	private Date e;
	private Date f;
	private Date g;
	private Date h;
	
	public Date getA() {
		return a;
	}

	public void setA(Date a) {
		this.a = a;
	}

	public Date getB() {
		return b;
	}

	public void setB(Date b) {
		this.b = b;
	}

	public Date getC() {
		return c;
	}

	public void setC(Date c) {
		this.c = c;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public Date getE() {
		return e;
	}

	public void setE(Date e) {
		this.e = e;
	}

	public Date getF() {
		return f;
	}

	public void setF(Date f) {
		this.f = f;
	}

	public Date getG() {
		return g;
	}

	public void setG(Date g) {
		this.g = g;
	}

	public Date getH() {
		return h;
	}

	public void setH(Date h) {
		this.h = h;
	}

	public DateHack() {
		// TODO Auto-generated constructor stub
	}

	public String action() {
		System.out.println(toString());
		return null;
	}

	@Override
	public String toString() {
		return "DateHack [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
				+ ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + "]";
	}
}
