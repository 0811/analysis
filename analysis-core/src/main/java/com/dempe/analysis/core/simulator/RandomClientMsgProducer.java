package com.dempe.analysis.core.simulator;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class RandomClientMsgProducer {

    public static final String appkey = "b5558eab134cf306fdcdc3a6746afa25";
    public static final String[] appver_arr = {"1.0.0", "1.0.1", "1.0.2", "1.0.3", "1.0.4"};
    public static final String[] apppkg_arr = {"com.sharesdk.analytics1", "com.sharesdk.analytics2",
            "com.sharesdk.analytics3", "com.sharesdk.analytics4", "com.sharesdk.analytics5"};
    public static final String platform_id = "1";
    public static final String[] sdkver_arr = {"1.0", "1.1", "1.2", "1.3", "1.4"};
    public static final String[] channel_name_arr = {"SHARESDK_CHANNEL_A", "SHARESDK_CHANNEL_B", "SHARESDK_CHANNEL_C",
            "SHARESDK_CHANNEL_D", "SHARESDK_CHANNEL_E"};
    public static final String[] mac_arr = {"4c:be:be:f1:4f:42", "5c:be:be:f1:4f:52", "6c:be:be:f1:4f:62",
            "7c:be:be:f1:4f:72", "8c:be:be:f1:4f:82"};
    public static final String[] model_arr = {"MI 1", "MI 1s", "MI 2", "MI 2s", "MI 3"};
    public static final String[] sysver_arr = {"4.1.1", "4.1.3", "4.1.5", "4.1.7", "4.2.1"};
    public static final String[] carrier_arr = {"46000", "46001", "46002", "46003", "46004"};
    public static final String[] screensize_arr = {"640x1136", "740x1336", "840x1536", "940x1736", "1080x1920"};
    public static final String[] factory_arr = {"Vivio", "Jinli", "Huawei", "Sansamg", "Xiaomi"};
    public static final String[] networktype_arr = {"wifi", "3G", "2G", "4G"};
    public static final String[] is_pirate_arr = {"0", "1"};
    public static final String[] longitude_arr = {"111", "222", "333", "444", "555"};
    public static final String[] language_arr = {"zh", "en", "ja", "ko", "ge"};
    public static final String[] timezone_arr = {"+0800", "-0800", "+0700", "-0700", "+0900"};
    public static final String[] cpu_arr = {"ARMv7 Processor rev 8 (v7l)", "ARMv6 Processor rev 8 (v6l)",
            "ARMv5 Processor rev 8 (v5l)", "ARMv4 Processor rev 8 (v4l)", "ARMv3 Processor rev 8 (v3l)"};
    public static final String[] manuid_arr = {"JRO01H", "JRO02H", "JRO03H", "JRO04H", "JRO05H"};
    public static final String[] eventkey_arr = {"btn_click", "play_time", "event_tomato", "event_hashmap",
            "event_traveling", "event_penguin", "event_label_acc_rabit", "event_hashmap_swimming",
            "event_label_acc_cat", "event_hashmap_play"};
    public static final String[] from_page_arr = {"APP_START", "home", "index", "product", "list", "detail", "map"};
    public static final String[] page_arr = {"home", "index", "product", "list", "detail", "map"};
    public static final String[] notice_num_arr = {"1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"};
    public static final String[] label_arr = {"start", "stop", "loading", "playing", ""};
    public static final String[] duration_arr = {"10000", "50000", "100000", "500000", "1000000"};
    public static final String[] error_log_arr = {"123", "234", "345", "456", "567"};
    public static final String[] stack_trace_arr = {"123asd123asd123asd", "234asd234asd234asd", "345asd345asd345asd",
            "456asd456asd456asd", "567asd567asd567asd"};
    public final static String[] testkeys = {"b5558eab134cf306fdcdc3a6746afa25", "ca2bbd6a539ae3a33c5f2832f8baa4ac"};

    public final static String[] deviceIds = {"39huqeKMl0AO", "Oa4mq9WaCAmD", "YBzjfbwgQ5uI", "TcUVvnqNnPRr", "cKATwgNHPX3s",
            "hnVakTsZAtjo", "lgtLKVXSUkFS", "H4BEvO62B2H5", "JV8urBSKPRwv", "AvWpQGipYjXk"};
    public static Random rand = new Random();
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    String[] is_jailbroken_arr = {"0", "1"};
    String is_jailbroken = getRandEle(is_jailbroken_arr);

    public static String getmsg() {
        String appver = getRandEle(appver_arr);
        String is_pirate = getRandEle(is_pirate_arr);
        String networktype = getRandEle(networktype_arr);
        String factory = getRandEle(factory_arr);
        String screensize = getRandEle(screensize_arr);
        String carrier = getRandEle(carrier_arr);
        String sysver = getRandEle(sysver_arr);
        String model = getRandEle(model_arr);
        String mac = getRandEle(mac_arr);
        String channel_name = getRandEle(channel_name_arr);
        String sdkver = getRandEle(sdkver_arr);
        String apppkg = getRandEle(apppkg_arr);
        String longitude = getRandEle(longitude_arr);
        String from_page = getRandEle(from_page_arr);
        String eventkey = getRandEle(eventkey_arr);
        String manuid = getRandEle(manuid_arr);
        String cpu = getRandEle(cpu_arr);
        String timezone = getRandEle(timezone_arr);
        String language = getRandEle(language_arr);
        String manutime = RandDateUtil.getRandomDateString();
        String stack_trace = getRandEle(stack_trace_arr);
        String error_log = getRandEle(error_log_arr);
        String duration = getRandEle(duration_arr);
        String label = getRandEle(label_arr);
        String notice_num = getRandEle(notice_num_arr);
        String page = getRandEle(page_arr);
        // String device_id = randStoreArr[rand.nextInt(randLength)];
        // String device_id = randomString(12);
        String device_id = getRandEle(deviceIds);


        Map<String, String> deviceData = new HashMap<String, String>();
        deviceData.put("device_id", device_id);
        deviceData.put("appver", appver);
        deviceData.put("apppkg", apppkg);
        deviceData.put("platform_id", platform_id);
        deviceData.put("sdkver", sdkver);
        deviceData.put("channel_name", channel_name);
        deviceData.put("mac", mac);
        deviceData.put("model", model);
        deviceData.put("sysver", sysver);
        deviceData.put("carrier", carrier);
        deviceData.put("screensize", screensize);
        deviceData.put("factory", factory);
        deviceData.put("networktype", networktype);
        deviceData.put("is_pirate", is_pirate);
        deviceData.put("longitude", longitude);
        deviceData.put("language", language);
        deviceData.put("timezone", timezone);
        deviceData.put("cpu", cpu);
        deviceData.put("manuid", manuid);
        deviceData.put("manutime", manutime);

        List<Map<String, String>> ld = new ArrayList<Map<String, String>>();
        List<Map<String, String>> ed = new ArrayList<Map<String, String>>();
        List<Map<String, String>> pd = new ArrayList<Map<String, String>>();
        List<Map<String, String>> evtd = new ArrayList<Map<String, String>>();
        List<Map<String, String>> ekvd = new ArrayList<Map<String, String>>();
        List<Map<String, String>> errd = new ArrayList<Map<String, String>>();

        for (int i = 0; i < rand.nextInt(2); i++) {
            Date date = RandDateUtil.getRandomDate();

            String last_end_date = format.format(date.getTime() - 60 * 1000);
            String create_date = format.format(date.getTime());
            String start_date = format.format(date.getTime() + 3 * 1000);
            String end_date = format.format(date.getTime() + 3 * 1000 + rand.nextInt(5) * 1000);
            String session_id = randomString(12);
            Map<String, String> launchMap = new HashMap<String, String>();
            launchMap.put("session_id", session_id);
            launchMap.put("last_end_date", last_end_date);
            launchMap.put("create_date", create_date);
            ld.add(launchMap);

            Map<String, String> exitMap = new HashMap<String, String>();
            exitMap.put("session_id", session_id);
            exitMap.put("create_date", create_date);
            exitMap.put("end_date", end_date);
            ed.add(exitMap);

            Map<String, String> pageMap = new HashMap<String, String>();
            pageMap.put("session_id", session_id);
            pageMap.put("start_date", start_date);
            pageMap.put("end_date", end_date);
            pageMap.put("page", page);
            pageMap.put("from_page", from_page);
            pageMap.put("duration", duration);
            pd.add(pageMap);

            Map<String, String> eventMap = new HashMap<String, String>();
            eventMap.put("session_id", session_id);
            eventMap.put("create_date", create_date);
            eventMap.put("eventkey", eventkey);
            eventMap.put("notice_num", notice_num);
            eventMap.put("page", page);
            eventMap.put("label", label);
            eventMap.put("duration", duration);
            evtd.add(eventMap);

            Map<String, String> eventKVMap = new HashMap<String, String>();
            eventKVMap.put("session_id", session_id);
            eventKVMap.put("create_date", create_date);
            eventKVMap.put("eventkey", eventkey);
            eventKVMap.put("notice_num", notice_num);
            eventKVMap.put("page", page);
            eventKVMap.put("label", label);
            eventKVMap.put("duration", duration);
            eventKVMap.put("key1", "Stringue1");
            eventKVMap.put("key2", "Stringue2");
            eventKVMap.put("key3", "Stringue3");
            eventKVMap.put("key4", "Stringue4");
            eventKVMap.put("key5", "Stringue5");
            eventKVMap.put("key6", "Stringue6");
            eventKVMap.put("key7", "Stringue7");
            eventKVMap.put("key8", "Stringue8");
            eventKVMap.put("key9", "Stringue9");
            eventKVMap.put("key10", "Stringue10");
            ekvd.add(eventKVMap);

            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("session_id", session_id);
            errorMap.put("create_date", create_date);
            errorMap.put("error_log", error_log);
            errorMap.put("stack_trace", stack_trace);
            errorMap.put("page", page);
            errd.add(errorMap);

        }
        JSONObject jo = new JSONObject();
        jo.put("device_data", deviceData);
        jo.put("launch_data", ld);
        jo.put("exit_data", ed);
        jo.put("page_data", pd);
        jo.put("event_data", evtd);
        jo.put("eventkv_data", ekvd);
        jo.put("error_data", errd);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appkey", testkeys[new Random().nextInt(testkeys.length)]);
        jsonObject.put("m", jo);
        //System.out.println(jo.toString());

        //return jsonObject.toJSONString();
        return gizpBase64(jo.toString());


    }

    public static String getRandEle(String[] arr) {
        return arr[rand.nextInt(arr.length)];
    }

    public static void main(String[] args) throws IOException {
        String msg = getmsg();


        String deviceIds = "";
        for (int i = 0; i < 10; i++) {
            deviceIds = deviceIds + randomString(12) + ",";

        }
        System.out.println(deviceIds);

    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
        //return "gaTjM9zrS62h";
    }

    public static String gizpBase64(String str) {
        byte b1[] = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Base64OutputStream b64os = new Base64OutputStream(bos);
            GZIPOutputStream gout = new GZIPOutputStream(b64os);
            gout.write(str.getBytes("UTF-8"));
            gout.close();
            b64os.close();
            b1 = bos.toByteArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new String(b1));

    }

}
