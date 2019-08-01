package com.hisd3.hismk2

import com.hisd3.hismk2.security.SecurePasswordEncoder

class PasswordEncoderApp {
	
	static void main(String[] args) {
		
		println("Enter desired password")
		def password = System.in.newReader().readLine()
		
		SecurePasswordEncoder encoder = new SecurePasswordEncoder()
		
		def encoded = encoder.encode(password)
		
		println("Password is $encoded")
		
	}
}
