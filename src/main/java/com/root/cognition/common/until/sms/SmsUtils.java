package com.root.cognition.common.until.sms;//package com.root.cognition.common.until.sms;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.ivwk.system.domain.SmslogDO;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created on 17/6/7.
// * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
// * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了途邦物流平台-短信产品功能的AK即可)
// * 工程依赖了2个jar包(存放在工程的libs目录下)
// * 1:aliyun-java-sdk-core.jar
// * 2:aliyun-java-sdk-dysmsapi.jar
// *
// * 备注:Demo工程编码采用UTF-8
// * 国际短信发送请勿参照此DEMO
// */
//public class SmsUtils {
//
//
//    /**
//     * 产品名称:途邦物流平台短信API产品,开发者无需替换
//     */
//    static final String product = "Dysmsapi";
//
//    /**
//     * 产品域名,开发者无需替换
//     */
//    static final String domain = "dysmsapi.aliyuncs.com";
//    static final String signName = "途邦物流平台";
//
//    /**
//     * 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
//     */
//    static final String accessKeyId = "LTAIOijW5yWcLeaw";
//    static final String accessKeySecret = "Bdqx27ub1V52yavt3JDpFhX7SiUBB2";
//
//    public static SmslogDO sendSms(String mobile,String module,String templateCode,String [] keyword,String outId) throws ClientException,InterruptedException {
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
//           // SmslogService smslogService= ServiceHelper.getSmslogService();
//            return smslogDO;
//        }
//        return null;
//    }
//    public static SendSmsResponse sendSmsUtils(String mobile,String templateCode,String [] keyword,String outId) throws ClientException {
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
//
//        return sendSmsResponse;
//    }
//    public static SmslogDO sendCode(String mobile,String module,String templateCode,String keyword,String outId) throws ClientException,InterruptedException {
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
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"captcha\":\""+keyword+"\"}");
//
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(outId);
//
//        //hint 此处可能会抛出异常，注意catch
//            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        //查明细
//        Thread.sleep(3000L);
//        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(sendSmsResponse.getBizId(),mobile);
//            System.out.println("短信明细查询接口返回数据----------------");
//            System.out.println("Code=" + querySendDetailsResponse.getCode());
//            System.out.println("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            String sendStatus="";
//            String content="";
//            SmslogDO smslogDO=new SmslogDO();
//            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
//            {
//                sendStatus=Long.toString(smsSendDetailDTO.getSendStatus());
//                smslogDO.setContent(smsSendDetailDTO.getContent());
//                System.out.println("-----content=" + smsSendDetailDTO.getContent());
//                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//            }
//            smslogDO.setModule(module);
//            smslogDO.setMobile(mobile);
//            smslogDO.setCreatedate(new Date());
//            smslogDO.setIspush(sendStatus);
//            smslogDO.setTemplatecode(templateCode);
//            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//           /* SmslogService smslogService= ServiceHelper.getSmslogService();*/
//            return smslogDO;
//        }
//        return null;
//    }
//
//    public static SendSmsResponse sendSms() throws ClientException {
//
//        /*//可自助调整超时时间
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
//        request.setPhoneNumbers("17620020261");
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName("途邦物流平台");
//        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode("SMS_134329076");
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"first\":\"途邦交易平台使用资金账户\", \"remarks\":\"123.99元\"}");
//
//        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
//        //request.setSmsUpExtendCode("90997");
//
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");
//
//        //hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//
//        return sendSmsResponse;*/
//        return null;
//    }
//
//
//    public static QuerySendDetailsResponse querySendDetails(String bizId,String mobile) throws ClientException {
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
//        //组装请求对象
//        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
//        //必填-号码
//        request.setPhoneNumber(mobile);
//        //可选-流水号
//        request.setBizId(bizId);
//        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
//        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
//        request.setSendDate(ft.format(new Date()));
//        //必填-页大小
//        request.setPageSize(10L);
//        //必填-当前页码从1开始计数
//        request.setCurrentPage(1L);
//
//        //hint 此处可能会抛出异常，注意catch
//        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
//
//        return querySendDetailsResponse;
//    }
//
//    public static void main(String[] args) throws ClientException, InterruptedException {
//
//        //发短信
//        //SmslogDO smslogDO = sendCode("17620020261", DataDic.SMSCODE_YZM,"383883","");
//        SendSmsResponse response = sendSms();
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//        //查明细
//        if(response.getCode() != null && response.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId(),"17620020261");
//            System.out.println("短信明细查询接口返回数据----------------");
//            System.out.println("Code=" + querySendDetailsResponse.getCode());
//            System.out.println("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
//            {
//                System.out.println("SmsSendDetailDTO["+i+"]:");
//                System.out.println("Content=" + smsSendDetailDTO.getContent());
//                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
//                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
//                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
//                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
//                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
//                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
//            }
//            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//        }
//
//    }
//}
