package instancesOfDB;

import java.util.Objects;

public class Client {

    private String name;
    private String secondName;
    private int phoneNumber;

    public Client() {
    }

    public Client(String name, String secondName, int phoneNumber) {
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getPhoneNumber() == client.getPhoneNumber() &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSecondName(), client.getSecondName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSecondName(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
