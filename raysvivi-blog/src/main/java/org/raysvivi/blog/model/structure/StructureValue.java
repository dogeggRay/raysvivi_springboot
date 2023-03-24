package org.raysvivi.blog.model.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StructureValue {
    @JsonProperty("id")
    private String id;

    @JsonProperty("label")
    private String label;

    @JsonProperty("children")
    private List<StructureValue> children;

    private String pid;
}
