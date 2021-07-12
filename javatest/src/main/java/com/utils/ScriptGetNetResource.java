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
    public static String[] animalArray = new String[]{
            ////动物类 、
            "老虎","狮子","大象","狼","老鼠","长颈鹿","大象","貂","猴子","斑马","狗","狐狸","斑马"
            ,"熊","豹子","熊猫","大熊猫","羚羊","驯鹿","考拉","犀牛","袋鼠","穿山甲","河马","猩猩"
            ,"海牛","水獭","海豹","海豚","海象","鸭嘴兽","蝙蝠","","",""
            ,"刺猬","北极熊","鲸鱼","兔子","小白兔","黄鼠狼","","","",""
            ///家禽家畜
            ,"猪","牛","羊","马","骆驼","兔子","猫","狗","驴","骡子","","","",""
            ,"鸡","公鸡","母鸡","小鸡","鸭子","鹅","火鸡","鸽子","鹌鹑","","","",""
            //鱼类
            ,"鲨鱼","章鱼","水獭","鳄鱼","鲈鱼","鲤鱼","金枪鱼","鲟鱼","水獭"
            ///鸟类
            ,"老鹰","鹰","鹅","企鹅","鹦鹉","啄木鸟","鸵鸟","翠鸟","天鹅","蜂鸟","信天翁","鹤","夜莺","海鸥","夜莺"
            ///两栖动物
            ,"海狮","乌龟","蜥蜴","蟾蜍","青蛙","蛇","",""
            ///昆虫  肉足虫 藤壶
            ,"蝴蝶","蜻蜓","蝎子","珊瑚","纤毛虫","绦虫","","吸虫","水蚤","","蟋蟀","蜈蚣","蝗虫","","","","",""
            ///其他动物  草履虫
            ,"蚯蚓","知了","蝉","恐龙","海蜇","海参","海绵","水母","水螅","海星","乌贼","海葵","海胆","","","","",""
    };
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
        long firstTimeMs = System.currentTimeMillis();
        long currTimeMs = 0L;
        for(int i=0 ; i <animalArray.length ; i++){
            currTimeMs = System.currentTimeMillis();

            if (animalArray[i] == null || animalArray[i].trim().equalsIgnoreCase("")) continue;

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
            ///动物名称不同，豆豆返回的json结构不同
            ///狮子,result:{"stage":[{"image":"http://resource.doudoubot.cn/download/image/domain/animal/animal_24.jpg","message":"狮子的体型大，躯体均匀，四肢中长。","url":"http://resource.doudoubot.cn/download/audio/domain/animal/shizi_24.mp3"}],"status":0}
            ///大象,result:{"stage":[{"message":"小i唱一首《大象》，要掌声鼓励哦！"},{"image":"http://resource.doudoubot.cn/download/image/play/play_song.jpg","url":"http://resource.doudoubot.cn/download/audio/play/01-daxiang.mp3"}],"status":0}
            if (status == 0){
                JsonArray stageArray = asJsonObject.getAsJsonArray("stage");
                JsonObject jsonBean = null;
                for(int j = 0;j<stageArray.size();j++){
                    jsonBean = stageArray.get(j).getAsJsonObject();
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
                }
                refactorOutStr = "..JSON_IBOTN_IFLYTEK_"+ outJson.toString();
                System.out.println("getResources(),animal:" + animalArray[i] + ",refactorOutStr:" + refactorOutStr);
                hashMap.put(animalArray[i],refactorOutStr);
            }
            System.out.println("getResources(),consume time:" + (System.currentTimeMillis() - currTimeMs));
        }
        System.out.println("getResources(),current consume time total:" + (System.currentTimeMillis() - firstTimeMs));
        //////////////////保存数据到excel中///////////////
        createExcelEnter(hashMap);

        System.out.println("getResources(),consume time total(contain save to excel):" + (System.currentTimeMillis() - firstTimeMs));
    }

    private static void createExcelEnter(LinkedHashMap hashMap){

        String fileAbsPath = "D:/动物问答库-测试.xls";
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
        Label label3 = new Label(3,0,"情绪");
        sheet.addCell(label3);

        int i = 0;
        String keyName = null;
        String valueName = null;
        for(Object key : hashMap.keySet()){
            i++;
            keyName = key.toString();
            valueName = hashMap.get(keyName).toString();

            System.out.println("key = " + keyName + ", value = " + valueName);

            Label a = new Label(0,i*4-3,keyName);
            sheet.addCell(a);
            Label b = new Label(1,i*4-3,keyName);
            sheet.addCell(b);
            Label b1 = new Label(1,i*4-2,"我想看" + keyName);
            sheet.addCell(b1);
            Label b2 = new Label(1,i*4-1,keyName + "是什么");
            sheet.addCell(b2);
            Label c = new Label(1,i*4,keyName + "长什么样子");
            sheet.addCell(c);
            Label d = new Label(2,i*4-3,valueName);
            sheet.addCell(d);
            Label e = new Label(3,i*4-3,"默认");
            sheet.addCell(e);
        }

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
        System.out.println(TAG + ",createExcel ok." );
    }

}
