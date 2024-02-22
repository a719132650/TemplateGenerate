package com.kigooo.kgs.domain.TemplateGenerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullGenerate {

    private List<DBGenerate> dbGenerateList;

}
