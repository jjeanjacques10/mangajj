package com.mangajj.mangacontrol.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MANGA")
public class MangaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String status;

    @Column(columnDefinition = "LONGTEXT")
    private String synopsis;

    @ManyToMany(mappedBy = "mangas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<CollectionEntity> collection;
}
