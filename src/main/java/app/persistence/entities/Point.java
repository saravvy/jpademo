package app.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name="points")
@ToString

public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            int id;
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;

    }
}
