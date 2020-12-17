


public class Room {

    int roomId;
    //int roomNr;
    float price;

    public Room(int roomId,  float price) {
        this.roomId = roomId;

        this.price = price;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
