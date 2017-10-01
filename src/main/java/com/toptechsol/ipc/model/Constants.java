package com.toptechsol.ipc.model;

public class Constants {
	public enum Period {
		THREE_MONTHS(0, "3 Months"), SIX_MONTHS(1, "6 Months"), ONE_YEAR(2, "1 Year"), THREE_YEAR(3,"3 Years"), FIVE_YEARS_YEAR(4, "5 Years"), NOT_DEFINED(4, "Not defined") ;
		private int value;
		private String name;
 
		private Period(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}

	
	public enum DocumentType {
		CERTIFICATE(0, "Certificate"), MAINTENANCE(1, "Maintenance"), Extra(2, "Extra") ;
		private int value;
		private String name;
 
		private DocumentType(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
}
