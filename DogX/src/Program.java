//nyna2000 Nyamgarig Naranbaatar nyna2000@student.su.se
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Dog> dogArrayList = new ArrayList<>();

    public void initialize() {
        System.out.println("Welcome! ");
    }

    public void runCommandLoop() {
        boolean done;
        do {
            String command = readCommand();
            done = handleCommand(command);
        } while (!done);
    }

    public void closeDown() {
        System.out.println("Thank you, bye! ");
    }

    private String readCommand() {
        System.out.println("Type in command: ");
        String command = input.nextLine().toLowerCase().trim();
        return command;
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "register new dog":
                registerDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "remove dog":
                removeDog();
                break;
            case "increase age":
                incrementAge();
                break;
            case "register new user":
                registerUser();
                break;
            case "list users":
                listUsers();
                break;
            case "remove user":
                removeUser();
                break;
            case "start auction":
                startAuction();
                break;
            case "list auctions":
                listAuctions();
                break;
            case "make bid":
                makeBid();
                break;
            case "list bids":
                listBids();
                break;
            case "close auction":
                closeAuction();
                break;
            case "exit":
                return true;
            default:
                System.out.println("Error! Invalid command! ");
                return false;
        }
        return false;
    }

    private void registerDog() {
        System.out.println("Enter enter dog name: ");
        String dogName = input.nextLine().trim();
        dogName = isEmpty(dogName);

        System.out.println("Enter dog breed: ");
        String dogBreed = input.nextLine().trim();
        dogBreed = isEmpty(dogBreed);

        System.out.println("Enter dog age: ");
        int dogAge = input.nextInt();
        input.nextLine();

        System.out.println("Enter dog weight: ");
        int dogWeight = input.nextInt();
        input.nextLine();

        dogArrayList.add(new Dog(dogName, dogBreed, dogAge, dogWeight));
        System.out.println(dogName + " has been added. ");

    }

    private String isEmpty(String str){
        while(str.isEmpty()){
            System.out.println("Error! Cannot be empty.");
            str = input.nextLine().trim();
        }
        return str;
    }

    private Dog getDog() {
        System.out.println("Enter name of the dog: ");
        String dogName = input.nextLine().trim();
        while (dogName.isEmpty()) {
            System.out.println("Error! Cannot be empty. ");
            dogName = input.nextLine().trim();
        }
        for (Dog dog : dogArrayList) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                return dog;
            }
        }
        System.out.println("Error! no such dog in register. ");
        return null;
    }

    private void incrementAge() {
        if (!dogArrayList.isEmpty()) {
            Dog dog = getDog();
            if (dog == null) {
            } else {
                dog.increaseAge();
                System.out.println(dog.getName() + " is one year older.");
            }
        } else System.out.println("Error! no dogs yet! ");

    }

    private void removeDog() {
        if (!dogArrayList.isEmpty()) {
            Dog dog = getDog();
            if (dog == null) {
            } else {
                dogArrayList.remove(dog);
                removeAuction(dog);
                System.out.println(dog.getName() + " is removed.");
            }
        } else System.out.println("Error! no dogs yet! ");
    }

    private int getTailLength() {
        System.out.println("Enter tail length: ");
        int tailLength = input.nextInt();
        input.nextLine();

        return tailLength;
    }

    private void listDogs() {
        if (!dogArrayList.isEmpty()) {
            int tailLength = getTailLength();
            Collections.sort(dogArrayList, sortTailLength);
            System.out.println("Dogs after sorting:");
            for (Dog dog : dogArrayList) {
                if (dog.getGetTailLength() > tailLength) {
                    System.out.println(dog);
                } else if (tailLength == 0) {
                Collections.sort(dogArrayList, sortTailLength);
                    System.out.println(dogArrayList);
                }
            }
        } else System.out.println("Error! no dogs yet! ");
    }

    private Comparator<Dog> sortTailLength = new Comparator<Dog>() {
        @Override
        public int compare(Dog firstDog, Dog secondDog) {
            if(firstDog.getGetTailLength() == secondDog.getGetTailLength())
                return firstDog.getName().compareTo(secondDog.getName());
            if(firstDog.getGetTailLength() > secondDog.getGetTailLength())
                return 1;
            else return -1;
        }
    };

    ///////////////////////////////////////USER

    private ArrayList<User> userArrayList = new ArrayList<>();

    private User getUser() {
        System.out.println("Enter user name: ");
        String username = input.nextLine().trim();
        username = isEmpty(username);

        for (User user : userArrayList) {
            if (user.getName().equalsIgnoreCase(username)) {
                return user;
            }
        }
        System.out.println("Error! no such user in register. ");
        return null;
    }

    private void registerUser() {
        System.out.println("Enter user name: ");
        String username = input.nextLine().trim();
        username = isEmpty(username);

        userArrayList.add(new User(username));
        System.out.println("Following user has been registered: " + username);
    }

    private void removeUser() {
        if (!userArrayList.isEmpty()) {
            User user = getUser();
            if (user == null) {
                return;
            }
            removeUsersBid(user);
            return;
        }
        System.out.println("Error! no users yet!");
    }

    private void removeUsersBid(User user){
        for(User user1 : userArrayList){
            if(user1.getName().equalsIgnoreCase(user.getName())){
                for(Auction auction : auctionArrayList) {
                    auction.removeUserBid(user.getName());
                }
                dogArrayList.removeAll(user1.getDogs());
                userArrayList.remove(user1);
                System.out.println(user.getName() + " has been terminated. ");
                return;
            }
        }
    }

    private void listUsers() {
        if (!userArrayList.isEmpty()) {
            for (User user : userArrayList) {
                System.out.println(user);
            }
        } else System.out.println("Error! no users yet!");
    }

    ///////////////////////////////////////AUCTION

    private ArrayList<Auction> auctionArrayList = new ArrayList<>();

    private void startAuction() {
        Dog dog = getDog();
        if(dog == null) {
            return;
        }

        for(Auction auction : auctionArrayList) {
            if(auction.getDog().getName().equalsIgnoreCase(dog.getName())) {
                System.out.println("Error! Dog is already up for auction.");
                return;
            }
        }

        boolean hasOwner = belongToSomeone(dog);
        if(hasOwner) {
            System.out.println("Error! Dog already has owner.");
           return;
        }

        addAuction(dog);
    }

    private boolean belongToSomeone(Dog dog) {
        boolean hasOwner = false;
        for(Dog dog1 : dogArrayList) {
            if(dog1.getName().equalsIgnoreCase(dog.getName())) {
                hasOwner = dog1.hasOwner();
            }
        }

        return hasOwner;
    }

    private void addAuction(Dog dog){
        Auction firstAuction = new Auction(dog);

        auctionArrayList.add(firstAuction);

        System.out.println(dog.getName() + " has been put up for auction in auction " + "#" + firstAuction.getId());

    }

    private void listAuctions() {
        if(auctionArrayList.isEmpty()) {
            System.out.println("Error! No auctions yet.");
            return;
        }

        for(int i = 0; i< auctionArrayList.size(); i++) {
            System.out.println(auctionArrayList.get(i));
        }

    }

    private Dog findDog(String name) {
        for(Dog dog : dogArrayList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        System.out.println("Error! no such dog.");
        return null;
    }

    private void closeAuction(){
        Dog dog = getDog();
        if(dog == null) {
            return;
        }

        Auction auction = findAuction(dog);
        if(auction == null){
            System.out.println("Error! This dog is not up for auction.");
            return;
        }

        boolean hasBid = auction.hasBid(dog.getName());

        if(!hasBid){
            System.out.println("The auction is closed. No bids were made for " + dog.getName());
            return;
        }

        Bid bid = auction.getHighestBid();
        User user = bid.getUser();

        user.ownDog(auction.getDog());
        dog.setUser(user);
        System.out.println("The auction is closed. The winning bid was " + bid.getBidAmount() + " and was made by " + user.getName());
        auctionArrayList.remove(auction);

    }

    private Auction findAuction(Dog dog){
        for(Auction auction : auctionArrayList){
            if (auction.getDog().getName().equalsIgnoreCase(dog.getName())){
                return auction;
            }
        }
        return null;
    }

    private void removeAuction(Dog dog){
        for(Auction auction : auctionArrayList) {
            if(auction.getDog().getName().equalsIgnoreCase(dog.getName())){
                auctionArrayList.remove(auction);
            }
        }
    }

    ///////////////////////////////////////BID

    private void makeBid() {
        User user = getUser();
        if(user == null){
            return;
        }

        Dog dog = getDog();
        if(dog == null) {
            return;
        }

        bidChecker(user);

        Auction auction = findAuction(dog);
        if(auction == null){
            System.out.println("Error! Dog not up for auction.");
            return;
        }
        int amount = handleBid(auction);

        addBid(user, auction, amount);

    }

    private void bidChecker(User user){
        for(User user1 : userArrayList) {
            for(Auction auction : auctionArrayList) {
                if (user1.getName().equalsIgnoreCase(user.getName())) {
                    auction.replaceBid(user.getName());
                    break;
                }
            }
        }
    }

    private void addBid(User user, Auction auction, int amount){
        Bid bid = new Bid(user, auction.getDog(), amount);
        auction.addBid(bid);
        System.out.println("Bid successfully made.");
    }

    private int handleBid(Auction auction){
        int minAmount = 1;
        if(auction.getHighestBid() != null){
            minAmount = auction.getHighestBid().getBidAmount();
        }


        System.out.println("Amount to bid atleast " + minAmount);
        int amount = input.nextInt();
        input.nextLine();

        while(amount <= minAmount){
            System.out.println("Error! Amount too low.");
            System.out.println("Amount to bid atleast " + minAmount);
            amount = input.nextInt();
            input.nextLine();
        }

        return amount;
    }

    private void listBids(){
        System.out.println("Enter the name of the dog: ");
        String dog = input.nextLine().trim();
        while(dog.isEmpty()){
            System.out.println("Error! cannot be empty. ");
            dog = input.nextLine().trim();
        }


        for(Auction auction : auctionArrayList){
            if(auction.getDog().getName().equalsIgnoreCase(dog)){
                auction.dogBidList();
                return;
            }
        }
        System.out.println("Error! this dog is not up for auction.");
    }
}
