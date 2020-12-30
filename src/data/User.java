package data;

public class User {
    private String address;
    private int port;
    private String name;

    public User() {
    }

    public User(String address, int port, String name) {
        this.address = address;
        this.port = port;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
