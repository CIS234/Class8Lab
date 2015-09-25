package cis232.lab;

public class Student implements Comparable<Student>{
	String name;
	int points;
	String present = null;
	
	public Student(String name, int points){
		this.name = name;
		this.points = points;
	}
	
	public void setPresent(String present){
		this.present = present;
	}
	
	public boolean isPresent(){
		return present == null || !present.equalsIgnoreCase("n");
	}
	
	public void addPoint(){
		points++;
	}
	
	public String toString(){
		return name;
	}
	
	public String toCsvString(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s,%d", name, points));
		if(present != null){
			sb.append(String.format(",%s", present));
		}
		return sb.toString();
	}
	
	public int getPoints(){
		return points;
	}


	/**
	 * Compares this student object to another student object to sort highest points to lowest.
	 * @return -1 if this student has more points, 1 if this student has
	 * fewer points, or 0 if they have the same number of points.
	 */
	@Override
	public int compareTo(Student other) {
		if(this.points < other.points){
			return 1;
		} else if(this.points > other.points){
			return - 1;
		} else {
			return 0;
		}
	}
}
