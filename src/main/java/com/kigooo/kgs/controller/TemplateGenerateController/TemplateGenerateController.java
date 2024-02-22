package com.kigooo.kgs.controller.TemplateGenerateController;

import com.kigooo.kgs.component.kgResponseJson.KgResponseJson;
import com.kigooo.kgs.domain.TemplateGenerate.DBGenerate;
import com.kigooo.kgs.domain.TemplateGenerate.FullGenerate;
import com.kigooo.kgs.service.templateGenerate.DBCreateGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("tg")
public class TemplateGenerateController {

    @Autowired
    private DBCreateGenerateService dbCreateGenerateService;

//    @RequestMapping("/dbCreateGenerate")
//    @ResponseBody
//    public KgResponseJson dbCreateGenerate(@RequestBody DBGenerate dbGenerate){
//        return dbCreateGenerateService.dbCreateGenerate(dbGenerate);
//    }

    @RequestMapping("/doGenerate")
    @ResponseBody
    public KgResponseJson doGenerate(@RequestBody FullGenerate fullGenerate){
        return dbCreateGenerateService.doGenerate(fullGenerate);
    }

}
