package com.integracion.crud.util;

public class Constants {

	public final static String REGEXP_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
	public final static String REGEXP_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&#.$($)$-$_])[A-Za-z\\d$@$!%*?&#.$($)$-$_]{8,15}$";
	public static final int VALOR_SIZE_MIN_NAME = 3;
	public static final int VALOR_SIZE_MAX_NAME = 100;
	public static final int VALOR_SIZE_MAX_EMAIL = 100;
	public final static String MENSAJE_ERROR_REGEXP_EMAIL = "El atributo email, no esta en el formato correcto";
	public final static String MENSAJE_ERROR_REGEXP_PASSWORD = "El atributo password, no esta en el formato correcto";


}
