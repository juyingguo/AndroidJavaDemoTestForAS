package com.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.helper.HttpURLConnectionHelp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Date:2021/6/30,14:21
 * author:jy
 * <p>脚本：用于获取资源</p>
 */
public class ScriptGetNetResource {
    private static String TAG = "ScriptGetNetResource";
    public static String[] animalArray = new String[]{"老虎","狮子","大象"};
    private static String nlpDouDouUrlPrefix = "http://api.doudoubot.cn/rsvpbot/general/chat?appid=rsvpupR18lm6q8i0&token=HD8Mn045gGzZ8foc&userid=123456&question=";
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getResources();
            }
        }).start();
    }

    private static void getResources() {
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<String,String>();
        for(int i=0 ; i <animalArray.length ; i++){
            String url = nlpDouDouUrlPrefix + HttpURLConnectionHelp.encodeStr(animalArray[i]);
            String result = HttpURLConnectionHelp.doGet(url);
            System.out.println("getResources(),animal:" + animalArray[i] + ",result:" + result);

            if (result == null) continue;

            JsonObject outJson = new JsonObject();
            String refactorOutStr = null;
            JsonParser jsonObject = new JsonParser();
            JsonElement parse = jsonObject.parse(result);
            JsonObject asJsonObject = parse.getAsJsonObject();
            int status = asJsonObject.get("status").getAsInt();
            if (status == 0){
                JsonArray stageArray = asJsonObject.getAsJsonArray("stage");
                JsonObject jsonBean = stageArray.get(0).getAsJsonObject();
                if (jsonBean == null) continue;

                if (jsonBean.has("message")){
                    outJson.addProperty("answer",jsonBean.get("message").getAsString());
                }
                if (jsonBean.has("image")){
                    outJson.addProperty("imageUrl",jsonBean.get("image").getAsString());
                }
                if (jsonBean.has("url")){
                    outJson.addProperty("audioUrl",jsonBean.get("url").getAsString());
                }

                refactorOutStr = "..JSON_IBOTN_IFLYTEK_"+ outJson.toString();
                System.out.println("getResources(),animal:" + animalArray[i] + ",refactorOutStr:" + refactorOutStr);
                hashMap.put(animalArray[i],refactorOutStr);
            }
        }

        //////////////////保存数据到excel中///////////////
        createExcelEnter(hashMap);
    }

    private static void createExcelEnter(LinkedHashMap hashMap){

        String fileAbsPath = "D:/动物问答库.xls";
        try {
            File file = new File(fileAbsPath);
            boolean orExistsFile = FileUtils.createOrExistsFile(file);
            System.out.println(TAG + ",createExcelEnter orExistsFile=" + orExistsFile );
            FileOutputStream fos = new FileOutputStream(file);
            try {
                createExcel(fos,hashMap);

            } catch (WriteException | IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void createExcel(OutputStream os,LinkedHashMap hashMap) throws WriteException, IOException {

        System.out.println(TAG + ",createExcel start." );

        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("问答对",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label label0 = new Label(0,0,"主题");
        sheet.addCell(label0);
        Label label1 = new Label(1,0,"问题");
        sheet.addCell(label1);
        Label label2 = new Label(2,0,"答案");
        sheet.addCell(label2);
        Label label3 = new Label(2,0,"情绪");
        sheet.addCell(label3);

        int i = 0;
        String keyName = null;
        String valueName = null;
        for(Object key : hashMap.keySet()){
            i++;
            keyName = key.toString();
            valueName = hashMap.get(keyName).toString();

            System.out.println("key = " + keyName + ", value = " + valueName);

            Label a = new Label(0,i,keyName);
            sheet.addCell(a);
            Label b = new Label(1,i,"我想看" + keyName);
            sheet.addCell(b);
            Label c = new Label(1,i*2,keyName + "长什么样子");
            sheet.addCell(c);
            Label d = new Label(2,i,valueName);
            sheet.addCell(d);
            Label e = new Label(3,i,"默认");
            sheet.addCell(e);
        }


        Label qinghua = new Label(0,1,"清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1,1,"计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2,1,"高");
        sheet.addCell(gao);

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
        System.out.println(TAG + ",createExcel ok." );
    }

}
