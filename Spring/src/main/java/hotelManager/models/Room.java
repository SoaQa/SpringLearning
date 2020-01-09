package hotelManager.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "rooms")
public class Room implements Comparable<Room> {
    @Id
    @SequenceGenerator(name = "rooms_sequence_pk", sequenceName = "rooms_sequence_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_sequence_pk")
    @Column(name = "room_id")
    private long id;

    //@Column(name = "hotel_id")
    //private long hotelId;

    @Column(name = "room_num")
    private int nRoom;

    @Column(name = "price")
    private int costPerDay;

    //@org.hibernate.annotations.Type(type="yes_no")
    @Column(name = "is_free")
    private boolean isFree = true;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "rent_date")
    private LocalDate rentDate;

    //@org.hibernate.annotations.Type(type="yes_no")
    @Column(name = "is_restore")
    private boolean isRestore;


    @Column(name = "max_occupancy")
    private int maximumOccupancy;

    @Column(name = "stars")
    private int starsCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<HotelClient> historyClients = new ArrayList<HotelClient>();

    @OneToOne
    @JoinColumn(name="client_id", unique = true)
    private HotelClient client;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="hotel_id", nullable = false)
    private Hotel hotel;


    public Room() {
    }

    public Room(int id) {
        this.id = id;
    }

    public int compareTo(Room r) {
        return this.nRoom - r.getNRoom();
    }

}
