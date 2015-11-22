package jbosswildfly.view;

import java.io.Serializable;

import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
public class Foo implements Serializable {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private MapModel model = new DefaultMapModel();

	public Foo() {
		model.addOverlay(new Marker(new LatLng(33.677734, -117.794649), "M1"));
		model.addOverlay(new Marker(new LatLng(33.687734, -117.784649), "M2"));
		model.addOverlay(new Marker(new LatLng(33.187691, -117.283570), "M3"));
		// more overlays
	}
	public MapModel getModel() {
		return this.model; }
	
}
