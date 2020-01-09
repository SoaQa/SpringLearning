package hotelManager.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @SequenceGenerator(name = "hotels_sequence_pk", sequenceName = "hotels_sequence_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotels_sequence_pk")
    @Column(name = "hotel_id")
    private long id;

    @Column(name = "hotel_name")
    private String hotelName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Room> rooms;


}
