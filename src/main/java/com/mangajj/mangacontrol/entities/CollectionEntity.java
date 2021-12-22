package com.mangajj.mangacontrol.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class CollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID id;

    private String name;

    private String title;

}
