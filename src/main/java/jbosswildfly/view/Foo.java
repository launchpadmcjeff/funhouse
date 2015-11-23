package jbosswildfly.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import org.primefaces.model.map.Rectangle;

@Named
@SessionScoped
public class Foo implements Serializable {
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private Marker marker;

	private MapModel model = new DefaultMapModel();
	LatLng latlngM1 = new LatLng(33.677734, -117.794649);
	LatLng latlngM2 = new LatLng(33.687734, -117.784649);
	LatLng latlngM3 = new LatLng(33.187691, -117.283570);
	LatLng northEast = new LatLng(33.212667, -117.302560);
	LatLng southWest = new LatLng(33.182645, -117.262692);

	public Foo() {
		model.addOverlay(new Marker(latlngM1, "M1"));
		model.addOverlay(new Marker(latlngM2, "M2"));
		model.addOverlay(new Marker(latlngM3, "M3"));
		for (Marker premarker : model.getMarkers()) {
			premarker.setDraggable(true);
		}
		// more overlays
		polyline.getPaths().add(latlngM1);
		polyline.getPaths().add(latlngM2);
		polyline.getPaths().add(latlngM3);
		model.addOverlay(polyline);

		Polygon polygon = new Polygon();
		polygon.getPaths().add(latlngM1);
		polygon.getPaths().add(latlngM2);
		polygon.getPaths().add(latlngM3);
		model.addOverlay(polygon);

		Circle circle = new Circle(latlngM3, 500);
		circle.setStrokeColor("#00ff00");
		circle.setFillColor("#00ff00");
		circle.setStrokeOpacity(0.7);
		circle.setFillOpacity(0.7);
		model.addOverlay(circle);

		LatLngBounds bounds;
		bounds = new LatLngBounds(northEast, southWest);
		Rectangle rectangle = new Rectangle(bounds);
		rectangle.setFillOpacity(0.0);
		model.addOverlay(rectangle);

	}

	Polyline polyline = new Polyline();

	public void onMarkerDrag(MarkerDragEvent event) {
		marker = event.getMarker();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged",
						"Lat:" + marker.getLatlng().getLat() + ", Lng:"
								+ marker.getLatlng().getLng()));
	}

	public void onCircleSelect(OverlaySelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Circle Selected",
						null));
	}

	public MapModel getModel() {
		return this.model;
	}

	private MapModel geoModel;
	private MapModel revGeoModel;
	private String centerGeoMap = "41.850033, -87.6500523";
	private String centerRevGeoMap = "41.850033, -87.6500523";

	@PostConstruct
	public void init() {
		geoModel = new DefaultMapModel();
		revGeoModel = new DefaultMapModel();
	}

	public void onGeocodeLosAngeles(GeocodeEvent event) {
		System.out.println("onGeocodeLosAngeles");
		List<GeocodeResult> results = event.getResults();
		
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMap = center.getLat() + "," + center.getLng();
			
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				geoModel.addOverlay(new Marker(result.getLatLng(), result
						.getAddress()));
			}
		}
	}
	
	public void onGeocode(GeocodeEvent event) {
		System.out.println("onGeocode");
		List<GeocodeResult> results = event.getResults();

		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMap = center.getLat() + "," + center.getLng();

			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				geoModel.addOverlay(new Marker(result.getLatLng(), result
						.getAddress()));
			}
		}
	}

	public void onReverseGeocode(ReverseGeocodeEvent event) {
		System.out.println("onReverseGeocode");
		List<String> addresses = event.getAddresses();
		LatLng coord = event.getLatlng();

		if (addresses != null && !addresses.isEmpty()) {
			centerRevGeoMap = coord.getLat() + "," + coord.getLng();
			revGeoModel.addOverlay(new Marker(coord, addresses.get(0)));
		}
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public MapModel getRevGeoModel() {
		return revGeoModel;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public String getCenterRevGeoMap() {
		return centerRevGeoMap;
	}
}
