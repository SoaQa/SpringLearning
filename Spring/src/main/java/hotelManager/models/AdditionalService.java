package hotelManager.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="additional_services")
public class AdditionalService implements Comparable<AdditionalService>{
    @Id
    @SequenceGenerator(name = "addservices_sequence_pk", sequenceName = "addservices_sequence_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addservices_sequence_pk")
    @Column(name = "service_id")
    private long id;

    //@Column(name = "room_id")
    //private long roomId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;



    @Column(name = "service_name")
    private String serviceName;

    @Column(name="price")
    private int serviceCost;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "rent_date")
    private LocalDate rentDate;


    public int compareTo(AdditionalService r) {
        return this.serviceCost - r.serviceCost;
    }

    @Override
    public String toString(){
        return "name= " + serviceName.toString()+ ", cost= " + serviceCost;
    }

    public AdditionalService() {
    }

    public AdditionalService(long id) {
        this.id = id;
    }
}
