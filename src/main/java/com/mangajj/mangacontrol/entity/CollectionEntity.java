package com.mangajj.mangacontrol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Collection")
public class CollectionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @org.hibernate.annotations.Type(type="uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private UserEntity owner;

    @ManyToOne
    private MangaEntity manga;

    @OneToMany(mappedBy = "collection")
    private List<VolumeEntity> volumes;
}
