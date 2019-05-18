//nyna2000 Nyamgarig Naranbaatar nyna2000@student.su.se
public class Bid {
    private User user;
    private Dog dog;
    private int bidAmount;

    public Bid(User user, Dog dog, int bidAmount){
        this.user = user;
        this.dog = dog;
        this.bidAmount = bidAmount;
    }

    public User getUser(){
        return user;
    }

    public Dog getDog(){
        return dog;
    }

    public int getBidAmount(){
        return bidAmount;
    }

    @Override
    public String toString(){
        return user.getName() + " " +
                bidAmount + " kr ";
    }
}
