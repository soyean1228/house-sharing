package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Entity
@Table(name = "image_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private ForSale forSale;

    @Builder
    public Image(String path, ForSale forSale) {
        this.path = path;
        this.forSale = forSale;
    }

    public void updatePath(String path) {
        this.path = path;
    }
}
