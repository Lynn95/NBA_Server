package ShareClass;

public class PlayerInfo {
	private String name;			
	private int number;
	private Position position;				//��Ա�ڳ��ϵ�λ��
	private double height;					//��Ա���
	private double weight;					//��Ա����
	private String birth;					//����
	private int age;						
	private int exp;						//���䣬experience����˼
	private String school;					//��ҵѧϰ
	private String teamName;				//�����������
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
	/*������Ϣһ�����������㲻�����ı�*/
}
