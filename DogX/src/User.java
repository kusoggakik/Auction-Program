//nyna2000 Nyamgarig Naranbaatar nyna2000@student.su.se
import java.util.ArrayList;

public class User {
    private String name;

    public User(String name){
        this.name = name;
    }

    private ArrayList<Dog> ownedDogs = new ArrayList<>();

    public void ownDog(Dog dog){
        ownedDogs.add(dog);
    }

    public String dogName(){
        String tempstr = "";
        for(Dog dog : ownedDogs){
            if(dog.getName() != null){
                tempstr = tempstr+dog.getName();
            }
        }
        return tempstr;
    }

    public ArrayList<Dog> getDogs(){
        return ownedDogs;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                ", " + dogName();
    }
}
