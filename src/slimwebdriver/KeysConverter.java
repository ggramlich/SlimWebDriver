package slimwebdriver;

import org.openqa.selenium.Keys;

import fitnesse.slim.Converter;

public class KeysConverter implements Converter {

	@Override
	public Object fromString(String keyCode) {
		return Keys.valueOf(keyCode);
	}

	@Override
	public String toString(Object key) {
		return ((Keys) key).name();
	}

}
