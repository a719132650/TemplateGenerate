package com.kigooo.kgs.service.templateGenerate;

import com.kigooo.kgs.component.kgResponseJson.KgResponseJson;
import com.kigooo.kgs.domain.TemplateGenerate.DBGenerate;
import com.kigooo.kgs.domain.TemplateGenerate.FullGenerate;

public interface DBCreateGenerateService {

    KgResponseJson doGenerate(FullGenerate fullGenerate);

}
