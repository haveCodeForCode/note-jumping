package com.root.cognition.modules.config;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.root.cognition.common.until.Query;
import com.root.cognition.modules.entity.Dict;
import com.root.cognition.modules.entity.SmsLog;
import com.root.cognition.modules.service.DictService;
import com.root.cognition.system.config.ApplicationContextRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 阿里大于短信
 *
 * @author Worry
 * @version 2019/6/14
 */
@Service
public class AlibabaSms {

    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    private static final String REGIONID = "cn-hangzhou";

    private static String ACCESS_SERCRET = null;

    private static String ACCESS_KEYID = null;


    private static void setUpalibaba() {
        Map<String, Object> query = Query.withDelFlag();
        query.put("type", "alibaba_sms_data");
        DictService dictService = ApplicationContextRegister.getBean(DictService.class);
        List<Dict> dictList = dictService.list(query);

        for (Dict dict : dictList) {
            if ("accessSecret".equals(dict.getName())) {
                ACCESS_SERCRET = dict.getValue();
            }

            if ("accessKeyId".equals(dict.getName())) {
                ACCESS_KEYID = dict.getValue();
            }
        }
    }

//    public static void main(String[] args) {
//        List<String> mobiles = new ArrayList<>();
//        mobiles.add("17631012827");
//        mobiles.add("15531853734");
//        mobiles.add("13293039481");
//        mobiles.add("17610353986");
//        for (String mobile:mobiles) {
//            String re=  AlibabaSms.sendMesage(mobile, null, "SMS_167971389", null, null);
//            System.err.println(re);
//        }
//    }

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAImACndFVWhD0r", "JqngT3hwiOB16gQbfxIBdnZPGgokrB");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "17631012827");
        request.putQueryParameter("SignName", "知域服务");
        request.putQueryParameter("TemplateCode", "SMS_167974939");
        request.putQueryParameter("TemplateParam", "{\"code\":\"Its0a0werewolf\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }



    public static String sendMesage(String mobile, String SignName, String templateCode, String[] keyword, String outId) {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile(REGIONID, "LTAImACndFVWhD0r", "JqngT3hwiOB16gQbfxIBdnZPGgokrB");
        IAcsClient client = new DefaultAcsClient(profile);
        //组装发送对象
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", templateCode);

        //组装信息
        StringBuilder sendSmsContent = new StringBuilder("{");
        for (int i = 0; i < keyword.length; i++) {
            if (i == 0) {
                sendSmsContent.append("\"first\":\""+keyword[i]+"\"");
            }
        }
        sendSmsContent.append("}");

        request.putQueryParameter("TemplateParam", "{\"name\":\"各位，杰神，老毒莲，老彪，陈聪\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return  response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
            return  "false";
        } catch (ClientException e) {
            e.printStackTrace();
            return  "false";
        }
    }


//
//    public static Smslog sendSms(String mobile,String module,String templateCode,String [] keyword,String outId) throws ClientException,InterruptedException {
//
//        //可自助调整超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        //初始化acsClient,暂不支持region化
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//
//        //组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//        //必填:待发送手机号
//        request.setPhoneNumbers(mobile);
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName(signName);
//        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode(templateCode);
//        StringBuffer sendSmsContent = new StringBuffer("{");//阿里大于短信
//        Map<String, String> mapData = new HashMap<String, String>();
//        for (int i = 0; i < keyword.length; i++) {
//            // /i==0是为first
//            if (i == 0) {
//                sendSmsContent.append("\"first\":\""+keyword[i]+"\"");// 阿里大于短信
//                mapData.put("first", keyword[i]);
//                continue;
//            } else {
//                mapData.put("keyword" + i, keyword[i]);
//                sendSmsContent.append(",\"keyword"+i+"\":\""+keyword[i]+"\"");// 阿里大于短信
//            }
//            //}
//        }
//        sendSmsContent.append("}");
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam(sendSmsContent.toString());
//
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(outId);
//
//        //hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        //查明细
//        Thread.sleep(3000L);
//        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(sendSmsResponse.getBizId(),mobile);
//            System.out.println("短信明细查询接口返回数据----------------");
//            System.out.println("Code=" + querySendDetailsResponse.getCode());
//            System.out.println("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            String sendStatus="";
//            SmslogDO smslogDO=new SmslogDO();
//            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
//            {
//                smslogDO.setContent(smsSendDetailDTO.getContent());
//                sendStatus=Long.toString(smsSendDetailDTO.getSendStatus());
//                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//            }
//
//
//            smslogDO.setMobile(mobile);
//            smslogDO.setCreatedate(new Date());
//            smslogDO.setIspush(sendStatus);
//            smslogDO.setModule(module);
//            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//            // SmslogService smslogService= ServiceHelper.getSmslogService();
//            return smslogDO;
//        }
//        return null;
//    }
//


}
