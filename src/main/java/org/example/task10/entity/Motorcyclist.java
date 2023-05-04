package org.example.task10.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "name")
@ToString(exclude = "bikes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "motorcyclist", schema = "myschema")
@Entity
public class Motorcyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "motorcyclist")
    private Set<Bike> bikes = new HashSet<>();

    public void addBike(Bike bike) {
        this.bikes.add(bike);
        bike.setMotorcyclist(this);
    }
}
