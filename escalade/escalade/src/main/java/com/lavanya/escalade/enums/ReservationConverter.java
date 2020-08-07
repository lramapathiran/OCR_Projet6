package com.lavanya.escalade.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ReservationConverter implements AttributeConverter<Reservation, String> {
	
	@Override
    public String convertToDatabaseColumn(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return reservation.getCode();
    }

    @Override
    public Reservation convertToEntityAttribute(final String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Reservation.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
