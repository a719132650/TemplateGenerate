package com.kigooo.kgs.service.templateGenerate.impl;

import com.kigooo.kgs.component.kgResponseJson.KgResponseJson;
import com.kigooo.kgs.domain.TemplateGenerate.ColumnGenerate;
import com.kigooo.kgs.domain.TemplateGenerate.DBGenerate;
import com.kigooo.kgs.domain.TemplateGenerate.FullGenerate;
import com.kigooo.kgs.service.templateGenerate.DBCreateGenerateService;
import com.kigooo.kgs.util.KgUtil;
import com.kigooo.kgs.util.TemplateGenerateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBCreateGenerateImpl implements DBCreateGenerateService {

    private static final String TYPE_VARCHAR = "varchar";
    private static final String TYPE_INT = "int";
    private static final String TYPE_BIGINT = "bigint";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_DATE = "date";
    private static final String TEMPLATE_SERVICE_FOLDER = "E://tg/service";
    private static final String TEMPLATE_SERVICE_FOLDER_TEMP = "E://tg/";
    private static final String TEMPLATE_UI_FOLDER = "E://tg/ui";
    private static final String TEMPLATE_UI_FOLDER_TEMP = "E://tg/";

    @Override
    public KgResponseJson doGenerate(FullGenerate fullGenerate){
        KgResponseJson kgResponseJson = new KgResponseJson();
        String UUID = "10001_"+System.currentTimeMillis();
        serviceProjectGenerate(UUID);
        uiProjectGenerate(UUID);
        dbCreateGenerate(UUID,fullGenerate.getDbGenerateList());
        return kgResponseJson;
    }

    private void uiProjectGenerate(String UUID){
        TemplateGenerateUtil.copyFolder(TEMPLATE_UI_FOLDER,TEMPLATE_UI_FOLDER_TEMP+UUID);
    }
    private void serviceProjectGenerate(String UUID){
        TemplateGenerateUtil.copyFolder(TEMPLATE_SERVICE_FOLDER,TEMPLATE_SERVICE_FOLDER_TEMP+UUID);
    }

    private void dbCreateGenerate(String UUID, List<DBGenerate> dbGenerateList){
        String sqlFull = "";
        for(DBGenerate dbGenerate : dbGenerateList){
            String sql = "CREATE TABLE `" + dbGenerate.getNameEN() + "`( `oid` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识', ";
            for(ColumnGenerate column : dbGenerate.getColumnList()){
                sql = sql + "`"+column.getNameEN()+"` "+generateType(column.getType(),column.getLen())+" COMMENT '"+column.getNameCN()+"', ";
            }
            for(String foreign : dbGenerate.getRelTo()){
                sql = sql + "`" + foreign + "_oid` bigint DEFAULT NULL COMMENT '外键', " ;
            }
            sql = sql + "PRIMARY KEY (`oid`) USING BTREE ) ENGINE = InnoDB AUTO_INCREMENT=100000000000 COMMENT '"+dbGenerate.getNameCN()+"';";
            sqlFull = sqlFull + sql + "\n";
        }
        TemplateGenerateUtil.appendToFile(TEMPLATE_SERVICE_FOLDER_TEMP+UUID+"/kgs/sql/cuSql.sql",sqlFull);
    }

    private String generateType(String type,int len){
        String str;
        switch (type){
            case TYPE_INT:
                str = "int DEFAULT NULL";
                break;
            case TYPE_BIGINT:
            case TYPE_DATE:
                str = "bigint DEFAULT NULL";
                break;
            case TYPE_FLOAT:
                str = "float DEFAULT NULL";
                break;
            case TYPE_VARCHAR:
            default:
                str = "varchar("+len+") DEFAULT NULL";
        }
        return str;
    }
}
