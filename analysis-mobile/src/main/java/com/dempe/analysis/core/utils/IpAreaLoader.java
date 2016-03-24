package com.dempe.analysis.core.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class IpAreaLoader {
    private BufferedWriter outError = null;

    private BufferedWriter outResult = null;

    //这个map需要在系统启动时 放进缓存 功能需要调整一下
    private Map<String, Object> provinceMap = new HashMap<String, Object>();

    private String[][] nation = {{"IANA", "IANA"}, {"亚太地区", "ASIA"},
            {"未分配", "IANA"}, {"未知物理地址", "UNKNOWN"}, {"本机地址", "LOCAL"},
            {"局域网", "LOCAL"}, {"APNIC", "APNIC"}, {"AFRINIC", "AFRINIC"},
            {"联合国", "UN"}, {"阿拉伯联合酋长国", "AE"}, {"澳洲", "AU"},
            {"印尼", "ID"}, {"雅加达", "ID"}, {"欧洲", "EU"}, {"马其顿", "MK"},
            {"塞尔维亚", "YU"}, {"塞尔维亚和黑山", "YU"}, {"安提瓜和巴布达", "AG"},
            {"安提瓜", "AG"}, {"安地瓜", "AG"}, {"荷属安地列斯", "AN"},
            {"梵蒂冈", "VA"}, {"波斯尼亚", "BA"}, {"厄立特里亚", "ER"},
            {"韩国", "KR"},

            {"马耳他", "MT"}, {"科特迪瓦", "CI"}, {"多米尼加", "DO"},
            {"巴勒斯坦", "PS"}, {"柬埔寨", "KH"}, {"文莱", "BN"},
            {"毛里求斯", "MU"}, {"苏丹", "SD"}, {"特立尼达和多巴哥", "TT"},
            {"博茨瓦纳", "BW"}, {"乔治亚", "GS"}, {"罗尼西亚", "FM"},

            {"安道尔", "AD"}, {"阿联酋", "AE"}, {"阿富汗", "AF"},
            {"安提瓜和巴布达", "AG"}, {"安格拉", "AI"}, {"阿尔巴尼亚", "AL"},
            {"亚美尼亚", "AM"}, {"荷兰属地", "AN"}, {"安哥拉", "AO"},
            {"南极洲", "AQ"}, {"阿根廷", "AR"}, {"东萨摩亚", "AS"},
            {"奥地利", "AT"}, {"澳大利亚", "AU"}, {"阿鲁巴", "AW"},
            {"阿塞拜疆", "AZ"}, {"波黑", "BA"}, {"巴巴多斯", "BB"},
            {"孟加拉", "BD"}, {"比利时", "BE"}, {"布基纳法索", "BF"},
            {"保加利亚", "BG"}, {"巴林", "BH"}, {"布隆迪", "BI"}, {"贝宁", "BJ"},
            {"百慕大", "BM"}, {"文莱达鲁萨兰国", "BN"}, {"玻利维亚", "BO"},
            {"巴西", "BR"}, {"巴哈马", "BS"}, {"不丹", "BT"}, {"布韦群岛", "BV"},
            {"伯兹瓦纳", "BW"}, {"白俄罗斯", "BY"}, {"伯利兹", "BZ"},
            {"加拿大", "CA"}, {"科科斯群岛", "CC"}, {"中非", "CF"}, {"刚果", "CG"},
            {"瑞士", "CH"}, {"象牙海岸", "CI"}, {"库克群岛", "CK"}, {"智利", "CL"},
            {"喀麦隆", "CM"}, {"中国", "CN"}, {"哥伦比亚", "CO"},
            {"赤道几内亚", "CQ"}, {"哥斯达黎加", "CR"}, {"古巴", "CU"},
            {"佛得角", "CV"}, {"英属圣诞岛", "CX"}, {"塞浦路斯", "CY"},
            {"捷克", "CZ"}, {"德国", "DE"}, {"吉布提", "DJ"}, {"丹麦", "DK"},
            {"多米尼加联邦", "DM"}, {"多米尼加共和国", "DO"}, {"阿尔及利亚", "DZ"},
            {"厄瓜多尔", "EC"}, {"爱沙尼亚", "EE"}, {"埃及", "EG"},
            {"西萨摩亚", "EH"}, {"西班牙", "ES"}, {"埃塞俄比亚", "ET"},
            {"萨尔瓦多", "EV"}, {"芬兰", "FI"}, {"斐济", "FJ"},
            {"福克兰群岛", "FK"}, {"密克罗尼西亚", "FM"}, {"法罗群岛", "FO"},
            {"法国", "FR"}, {"加蓬", "GA"}, {"大不列颠联合王国", "GB"},
            {"格林纳达", "GD"}, {"格鲁吉亚", "GE"}, {"法属圭亚那", "GF"},
            {"加纳", "GH"}, {"直布罗陀", "GI"}, {"格陵兰群岛", "GL"},
            {"冈比亚", "GM"}, {"几内亚", "GN"}, {"瓜德罗普岛", "GP"},
            {"希腊", "GR"}, {"危地马拉", "GT"}, {"关岛", "GU"},
            {"几内亚比绍", "GW"}, {"圭亚那", "GY"},
            {"赫特与麦克唐纳群岛", "HM"}, {"洪都拉斯", "HN"}, {"克罗地亚", "HR"},
            {"海地", "HT"}, {"匈牙利", "HU"}, {"印度尼西亚", "ID"},
            {"爱尔兰", "IE"}, {"以色列", "IL"}, {"印度", "IN"},
            {"英属印度洋", "IO"}, {"伊拉克", "IQ"}, {"伊朗", "IR"}, {"冰岛", "IS"},
            {"意大利", "IT"}, {"牙买加", "JM"}, {"约旦", "JO"}, {"日本", "JP"},
            {"肯尼亚", "KE"}, {"吉尔吉斯斯坦", "KG"}, {"基里巴斯", "KI"},
            {"科摩罗", "KM"}, {"圣茨和尼维斯", "KN"}, {"北朝鲜", "KP"},
            {"南朝鲜", "KR"}, {"科威特", "KW"}, {"开曼群岛", "KY"},
            {"哈萨克斯坦", "KZ"}, {"老挝", "LA"}, {"黎巴嫩", "LB"},
            {"圣露西亚岛", "LC"}, {"列支敦士登", "LI"}, {"斯里兰卡", "LK"},
            {"利比里亚", "LR"}, {"莱索托", "LS"}, {"立陶宛", "LT"},
            {"卢森堡", "LU"}, {"拉托维亚", "LV"}, {"利比亚", "LY"},
            {"摩洛哥", "MA"}, {"摩纳哥", "MC"}, {"摩尔多瓦", "MD"},
            {"马达加斯加", "MG"}, {"马绍尔群岛", "MH"}, {"马里", "ML"},
            {"缅甸", "MM"}, {"蒙古", "MN"},
            {"北马里亚纳群岛", "MP"}, {"马提尼克岛", "MQ"}, {"毛里塔尼亚", "MR"},
            {"蒙塞拉特岛", "MS"}, {"马尔他", "MT"}, {"马尔代夫", "MV"},
            {"马拉维", "MW"}, {"墨西哥", "MX"}, {"马来西亚", "MY"},
            {"莫桑比克", "MZ"}, {"纳米比亚", "NA"}, {"新喀里多尼亚", "NC"},
            {"尼日尔", "NE"}, {"诺福克岛", "NF"}, {"尼日利亚", "NG"},
            {"尼加拉瓜", "NI"}, {"荷兰", "NL"}, {"挪威", "NO"}, {"尼泊尔", "NP"},
            {"瑙鲁", "NR"}, {"中立区", "NT"}, {"纽埃", "NU"}, {"新西兰", "NZ"},
            {"阿曼", "OM"}, {"巴拿马", "PA"}, {"秘鲁", "PE"},
            {"法属波利尼西亚", "PF"}, {"巴布亚新几内亚", "PG"}, {"菲律宾", "PH"},
            {"巴基斯坦", "PK"}, {"波兰", "PL"}, {"圣皮埃尔和密克隆岛", "PM"},
            {"皮特克恩岛", "PN"}, {"波多黎各", "PR"}, {"葡萄牙", "PT"},
            {"帕劳", "PW"}, {"巴拉圭", "PY"}, {"卡塔尔", "QA"}, {"留尼汪岛", "RE"},
            {"罗马尼亚", "RO"}, {"俄罗斯", "RU"}, {"卢旺达", "RW"},
            {"沙特阿拉伯", "SA"}, {"所罗门群岛", "SB"}, {"塞舌尔", "SC"},
            {"苏旦", "SD"}, {"瑞典", "SE"}, {"新加坡", "SG"}, {"海伦娜", "SH"},
            {"斯洛文尼亚", "SI"}, {"斯马尔巴特和扬马延岛", "SJ"}, {"斯洛伐克", "SK"},
            {"塞拉利昂", "SL"}, {"圣马力诺", "SM"}, {"塞内加尔", "SN"},
            {"索马里", "SO"}, {"苏里南", "SR"}, {"圣多美和普林西比", "ST"},
            {"苏联", "SU"}, {"叙利亚", "SY"}, {"斯威士兰", "SZ"},
            {"特克斯群岛与凯科斯群岛", "TC"}, {"乍得", "TD"}, {"法属南半球领地", "TF"},
            {"多哥", "TG"}, {"泰国", "TH"}, {"塔吉克斯坦", "TJ"},
            {"托克劳群岛", "TK"}, {"土库曼斯坦", "TM"}, {"突尼斯", "TN"},
            {"汤加", "TO"}, {"东帝汶", "TP"}, {"土耳其", "TR"},
            {"特立尼和多巴哥", "TT"}, {"图瓦卢", "TV"},
            {"坦桑尼亚", "TZ"}, {"乌克兰", "UA"}, {"乌干达", "UG"}, {"英国", "UK"},
            {"美国", "US"}, {"乌拉圭", "UY"}, {"乌兹别克斯坦", "UZ"},

            {"梵地冈", "VA"}, {"圣文森特和格林纳丁斯", "VC"}, {"委内瑞拉", "VE"},
            {"维京群岛", "VG"}, {"越南", "VN"}, {"瓦努阿图", "VU"},
            {"瓦利斯和富图纳群岛", "WF"}, {"东萨摩亚", "WS"}, {"也门", "YE"},
            {"南斯拉夫", "YU"}, {"南非", "ZA"}, {"赞比亚", "ZM"}, {"扎伊尔", "ZR"},
            {"津巴布韦", "ZW"}

    };

    private String[][] province = {{"北京", "110000"}, {"天津", "120000"},
            {"河北", "130000"}, {"山西", "140000"}, {"内蒙古", "150000"},
            {"辽宁", "210000"}, {"吉林", "220000"}, {"黑龙江", "230000"},
            {"上海", "310000"}, {"江苏", "320000"}, {"浙江", "330000"},
            {"安徽", "340000"}, {"福建", "350000"}, {"江西", "360000"},
            {"山东", "370000"}, {"河南", "410000"}, {"湖北", "420000"},
            {"湖南", "430000"}, {"广东", "440000"}, {"广西", "450000"},
            {"海南", "460000"}, {"重庆", "500000"}, {"四川", "510000"},
            {"贵州", "520000"}, {"云南", "530000"}, {"西藏", "540000"},
            {"陕西", "610000"}, {"甘肃", "620000"}, {"青海", "630000"},
            {"宁夏", "640000"}, {"新疆", "650000"}, {"澳门", "MO"}, {"香港", "HK"}, {"台湾", "TW"}};


    /**
     * 初始化省份表，路径为本机路径
     */
    public void initProvinceMap() {
        BufferedReader in = null;
        try {
            String id_card_code_path = "classpath:id_card_code.txt";//PropertyUtil.getProperty(QQWry.IP_DB,"id_card_code_path");
            File configPath = ResourceUtils.getFile(id_card_code_path);
            in = new BufferedReader(new InputStreamReader(new FileInputStream(configPath), "GBK"));
            String line = in.readLine();
            int i = 1;
            ArrayList<String[]> list = null;
            while (line != null) {
                String[] n = line.split(",");
                if (n[0].endsWith("0000")) {
                    list = new ArrayList<String[]>();
                    provinceMap.put("CN" + n[0], list);
                }
                list.add(n);
                line = in.readLine();
                i++;
                // if( i>1000 ) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 读入文件进行处理
     *
     * @param fileName
     */
    private void hanleFile(String fileName) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(
                    fileName), "GBK"));
            String line = in.readLine();
            int i = 1;
            // 逐行处理内容
            while (line != null) {
                handleLine(i, line);
                line = in.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 根据ip从QQ纯真ip库获取某条ip范围的信息
     *
     * @param ip
     * @return
     */
    private IpEntry getIpEntry(String ip) {
        QQWry w = new QQWry();
        IpEntry entry = null;
        try {
            entry = w.getIpEntry(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entry;
    }

    /**
     * 处理每一行的内容
     *
     * @param i
     * @param ip
     */
    public IpArea handleLine(int i, String ip) {
        String line = this.getIpEntry(ip).toString();
        /*if(logger.isInfoEnabled())
            logger.info("ip line>>"+line);*/
        IpArea obj = new IpArea();
        try {

            String[] p = preHandle(line).split(",");
            if (p.length < 2) {
                return null;
            }
            obj.setStartIp(new Long(p[0]));
            obj.setEndIp(new Long((p[1])));
            if (p.length == 2) {
                obj.setCountry("");
                obj.setAreaCode("UNKNOWN");
                //saveIpArea(obj);
                return null;
            }
            String local = p[2];
            if (p.length >= 4) {
                local = local + " " + p[3];
            }
            boolean ret = false;

            ret = handleNation(local, obj);
            if (ret) {
                //saveIpArea(obj);
                return obj;
            }
            handleEdu(local, obj);
            handleCompany(local, obj);
            handleOwner(local, obj);

            ret = handleProvince(local, obj);
            if (!ret) {
                String[] n = local.split(" ");
                obj.setCountry(n[0]);
                obj.setAreaCode("UNKNOWN");
                //writeErrorLine(line, obj);
                return obj;
            }
            //saveIpArea(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if(logger.isInfoEnabled())
            logger.info("IpArea >>"+obj );*/
        return obj;
    }


    /**
     * 处理省份
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleProvince(String name, IpArea obj) {
        obj.setCountry("中国");
        obj.setAreaCode("CN");
        boolean ret = handleProvinceUniversity(name, obj);
        if (ret)
            return ret;

        for (int i = 0; i < province.length; i++) {
            if (name.startsWith(province[i][0])) {
                obj.setProvince(province[i][0]);
                obj.setAreaCode("CN" + province[i][1]);
                handleCity(name, obj);
                return true;
            }
        }
        ret = handleCityUniversity(name, obj);
        if (ret)
            return ret;

        return false;
    }

    /**
     * 处理区号
     *
     * @param obj
     * @return
     */
    private boolean handleAreaCode(IpArea obj) {
        for (int i = 0; i < province.length; i++) {
            if (obj.getProvince().startsWith(province[i][0])) {
                obj.setProvince(province[i][0]);
                obj.setAreaCode("CN" + province[i][1]);
                handleCity(obj.getCity() + obj.getDistrict(), obj);
                return true;
            }
        }
        return false;

    }

    /**
     * 处理城市/
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleCityUniversity(String name, IpArea obj) {
        if (!isEdu(name))
            return false;
        for (int i = 0; i < province.length; i++) {
            ArrayList list = (ArrayList) provinceMap.get("CN" + province[i][1]);
            if (list == null) {
                return false;
            }
            for (int j = 0; j < list.size(); j++) {
                String[] area = (String[]) list.get(j);
                if (area.length > 2 && (!StringUtils.isEmpty(area[2]))) {
                    String key = area[2];
                    if (key.endsWith("市"))
                        key = key.replace("市", "");

                    if (name.startsWith(key)) {
                        obj.setProvince(province[i][0]);
                        obj.setCity(area[2]);
                        if (area.length > 3 && !StringUtils.isEmpty(area[3]))
                            obj.setDistrict(area[3]);
                        obj.setAreaCode("CN" + area[0]);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 处理大学
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleProvinceUniversity(String name, IpArea obj) {
        if (name.startsWith("清华大学") || name.startsWith("中央财经大学")
                || name.startsWith("中央民族大学") || name.startsWith("民族大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("海淀区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("对外经济贸易大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("朝阳区");
            handleAreaCode(obj);
            return true;
        }

        if (name.startsWith("北方工业大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("石景山区");
            handleAreaCode(obj);
            return true;
        }

        if (name.startsWith("首都经贸大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("丰台区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("首都师范大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("海淀区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("首都科技大学")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("延庆县");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华北科技学院")) {
            obj.setProvince("北京");
            // obj.setCity( "北京");
            obj.setDistrict("通州");
            handleAreaCode(obj);
            return true;
        }

        if (name.startsWith("西南石油大学")) {
            obj.setProvince("四川");
            obj.setCity("南充");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("西北工业大学")) {
            obj.setProvince("陕西");
            obj.setCity("西安");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("第二炮兵工程学院")) {
            obj.setProvince("陕西");
            obj.setCity("西安");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("黄河科技大学")) {
            obj.setProvince("河南");
            obj.setCity("郑州");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("东华理工大学")) {
            obj.setProvince("江西");
            obj.setCity("抚州");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("中北大学")) {
            obj.setProvince("山西");
            obj.setCity("太原");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("同济大学")) {
            obj.setProvince("上海");
            // obj.setCity( "厦门");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }

        if (name.startsWith("东华大学")) {
            obj.setProvince("上海");
            // obj.setCity( "厦门");
            obj.setDistrict("松江区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华东师范大学")) {
            obj.setProvince("上海");
            // obj.setCity( "厦门");
            obj.setDistrict("闵行区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华东理工大学")) {
            obj.setProvince("上海");
            // obj.setCity( "厦门");
            obj.setDistrict("徐汇区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("中南大学")) {
            obj.setProvince("湖南");
            obj.setCity("长沙");
            // obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("集美大学")) {
            obj.setProvince("福建");
            obj.setCity("厦门");
            obj.setDistrict("集美区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("西华大学")) {
            obj.setProvince("四川");
            obj.setCity("成都");
            obj.setDistrict("高新区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华中科技大学") || name.startsWith("中南财经政法大学")) {
            obj.setProvince("湖北");
            obj.setCity("武汉");
            obj.setDistrict("洪山区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华中农业大学")) {
            obj.setProvince("湖北");
            obj.setCity("武汉");
            // obj.setDistrict("洪山区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("长江大学")) {
            obj.setProvince("湖北");
            obj.setCity("荆州");
            // obj.setDistrict("洪山区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("东南大学")) {
            obj.setProvince("江苏");
            obj.setCity("南京");
            obj.setDistrict("玄武区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("华东交通大学")) {
            obj.setProvince("江西");
            obj.setCity("南昌");
            // obj.setDistrict("海淀区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("南开大学")) {
            obj.setProvince("天津");
            // obj.setCity( "南昌");
            obj.setDistrict("南开区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("东北农业大学")) {
            obj.setProvince("黑龙江");
            obj.setCity("哈尔滨");
            // obj.setDistrict("南开区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("东北林业大学")) {
            obj.setProvince("黑龙江");
            obj.setCity("哈尔滨");
            obj.setDistrict("香坊区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("东北大学")) {
            obj.setProvince("辽宁");
            obj.setCity("沈阳");
            obj.setDistrict("和平区");
            handleAreaCode(obj);
            return true;
        }

        if (name.startsWith("中山大学")) {
            obj.setProvince("广东");
            obj.setCity("广州");
            obj.setDistrict("海珠区");
            handleAreaCode(obj);
            return true;
        }
        if (name.startsWith("暨南大学") || name.startsWith("华南理工大学")
                || name.startsWith("华南农业大学")) {
            obj.setProvince("广东");
            obj.setCity("广州");
            obj.setDistrict("天河区");
            handleAreaCode(obj);
            return true;
        }

        return false;
    }

    /**
     * 处理城市
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleCity(String name, IpArea obj) {
        ArrayList list = (ArrayList) provinceMap.get(obj.getAreaCode());
        if (list == null) {
            obj.setCity(name);
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] area = (String[]) list.get(i);
            boolean ret = false;
            if (area.length > 2 && (!StringUtils.isEmpty(area[2]))
                    && name.indexOf(area[2]) > 0) {
                obj.setCity(area[2]);
                obj.setAreaCode("CN" + area[0]);
                ret = true;
            }
            if (area.length > 3 && (!StringUtils.isEmpty(area[3]))
                    && name.indexOf(area[3]) > 0) {
                if (!StringUtils.isEmpty(area[2]))
                    obj.setCity(area[2]);
                obj.setDistrict(area[3]);
                obj.setAreaCode("CN" + area[0]);
                ret = true;
            }
            if (ret)
                return ret;
        }

        return false;
    }

    /**
     * 处理国家
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleNation(String name, IpArea obj) {

        for (int i = 0; i < nation.length; i++) {
            if (name.startsWith(nation[i][0])) {
                obj.setCountry(nation[i][0]);
                obj.setAreaCode(nation[i][1]);
                String province = "其他";
                if (name.startsWith("中国")) {
                    province = name.replace("中国", "");
                }
                obj.setProvince(province);
                handleEdu(name, obj);
                handleCompany(name, obj);
                handleOwner(name, obj);
                return true;
            }
        }
        return false;
    }

    /**
     * 处理教育机构
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleEdu(String name, IpArea obj) {
        boolean ret = isEdu(name);
        if (!ret)
            return ret;

        String[] n = name.split(" ");
        if (isEdu(n[0])) {
            obj.setEdu(n[0]);
            return true;
        }
        obj.setEdu(name.substring(name.indexOf(" ") + 1));
        return true;
    }

    /**
     * 是否教育机构
     *
     * @param name
     * @return
     */
    private boolean isEdu(String name) {
        String temp = name.toLowerCase();
        int a = temp.indexOf("college");
        int b = temp.indexOf("university");
        int c = temp.indexOf("school");
        int d = temp.indexOf("大学");
        int e = temp.indexOf("学院");
        int f = temp.indexOf("学校");
        int g = temp.indexOf("中学");
        int h = temp.indexOf("小学");
        int i = temp.indexOf("教育网");

        if (a < 0 && b < 0 && c < 0 && d < 0 && e < 0 && f < 0 && g < 0
                && h < 0 && i < 0)
            return false;
        return true;
    }

    /**
     * 处理公司
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleCompany(String name, IpArea obj) {
        String temp = name.toLowerCase();
        int a = temp.indexOf(" inc");
        int b = temp.indexOf("网吧");
        int c = temp.indexOf("网络服务中心");
        int d = temp.indexOf("网络服务部");
        int e = temp.indexOf("公司");
        int f = temp.indexOf(" ltd");
        int g = temp.indexOf(",ltd.");

        if (a < 0 && b < 0 && c < 0 && d < 0 && e < 0 && f < 0 && g < 0)
            return false;

        obj.setCompany(name.substring(name.indexOf(" ") + 1));

        return true;
    }

    /**
     * 处理运营商
     *
     * @param name
     * @param obj
     * @return
     */
    private boolean handleOwner(String name, IpArea obj) {
        String temp = name.toLowerCase().trim();
        int a = temp.indexOf("电信");
        int b = temp.indexOf("网通");
        int c = temp.indexOf("铁通");
        int d = temp.indexOf("长城宽带");
        int e = temp.indexOf("移动");
        int f = temp.indexOf("电讯盈科");
        int g = temp.indexOf("联通");
        int h = temp.indexOf("genuity用户");
        int i = temp.indexOf("att用户");
        int j = temp.indexOf("cable用户");
        int k = temp.indexOf("有线通");
        int l = temp.indexOf("珠江宽频");
        int m = temp.indexOf("教育网");

        if (a < 0 && b < 0 && c < 0 && d < 0 && e < 0 && f < 0 && g < 0
                && h < 0 && i < 0 && j < 0 && k < 0 && l < 0 && m < 0) {
            obj.setOwner("");
            return false;
        }

        if (a > 0)
            obj.setOwner("电信");
        if (b > 0)
            obj.setOwner("网通");
        if (c > 0)
            obj.setOwner("铁通");
        if (d > 0)
            obj.setOwner("长城宽带");
        if (e > 0)
            obj.setOwner("移动");
        if (f > 0)
            obj.setOwner("电讯盈科");
        if (g > 0)
            obj.setOwner("联通");
        if (h > 0)
            obj.setOwner("Genuity");
        if (i > 0)
            obj.setOwner("ATT");
        if (j > 0)
            obj.setOwner("Cable");
        if (k > 0)
            obj.setOwner("有线通");
        if (l > 0)
            obj.setOwner("珠江宽频");
        if (l > 0)
            obj.setOwner("教育网");
        return true;
    }

    /**
     * 预处理错误的内容
     *
     * @param line
     * @return
     */
    private String preHandle(String line) {
        String ret = line.trim();
        ret = ret.replace("CZ88.NET", "");
        ret = ret.replace("内蒙包头市", "内蒙古包头市");
        ret = ret.replace("广州市深圳市", "广东省深圳市");
        ret = ret.replace("温州市温州市", "浙江省温州市");
        ret = ret.replace("中华人民共和国", "中国");
        ret = ret.replace("全国通用", "中国");
        ret = ret.replace("解放军 重庆通信学院", "重庆 重庆通信学院");
        ret = ret.replace("湖被省", "湖北省");
        ret = ret.replace("罗马利亚", "罗马尼亚");
        ret = ret.replace("陝西省", "陕西省");
        ret = ret.replace("安微省", "安徽省");
        ret = ret.replace("华北地区", "北京 华北地区");
        ret = ret.replace("IBM中国公司", "北京 IBM中国公司");

        ret = ret.replace("华东地区", "上海 华北地区");
        ret = ret.replace("中经网", "中国 中经网");
        ret = ret.replace("艾提宽带(北京/上海)", "中国 艾提宽带(北京/上海)");

        ret = ret.replace("聚友网络 河南", "中国 聚友网络 河南");
        ret = ret.replace("塞黑 /阿尔巴尼亚", "阿尔巴尼亚 塞黑");

        ret = ret.replace("苏格兰", "英国 苏格兰");
        ret = ret.replace("英格兰", "英国 英格兰");

        ret = ret.replace("土尔其", "土耳其");
        ret = ret.replace("REPUBLIC OF KOREA", "韩国");

        ret = ret.replace("乌苏里", "俄罗斯 乌苏里");
        ret = ret.replace("圣地亚哥", "智利 圣地亚哥");
        ret = ret.replace("ARIN", "美国 ARIN");
        ret = ret.replace(",20四川省南充市南部县 彩虹网吧", ",四川省南充市南部县 彩虹网吧");
        ret = ret.replace("拉脱维亚", "拉托维亚");
        ret = ret.replace("柬埔塞", "柬埔寨");

        ret = ret.replace("黑龙工省", "黑龙江省");
        ret = ret.replace("奥大利亚", "澳大利亚");
        ret = ret.replace("雅虎", "美国 雅虎");
        ret = ret.replace("Yahoo", "美国 雅虎");
        ret = ret.replace("第二炮兵 工程学院", "第二炮兵工程学院");

        if (ret.endsWith("圣多美和普林"))
            ret = ret.replace("圣多美和普林", "圣多美和普林西比");
        if (ret.endsWith("Microsoft 公司"))
            ret = ret.replace("Microsoft 公司", "美国 Microsoft 公司");
        if (ret.endsWith("Microsoft 日本公司"))
            ret = ret.replace("Microsoft 日本公司", "日本 Microsoft 日本公司");

        if (ret.endsWith("法属玻里尼西"))
            ret = ret.replace("法属玻里尼西", "法属波利尼西亚");
        if (ret.endsWith("法属玻利尼西"))
            ret = ret.replace("法属玻利尼西", "法属波利尼西亚");
        if (ret.endsWith("法属玻利尼西亚"))
            ret = ret.replace("法属玻利尼西亚", "法属波利尼西亚");

        if (ret.endsWith("联通 CDMA"))
            ret = ret.replace("联通 CDMA", "中国 联通 CDMA");
        if (ret.endsWith("联通"))
            ret = ret.replace("联通", "中国 联通");
        if (ret.endsWith("新浪网"))
            ret = ret.replace("新浪网", "北京 新浪网");
        if (ret.endsWith("CNNIC"))
            ret = ret.replace("CNNIC", "中国 CNNIC");
        if (ret.endsWith("浪点网吧 彬彬网吧"))
            ret = ret.replace("浪点网吧 彬彬网吧", "中国 浪点网吧 彬彬网吧");
        if (ret.endsWith("长城宽带"))
            ret = ret.replace("长城宽带", "中国 长城宽带");
        if (ret.endsWith("安提瓜和巴布"))
            ret = ret.replace("安提瓜和巴布", "安提瓜和巴布达");
        if (ret.endsWith("荷属安的列斯"))
            ret = ret.replace("荷属安的列斯", "荷属安地列斯");

        int index = ret.indexOf(";");
        if (index > 0)
            ret = ret.substring(0, index);
        return ret;
    }

    /**
     * 将IP地址转变成数字
     *
     * @param ip
     * @return
     */
    public static long valueOfIpv4(String ip) {
        long ret = 0;
        String[] p = ip.split("\\.");
        ret += Long.parseLong(p[0]) << 24;
        ret += Long.parseLong(p[1]) << 16;
        ret += Long.parseLong(p[2]) << 8;
        ret += Long.parseLong(p[3]);
        return ret;
    }

    /**
     * 将数字转变成IP地址
     *
     * @param ip
     * @return
     */
    public static String ipv4(long ip) {
        StringBuffer ret = new StringBuffer();
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip);
        return ret.toString();
    }


}