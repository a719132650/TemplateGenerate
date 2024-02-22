package com.kigooo.kgs.domain.TemplateGenerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBGenerate {

    private String nameCN;
    private String nameEN;
    List<ColumnGenerate> columnList;
    private List<String> relTo;

}
