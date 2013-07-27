package com.brtracker.shared.utils;

public class EnforaPrint2 {

	public static void main(String[] args) {

		short[] buf7 = {
				0x0, 0x5, 0x2, 0x0, 
				0x0, 0x0, 0x9, 0xffffffc4, 
				0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 
				0x20, 0x20, 0x20, 0x53, 0x42, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x20, 
				0x3f, 0x18, 
				0x43, 
				0x1, 0x3a, 0x3f, 
				0x1, 
				0x38, 0xffffffd1, 0x3e, 
				0xffffffff, 0x45, 0xffffffce, 0x10, 
				
				0x0, 0x0, 0x0, 0x72, 0x2, 0xffffffaa, 0xffffffeb, 0x0, 0x0, 0x15, 0x6, 0x0, 0x24, 0x4a, 0x7d, 0xc, 0x5, 0x8, 0x11, 0x30, 0x1a, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x7, 0x0, 0x0, 0x0, 0x51, 0x9, 0x0	
		};

		short[] buf8 = {
				0x0, 0x5, 0x2, 0x0, 
				0x0, 0x0, 0x9, 0xffffffc4, 
				0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 
				0x20, 0x20, 0x20, 0x53, 0x42, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x20, 
				0x3f, 0x8, 
				0x43, 
				0x1, 0x3f, 0xffffffa0, 
				0x1, 
				0x38, 0xffffffef, 0x3f, 
				0xffffffff, 0x46, 0x7f, 0x3f, 
				
				0x0, 0x0, 0x0, 0x0, 0x0, 0xffffffd7, 0x58, 0x0, 0x0, 0x0, 0x9, 0x0, 0x25, 0xffffffd6, 0x73, 0xc, 0x5, 0xa, 0x5, 0x33, 0x1c, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x7, 0x0, 0x0, 0x0, 0x51, 0x18, 0x0	
		};

		System.out.println(dumpLat(new short[] { 0x31, 0xBD, 0xA8 }) + 
				"," + dumpLng(new short[] { 0xFF, 0x6C, 0xDC, 0x96 }));

		System.out.println(dumpLat(new short[] { 0x38, 0xffffffef, 0x3f }) + 
				"," + dumpLng(new short[] { 0xffffffff, 0x46, 0x7f, 0x3f }));
	}
	
	public static double dumpLat(short[] tbuf) {
		
		short pad = 0x00;
		if ((tbuf[2] & 0x80) == 1) {
			pad = 0xFF;
		}
		short[] buf = new short[] {pad, tbuf[0], tbuf[1], tbuf[2]};
		
		int value = 0;
		for (int i=0; i<buf.length; i++) {
			value = value << 8 | (buf[i] & 0xff);
		}
		int sign = value < 0 ? -1:1;
		double a = Math.abs(value);
		double degrees = Math.floor(a/100000.0);
		double minutes = (a - (degrees*100000.0))/1000.0; 
		
		double lat = degrees + (minutes/60.0);
		
		return sign*lat;
	}

	public static double dumpLng(short[] buf) {
		
		int value = 0;
		for (int i=0; i<buf.length; i++) {
			value = value << 8 | (buf[i] & 0xff);
		}
		int sign = value < 0 ? -1:1;
		double a = Math.abs(value);
		double degrees = Math.floor(a/100000.0);
		double minutes = (a - (degrees*100000.0))/1000.0; 
		
		double lat = degrees + (minutes/60.0);
		
		return sign*lat;
	}

}