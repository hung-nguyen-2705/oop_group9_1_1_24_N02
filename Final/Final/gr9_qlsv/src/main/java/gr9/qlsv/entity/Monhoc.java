package gr9.qlsv.entity;

public class Monhoc {
	  	private String id;
	    private String name;
	    private String teacher;
	    private String address;

	    public Monhoc(String id, String name, String teacher, String address) {
	    	this.id = id;
	        this.name = name;
	        this.teacher = teacher;
	        this.address = address;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getTeacher() {
	        return teacher;
	    }

	    public String getAddress() {
	        return address;
	    }
	    
	   
	    public void setTeacher(String teacher) {
	        this.teacher= teacher;
	    }
	    
	    public void setAddress(String address) {
	        this.address= address;
	    }
}
