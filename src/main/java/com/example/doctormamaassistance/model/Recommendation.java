package com.example.doctormamaassistance.model;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Write JavaDoc please.
 *
 * @author Andrei_Yakushin
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation extends MessageLike {
    @ElementCollection
    @CollectionTable(name ="recommendation_completion")
    private List<Boolean> accomplished = new ArrayList<>();

    public Recommendation init(Integer itemsAmount) {
        accomplished = range(0, itemsAmount).mapToObj(i -> false).collect(toList());
        return this;
    }
}