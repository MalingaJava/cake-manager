package com.waracle.cakemgr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@Table(name = "cakes")
@JsonRootName("cakes")
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CakeDTO implements Serializable {
    static final int MAX_NAME_LENGTH = 100, MAX_DESCRIPTION_LENGTH = 100, MAX_IMAGE_URL_LENGTH = 300;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = MAX_NAME_LENGTH)
    @JsonProperty("name")
    private String name;
    @Column(name = "description", nullable = false, length = MAX_DESCRIPTION_LENGTH)
    @JsonProperty("description")
    private String description;
    @Column(name = "image", nullable = false, length = MAX_IMAGE_URL_LENGTH)
    @JsonProperty("image")
    private String imageURL;

    public CakeDTO() {
    }

    public CakeDTO(String name, String description, String imageURL) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

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
