package src;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return this.row;
    }

    public int getSeat() {
        return this.seat;
    }

    public double getPrice() {
        return this.price;
    }

    public void print() {
        System.out.println("Name : " + this.person.getName());
        System.out.println("Surname : " + this.person.getSurname());
        System.out.println("Email : " + this.person.getEmail());
        System.out.println("Row : " + this.row);
        System.out.println("Seat : " + this.seat);
        System.out.println("Price : $" + this.price);
    }
}
