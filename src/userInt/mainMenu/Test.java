package userInt.mainMenu;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Test {
	public static SecretKey testKey;
	public static IvParameterSpec testIv;
	
	public Test() {
		
	}
	
	public SecretKey getKey() {
			return testKey; 
	}
	
	public IvParameterSpec getIv() {
		return testIv;
	}
}
