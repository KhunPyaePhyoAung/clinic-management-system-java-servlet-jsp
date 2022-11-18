package me.khun.clinic.model.entity.validator;

import me.khun.clinic.model.entity.Address;
import me.khun.clinic.model.service.exception.InvalidFieldException;
import me.khun.clinic.util.StringUtils;

public class AddressValidator {
	
	public static final boolean STREET_NULLABLE = true;
	public static final boolean STREET_EMPTYABLE = true;
	public static final int MAX_STREET_LENGTH = 255;
	
	public static final boolean CITY_NULLABLE = false;
	public static final boolean CITY_EMPTYABLE = false;
	public static final int MAX_CITY_LENGTH = 100;
	
	public static final boolean STATE_NULLALE = false;
	public static final boolean STATE_EMPTYABLE = false;
	public static final int MAX_STATE_LENGTH = 100;
	
	public static final boolean COUNTRY_NULLABLE = true;
	public static final boolean COUNTRY_EMPTYABLE = true;
	public static final int MAX_COUNTRY_LENGTH = 100;

	public static void validate(Address address) {
		
		var street = StringUtils.isNullOrBlank(address.getStreet()) ? null : address.getStreet().trim();
		var city = StringUtils.isNullOrBlank(address.getCity()) ? null : address.getCity().trim();
		var state = StringUtils.isNullOrBlank(address.getState()) ? null : address.getState().trim();
		var country = StringUtils.isNullOrBlank(address.getCountry()) ? null : address.getCountry().trim();
		
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		
		if (!STREET_NULLABLE && street == null) {
			throw new InvalidFieldException("street", "Street cannot be empty.");
		}
		
		if (!STREET_EMPTYABLE && street != null && street.isBlank()) {
			throw new InvalidFieldException("street", "Street cannot be empty.");
		}
		
		if (street != null && street.length() > MAX_STREET_LENGTH) {
			throw new InvalidFieldException("street", "Street cannot exceed %d character(s).".formatted(MAX_STREET_LENGTH));
		}
		
		if (!CITY_NULLABLE && city == null) {
			throw new InvalidFieldException("city", "City cannot be empty.");
		}
		
		if (!CITY_EMPTYABLE && city != null && city.isBlank()) {
			throw new InvalidFieldException("city", "City cannot be empty.");
		}
		
		if (city != null && city.length() > MAX_CITY_LENGTH) {
			throw new InvalidFieldException("city", "City cannot exceed %d character(s).".formatted(MAX_CITY_LENGTH));
		}
		
		if (!STATE_NULLALE && state == null) {
			throw new InvalidFieldException("state", "State cannot be empty.");
		}
		
		if (!STATE_EMPTYABLE && state != null && state.isBlank()) {
			throw new InvalidFieldException("state", "State cannot be empty.");
		}
		
		if (state != null && state.length() > MAX_STATE_LENGTH) {
			throw new InvalidFieldException("state", "State cannot exceed %d character(s).".formatted(MAX_STATE_LENGTH));
		}
		
		if (!COUNTRY_NULLABLE && country == null) {
			throw new InvalidFieldException("country", "Country cannot be empty.");
		}
		
		if (!COUNTRY_EMPTYABLE && country != null && country.isBlank()) {
			throw new InvalidFieldException("country", "Country cannot be empty.");
		}
		
		if (country != null && country.length() > MAX_COUNTRY_LENGTH) {
			throw new InvalidFieldException("country", "Country cannot exceed %d character(s).".formatted(MAX_COUNTRY_LENGTH));
		}
	}
}
