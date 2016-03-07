package org.usfirst.frc3219.Robot_2016.utility;

public class Utility {
	public static int unsigned(byte in){
		int i = in;
		if(i < 0 ) {
			i += 256;
		}
		
		return i;
	}
	
	public static int unsigned(short in){
		int i = in;
		if (i < 0) {
			return 65536 + i;
		}
		
		return i;
	}
	
	public static short getShort(byte[] data, int offset){
		int byteOne = unsigned(data[offset]);
		int byteTwo = unsigned(data[offset + 1]);
		short result = (short)((byteOne << 8 ) + byteTwo);
		return result;
	}
	
	
}
