package backend.demo.Model;

import backend.demo.DTO.productDTO;
import java.util.Objects;

public class paymentMethod {
   private String name;
   private int number;

    public paymentMethod() {
    }

    public paymentMethod(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public paymentMethod name(String name) {
        setName(name);
        return this;
    }

    public paymentMethod number(int number) {
        setNumber(number);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof paymentMethod)) {
            return false;
        }
        paymentMethod paymentMethod = (paymentMethod) o;
        return Objects.equals(name, paymentMethod.name) && number == paymentMethod.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", number='" + getNumber() + "'" +
            "}";
    }
        
}
