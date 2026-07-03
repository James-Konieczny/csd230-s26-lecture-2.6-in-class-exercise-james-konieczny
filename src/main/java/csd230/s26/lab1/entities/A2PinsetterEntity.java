package csd230.s26.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("A2PINSETTER")
public abstract class A2PinsetterEntity extends ProductEntity {
    private String material;
    private String purpose;

    public A2PinsetterEntity() {
        setMaterial("Metal");
        setPurpose("");
    }

    public A2PinsetterEntity(String material, String purpose) {
        this.material = material;
        this.purpose = purpose;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        A2PinsetterEntity that = (A2PinsetterEntity) o;
        return Objects.equals(material, that.material) && Objects.equals(purpose, that.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), material, purpose);
    }

    @Override
    public String toString() {
        return "A2PinsetterEntity{" +
                "material='" + material + '\'' +
                ", price=" + purpose +
                '}' + super.toString();
    }
}