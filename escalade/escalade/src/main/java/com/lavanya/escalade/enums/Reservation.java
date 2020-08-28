package com.lavanya.escalade.enums;

public enum Reservation {
	
//	A for AVAILABLE, R for RESERVED, U for UNAVAILABLE, I for In progress
	A("Disponible"), R("Accepté"), U("Indisponible"), I("En cours de réservation");

	private String label;

    Reservation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
