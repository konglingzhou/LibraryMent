package com.cqeec.entity;

public class Reader {
	private String readerid = "";
	private String readername = "";
	private String sex = "";
	private String birthday = "";
	private String telephone = "";
	private String address = "";
	//查询字段
		private String startDate="";//起始日期
		private String endDate="";//结束日期
		public String getReaderid() {
			return readerid;
		}
		public void setReaderid(String readerid) {
			this.readerid = readerid;
		}
		public String getReadername() {
			return readername;
		}
		public void setReadername(String readername) {
			this.readername = readername;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	
}
