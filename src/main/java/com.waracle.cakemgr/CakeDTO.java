package com.waracle.cakemgr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 40)
    @JsonProperty("name")
    @NotNull
    @Size (min = 2, max = 40)
    private String name;
    @Column(name = "description", nullable = false, length = 100)
    @JsonProperty("description")
    @NotNull
    @Size (min = 2, max = 100)
    private String description;
    @Column(name = "image", nullable = false, length = 100)
    @JsonProperty("image")
    @NotNull
    @Size (min = 2, max = 100)
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
