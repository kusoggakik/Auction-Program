//nyna2000 Nyamgarig Naranbaatar nyna2000@student.su.se
import java.util.*;

public class Auction {
    private static int counter = 1;
    private Dog dog;
    private int id;
    private Bid bid;


    public Auction(Dog dog){
        this.dog = dog;
        id = counter;
        counter++;
    }

    private ArrayList<Bid> bidArrayList = new ArrayList<>();

    private Bid[] myBidArray = new Bid[3];

    public Dog getDog() {
        return dog;
    }

    public int getId() {
        return id;
    }

    public void removeUserBid(String name){
        for(Bid bid : bidArrayList){
            if(bid.getUser().getName().equalsIgnoreCase(name)){
                bidArrayList.remove(bid);
            }
        }
    }

    public void replaceBid(String name){
        for(Bid bid : bidArrayList){
            if(bid.getUser().getName().equalsIgnoreCase(name)){
                bidArrayList.remove(bid);
                break;
            }
        }
    }

    public String getThreeHighestBid(){
        String slot = " ";
        sortedArray();
        for(int i = 0; i < Math.min(bidArrayList.size(), 3); i++){
            myBidArray[i] = bidArrayList.get(i);
            slot += myBidArray[i] + " ";
        }
        return slot;
    }

    public Bid getHighestBid(){
        if(bidArrayList.isEmpty()){
            return null;
        }
        sortedArray();
        return bidArrayList.get(0);

    }

    public void dogBidList(){
        if(bidArrayList.isEmpty()){
            System.out.println("Error! no bids yet made for this dog.");
            return;
        } else {
            sortedArray();
            System.out.println("Here are the bids for this auction: ");
            for(Bid bid : bidArrayList){
                System.out.println(bid);
            }
        }
    }

    public void sortedArray(){
        for(int i = 1; i< bidArrayList.size(); i++){
            for(int j = i; j > 0 && bidArrayList.get(j).getBidAmount() > bidArrayList.get(j - 1).getBidAmount(); j--){
                Bid bid = bidArrayList.get(j);
                bidArrayList.set(j, bidArrayList.get(j - 1));
                bidArrayList.set(j - 1, bid);
            }
        }
    }

    public void addBid(Bid newBid){
        for (Bid b : bidArrayList){
            if(b.getUser().equals(newBid.getUser())){
                bidArrayList.remove(b);
                break;
            }
        }
        bidArrayList.add(newBid);
    }

    public boolean hasBid(String name){
        boolean has = false;
        for(Bid bid : bidArrayList){
            if(bid.getDog().getName().equalsIgnoreCase(name)){
                has = true;
            }
        }
        return has;
    }

    public Bid getBid() {
        return bid;
    }

    @Override
    public String toString(){
        return "Auction #" + getId() + ": " +
                " Dog Name: " + dog.getName() + ". " +
               " Top bids: " + getThreeHighestBid();
    }
}
