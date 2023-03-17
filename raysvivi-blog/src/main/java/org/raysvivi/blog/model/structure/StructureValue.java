package org.raysvivi.blog.model.structure;

import lombok.Data;

import java.util.List;

@Data
public class StructureValue {

    private String id;

    private String label;

    private List<StructureValue> children;
}
