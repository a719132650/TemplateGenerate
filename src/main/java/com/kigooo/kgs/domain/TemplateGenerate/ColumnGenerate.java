package com.kigooo.kgs.domain.TemplateGenerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnGenerate {

    private String nameCN;
    private String nameEN;
    private int len;
    private String type;

}
