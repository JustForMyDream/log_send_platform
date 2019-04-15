package com.weaver.police;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLogBean;
import com.weaver.police.dao.OperateLogDao;
import com.weaver.police.dao.OperateLogMapper;
import com.weaver.police.dao.impl.OperateLogDaoImpl;
import com.weaver.police.util.DBManager;
import com.weaver.police.util.DatabaseHelper;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
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
//       DatabaseHelper.insertEntity("\"SYSDBA\".\"OPERATE_LOG\"",map);
        String sql = "insert into\"SYSDBA\".\"OPERATE_LOG\"(\"NUM_ID\", \"REG_ID\", \"USER_ID\", \"ORGANIZATION\", \"USER_NAME\", \"TERMINAL_ID\", \"OPERATE_TIME\", \"OPERATE_TYPE\", \"OPERATE_RESULT\")  VALUES(?,?,?,?,?,?,?,?,?)";

        System.out.println(sql);
    }


}
