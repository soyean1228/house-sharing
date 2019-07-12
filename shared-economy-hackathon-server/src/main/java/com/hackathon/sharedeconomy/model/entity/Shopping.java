package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Entity
@Table(name = "shopping_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private ForSale forSale;

    @Builder
    public Shopping(User user, ForSale forSale) {
        this.user = user;
        this.forSale = forSale;
    }
}
