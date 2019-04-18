package com.weaver.police;

import com.alibaba.fastjson.JSONObject;
import com.weaver.police.bean.DataLogBean;
import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.bean.UserInfo;
import com.weaver.police.util.DbPoolConnection;
import com.weaver.police.util.EncryptUtil;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/12  16:14
 * @Version 1.0 :
 * @Description :
 **/
public class TestDB {


    @Test
    public void test(){
//        String driver= "dm.jdbc.driver.DmDriver";
//        String url= "jdbc:dm://localhost:5236/DAMENG";
//        String username="SYSDBA";
//        String password="123456789";
//        Connection con = null;
//        try {
//            Class.forName(driver);
//        } catch (java.lang.ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//// 数据库连接
//            con = DriverManager.getConnection(url, username, password);
//
//            Statement st=con.createStatement();
//
//            ResultSet rs=st.executeQuery("select \"ID\",\"NUM_ID\",\"REG_ID\",\"USER_ID\",\"ORGANIZATION\",\"ORGANIZATION_ID\",\"USER_NAME\",\"TERMINAL_ID\",\"OPERATE_TIME\",\"OPERATE_TYPE\",\"OPERATE_RESULT\",\"ERROR_CODE\",\"OPERATE_NAME\",\"OPERATE_CONDITION\",\"OPERATE_NUMBER\",\"OPERATE_TABLE\",\"OPERATE_KEY\",\"INSERT_TIME\",\"COLLECT_TYPE\",\"SENDID\"\n" +
//                    "from \"SYSDBA\".\"OPERATE_LOG\"");
//
//           while (rs.next()){
//               System.out.println(rs.getString("ID"));
//               System.out.println(rs.getString("OPERATE_TIME"));
//           }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//      Connection connection = DBManager.getConnection();
//
//        try {
//            Statement st=connection.createStatement();
//            ResultSet rs=st.executeQuery("select \"ID\",\"NUM_ID\",\"REG_ID\",\"USER_ID\",\"ORGANIZATION\",\"ORGANIZATION_ID\",\"USER_NAME\",\"TERMINAL_ID\",\"OPERATE_TIME\",\"OPERATE_TYPE\",\"OPERATE_RESULT\",\"ERROR_CODE\",\"OPERATE_NAME\",\"OPERATE_CONDITION\",\"OPERATE_NUMBER\",\"OPERATE_TABLE\",\"OPERATE_KEY\",\"INSERT_TIME\",\"COLLECT_TYPE\",\"SENDID\"\n" +
//                    "from \"SYSDBA\".\"OPERATE_LOG\"");
//            while (rs.next()){
//                System.out.println(rs.getString("ID"));
//                System.out.println(rs.getString("OPERATE_TIME"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        OperateLogDao operateLogDao = new OperateLogDaoImpl();
////        operateLogDao.insert(new OperateLogBean());
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("NUM_ID",1);
//     DatabaseHelper.insertEntity("\"SYSDBA\".\"OPERATE_LOG\"",map);
//        String sql = "insert into\"SYSDBA\".\"OPERATE_LOG\"(\"NUM_ID\", \"REG_ID\", \"USER_ID\", \"ORGANIZATION\", \"USER_NAME\", \"TERMINAL_ID\", \"OPERATE_TIME\", \"OPERATE_TYPE\", \"OPERATE_RESULT\")  VALUES(?,?,?,?,?,?,?,?,?)";
//
//        System.out.println(sql);

//        String str = "/ABC/abcjsjdas";
//
//        String key = "a745baf0-424c-45a1-9070-622a6a4c5db6";

//        System.out.println(EncryptUtil.getInstance().DESencode(str,key));
//        System.out.println(EncryptUtil.getInstance().DESdecode("F7C487CFA0AE4FC1999444B46B6FD3D0",key));

        UserInfo userInfo = new UserInfo();
        userInfo.setId("20");
        userInfo.setDepartmentname("IT部");
        userInfo.setLastname("杨柳-汪路军");
        userInfo.setSubcompanyname("东展集团-汪路军");
        userInfo.setCertificatenum("510623199511206521");
        userInfo.setDepartmentcode("110");
        userInfo.setSubcompanycode("110");

        System.out.println(JSONObject.toJSONString(userInfo));
        System.out.println("haha");
        System.out.println("1555487926459");
        System.out.println("1");
        System.out.println("sdasdas");


        System.out.println(EncryptUtil.getInstance().Base64Encode(JSONObject.toJSONString(userInfo)));
        System.out.println(EncryptUtil.getInstance().Base64Encode("haha"));
        System.out.println(EncryptUtil.getInstance().Base64Encode("1555487926459"));
        System.out.println(EncryptUtil.getInstance().Base64Encode("1"));
        System.out.println(EncryptUtil.getInstance().Base64Encode("sdasdas"));
//
//        List<OperateLog> list = new ArrayList<>();
//
//        for(int i = 0;i < 20;i ++){
//            OperateLog operateLog = new OperateLog();
//            operateLog.setOPERATE_TIME("1555487926459");
//            operateLog.setOPERATE_TYPE((short) 1);
//            operateLog.setOPERATE_RESULT("1");
//            operateLog.setTERMINAL_ID("192.168.42.246");
//
//            list.add(operateLog);
//        }
//
//
//        List<InterfaceLog> interfaceLogList = new ArrayList<>();
//
//
//        for(int i = 0;i < 20;i ++){
//            InterfaceLog interfaceLog = new InterfaceLog();
//            interfaceLog.setREQUESTER("ecology");
//            interfaceLog.setINTERFACE_TIME("1555487926459");
//            interfaceLog.setINTERFACE_RESULT("1");
//            interfaceLog.setINTERFACE_CONDITION("name=张三");
//            interfaceLog.setTERMINAL_ID("192.168.42.246");
//            interfaceLogList.add(interfaceLog);
//
//        }
//
//
//        DataLogBean dataLogBean = new DataLogBean();
//        dataLogBean.setUserInfo(JSONObject.toJSONString(userInfo));
//        dataLogBean.setOperateLog(JSONObject.toJSONString(list));
//        dataLogBean.setInterfaceLog(JSONObject.toJSONString(interfaceLogList));

//        Map<String,Object> map = new HashMap<>();
//        map.put("data",dataLogBean);

//        System.out.println(JSONObject.toJSON(dataLogBean).toString());
//
//        System.out.println(EncryptUtil.getInstance().Base64Encode(JSONObject.toJSON(dataLogBean).toString()));




//        System.out.println(JSONObject.toJSONString(userInfo));
//        System.out.println(EncryptUtil.getInstance().DESencode(JSONObject.toJSONString(userInfo),key));
//        System.out.println(EncryptUtil.getInstance().DESencode("1555313732072",key));
//        System.out.println(EncryptUtil.getInstance().DESencode("api/add",key));
//        System.out.println(EncryptUtil.getInstance().DESencode("1",key));
//        System.out.println(EncryptUtil.getInstance().Base64Encode("1"));
//        System.out.println(EncryptUtil.getInstance().DESdecode("0316F512BFA69712",key));
//        System.out.println(EncryptUtil.getInstance().DESencode("192.168.2.112",key));

    }

    @Test
    public void testLoad(){

//        DbPoolConnection.loadPropertyFile("durid.properties");

    }


}
