package com.lavanya.escalade.enums;

public enum Reservation {
	
//	A for AVAILABLE, R for RESERVED, U for UNAVAILABLE, I for IGNORED
	Disponible("A"), Accepte("R"), Indisponible("U"), Refusé("I");

	private String code;

    Reservation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
