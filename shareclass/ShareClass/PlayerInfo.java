package ShareClass;

public class PlayerInfo {
	private String name;			
	private int number;
	private Position position;				//球员在场上的位置
	private double height;					//球员身高
	private double weight;					//球员体重
	private String birth;					//生日
	private int age;						
	private int exp;						//球龄，experience的意思
	private String school;					//毕业学习
	private String teamName;				//所属球队名称
	public PlayerInfo(String name,int num,Position p,double height,
			double weight,int age ,int exp,String birth
			,String school){
		this.name=name;
		this.number=num;
		this.position=p;
		this.height=height;
		this.weight=weight;
		this.age=age;
		this.exp=exp;
		this.birth=birth;
		this.school=school;
		
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public Position getPosition() {
		return position;
	}
	public double getHeight() {
		return height;
	}
	public double getWeight() {
		return weight;
	}
	public String getBirth() {
		return birth;
	}
	public int getAge() {
		return age;
	}
	public int getExp() {
		return exp;
	}
	public String getSchool() {
		return school;
	}
	public String getTeamName() {
		return teamName;
	}
	/*基本信息一旦定下来，便不发生改变*/
}
