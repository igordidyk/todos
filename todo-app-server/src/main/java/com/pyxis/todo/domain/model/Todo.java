package com.pyxis.todo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document("todo")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Todo {

    @Id
    private String id;
    @NotBlank
    @Size(max = 64)
    @Indexed(unique = true)
    private String title;
    private boolean isCompleted = false;
    private Date createAt = new Date();

}
