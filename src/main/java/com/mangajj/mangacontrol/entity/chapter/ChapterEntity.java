package com.mangajj.mangacontrol.entity.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Chapter")
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private String number;

    @Column(name = "release_date", nullable = false)
    private LocalDateTime releaseDate;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MangaPageEntity> pages;

}
