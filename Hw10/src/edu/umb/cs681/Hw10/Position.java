package edu.umb.cs681.Hw10;

public final class Position {

	private final double latitude, longitude, altitude;

	public Position(double lat, double lon, double alt) {
		this.latitude = lat;
		this.longitude = lon;
		this.altitude = alt;
	}
	

	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getAltitude() {
		return altitude;
	}
	
	public String toString(){
		return latitude + " - " + longitude + " - " + altitude;
	}
	
	public boolean equals(Position nextPosition) {
		if(this.toString().equals(nextPosition.toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public Position changeLat(double updateLatitude) {
		return new Position(updateLatitude, this.longitude, this.altitude);
	}
	
	public Position changeLong(double updateLongitude) {
		return new Position(this.latitude, updateLongitude, this.altitude);
	}
	
	public Position changeAlt(double updateAltitude) {
		return new Position(this.latitude, this.longitude, updateAltitude);
	}
	
	

    public double distanceTo(Position nextPosition) {
        double lat1 = Math.toRadians(this.latitude);
        double lat2 = Math.toRadians(nextPosition.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lon2 = Math.toRadians(nextPosition.longitude);
        double lat3 = lat2 - lat1;
        double lon3 = lon2 - lon1;
        double a = Math.sin(lat3 / 2) * Math.sin(lat3 / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(lon3 / 2) * Math.sin(lon3 / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}