package com.mangajj.mangacontrol.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COLLECTION")
public class CollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36)
    private UUID id;

    private String name;

    @ManyToOne()
    private UserEntity owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "manga_collection",
            joinColumns = @JoinColumn(name = "id_collection", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_manga", referencedColumnName = "id"))
    @JsonManagedReference
    private List<MangaEntity> mangas;

    public List<MangaEntity> getMangas() {
        return mangas;
    }
}
