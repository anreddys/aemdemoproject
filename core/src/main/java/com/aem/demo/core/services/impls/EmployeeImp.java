package com.aem.demo.core.services.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.demo.core.bean.Employees;
import com.aem.demo.core.dbutils.DatabaseConnectionHelper;
import com.aem.demo.core.service.Employe;

@Component(immediate = true, service = Employe.class)
public class EmployeeImp implements Employe {

	private static Logger log = LoggerFactory.getLogger(EmployeeImp.class);

	@Reference
	private DatabaseConnectionHelper connetionHelper;

	Connection con = null;
	PreparedStatement pstmt = null;

	@Override
	public boolean registerEmployeeDetails(Employees emp) {
		// TODO Auto-generated method stub
		log.info("---registerEmployeeDetails Invoked----");
		String sqlQuery = "insert into employ values(?,?,?,?)";
		boolean flag = false;
		try {
			con = connetionHelper.getDataBaseConnection("datasource");
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, emp.getId());
			pstmt.setString(2, emp.getName());
			pstmt.setString(3, emp.getSalary());
			pstmt.setString(4, emp.getDepart());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				flag = true;

				log.info("Record Inserted Sucessfully");
			} else {
				flag = false;

				log.info("OOPs !! Try Again ");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		log.info("---registerEmployeeDetails End----");

		return flag;
	}

	@Override
	public boolean loginEmploye() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(String id, String name, String salary, String depart) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String sqlQuery = "update employ set salary=? where id=? and name=? and depart=?";
		boolean b = false;
		try {

			con = connetionHelper.getDataBaseConnection("datasource");
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, salary);
			pstmt.setString(4, depart);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				b = true;

				log.info("Record Inserted Sucessfully");
			} else {
				b = false;

				log.info("OOPs !! Try Again ");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return b;
	}

	@Override
	public boolean deleteEmployee(String id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		String sqlQuery = "delete from empploy where id=?";
		boolean b = false;
		try {

			con = connetionHelper.getDataBaseConnection("datasource");
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, id);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				b = true;

				log.info("Record Deleted Sucessfully");
			} else {
				b = false;

				log.info("OOPs !! Try Again ");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return b;

	}

	@Override
	public JSONObject getEmployeDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> getAllEmployees() {
		// TODO Auto-generated method stub
		log.info("---getAllEmployees Invoked----");
		String sqlQuery = "select * from employ";
		boolean flag = false;
		ResultSet rs = null;
		List<Employees> list = null;
		try {

			con = connetionHelper.getDataBaseConnection("datasource");
			pstmt = con.prepareStatement(sqlQuery);

			rs = pstmt.executeQuery();

			list = new ArrayList<Employees>();

			while (rs.next()) {

				Employees emps = new Employees();
				emps.setId(rs.getString(1));
				emps.setName(rs.getString(2));
				emps.setSalary(rs.getString(3));
				emps.setDepart(rs.getString(4));
				list.add(emps);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return list;
	}

}
