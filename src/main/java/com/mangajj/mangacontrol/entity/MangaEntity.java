package com.mangajj.mangacontrol.entity;

import com.mangajj.mangacontrol.entity.chapter.ChapterEntity;
import lombok.*;
import org.hibernate.annotations.Cache;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MANGA")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class MangaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Column(unique = true)
    private String title;
    private String status;
    private int volumes;
    private int chaptersNumber;
    private int popularity;
    private ArrayList<String> genres;

    @JsonProperty("image_url")
    @Column(name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters;

    @JsonBackReference
    @ManyToMany(mappedBy = "mangas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CollectionEntity> collection;

}
