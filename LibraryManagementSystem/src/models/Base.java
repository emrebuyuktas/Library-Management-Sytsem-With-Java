package models;

public class Base {
	private String nameString;
	private String surnameString;
	private String idString;
	private String emailString;
	public Base(String idString,String nameString, String surnameString, String email) {
		
		this.nameString = nameString;
		this.surnameString = surnameString;
		this.idString = idString;
		this.emailString=email;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getSurnameString() {
		return surnameString;
	}
	public void setSurnameString(String surnameString) {
		this.surnameString = surnameString;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	
	
	

}
