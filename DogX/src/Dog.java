//nyna2000 Nyamgarig Naranbaatar nyna2000@student.su.se
public class Dog {
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double getTailLength;
    private User user;


    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.getGetTailLength();
    }

    public void increaseAge(){
        this.age++;
        getGetTailLength();
    }

    public String getName(){
        return name;
    }

    public void setUser(User user){
        this.user = user;
    }

    public boolean hasOwner(){
        return user != null;
    }

    public String getOwner(){
        String username = "";
        if(user == null){
            return username;
        }
        return "Owned by: " + user.getName();
    }

    public double getGetTailLength() {
        if(this.breed.equalsIgnoreCase("tax") || this.breed.equalsIgnoreCase("dachshund")){
            this.getTailLength = 3.7;
            return 3.7;
        } else {
            this.getTailLength = (age * weight/10d);
            return (age * weight/10d);
        }
    }

    @Override
    public String toString(){
        return "Name: " + name +
                " Breed: " + breed +
                " Age: " + age +
                " Weight: " + weight +
                " Tail Length: " + getTailLength + " " + getOwner();
    }

    /*@Override
    public int compareTo(Dog sortDog){
        return (this.getGetTailLength() < sortDog.getGetTailLength() ? -1 :
                this.getGetTailLength() == sortDog.getGetTailLength() ? 0 : 1);
    }*/
}
