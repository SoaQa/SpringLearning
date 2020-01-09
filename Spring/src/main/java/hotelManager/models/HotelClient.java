package hotelManager.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Setter
@Getter
@JsonAutoDetect
@Entity
@Table(name="hotel_clients")
public class HotelClient{
    @Id
    @SequenceGenerator(name = "clients_sequence_pk", sequenceName = "clients_sequence_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_sequence_pk")
    @Column(name = "client_id")
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String surname;

    @Column
    private int age;

    public HotelClient() {
    }

    public HotelClient(long id) {
        this.id = id;
    }

}
