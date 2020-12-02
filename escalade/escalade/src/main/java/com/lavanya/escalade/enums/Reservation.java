package com.lavanya.escalade.enums;

/**
 * enum required for Topo class and required as one of its attributes 
 * Different type of Reservation possible.
 * @author lavanya
 */
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
