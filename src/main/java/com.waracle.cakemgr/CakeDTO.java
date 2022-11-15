package com.waracle.cakemgr;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "cakes")
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CakeDTO {

    static final int MAX_NAME_LENGTH = 100, MAX_DESCRIPTION_LENGTH = 100, MAX_IMAGE_URL_LENGTH = 300;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = MAX_NAME_LENGTH)
    private String name;
    @Column(name = "description", nullable = false, length = MAX_DESCRIPTION_LENGTH)
    private String description;
    @Column(name = "image", nullable = false, length = MAX_IMAGE_URL_LENGTH)
    private String imageURL;

//    public CakeDTO(String name, String description, String imageURL) {
//        isTrue(notEmpty(name).length() <= MAX_NAME_LENGTH);
//        isTrue(notEmpty(description).length() <= MAX_DESCRIPTION_LENGTH);
//        isTrue(notEmpty(imageURL).length() <= MAX_IMAGE_URL_LENGTH);
//
//        this.name = name;
//        this.description = description;
//        this.imageURL = imageURL;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CakeDTO cakeDTO = (CakeDTO) o;
        return id.equals(cakeDTO.id) && name.equals(cakeDTO.name)
                && description.equals(cakeDTO.description) && imageURL.equals(cakeDTO.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imageURL);
    }
}
