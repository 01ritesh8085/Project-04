package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.AttributeBean;
import com.rays.pro4.Bean.AttributeBean;
import com.rays.pro4.Util.JDBCDataSource;

public class AttributeModel {
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_attribute");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(AttributeBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_attribute values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getdisplayeName());
		pstmt.setString(3, bean.getDataType());
		// pstmt.setDate(4, new java.sql.Date(bean.getTransactionDate().getTime()));
		pstmt.setString(4, bean.getIsActive());
		pstmt.setString(5, bean.getDescription());
		int i = pstmt.executeUpdate();
		System.out.println("Attribute Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(AttributeBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_attribute where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Attribute delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(AttributeBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_attribute set displayeName = ?, dataType = ?, IsActive = ?, description = ?");

		pstmt.setString(1, bean.getdisplayeName());
		pstmt.setString(2, bean.getDataType());
		// pstmt.setDate(3, new java.sql.Date(bean.getTransactionDate().getTime()));
		pstmt.setString(3, bean.getIsActive());
		pstmt.setString(4, bean.getDescription());

		int i = pstmt.executeUpdate();

		System.out.println("Attribute update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public AttributeBean findByPK(long pk) throws Exception {

		String sql = "select * from st_attribute where id = ?";
		AttributeBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new AttributeBean();
			bean.setId(rs.getLong(1));
			bean.setdisplayeName(rs.getString(2));
			bean.setDataType(rs.getString(3));
			bean.setIsActive(rs.getString(4));
			bean.setDescription(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(AttributeBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_attribute where 1=1");
		if (bean != null) {

			if (bean.getdisplayeName() != null && bean.getdisplayeName().length() > 0) {
				sql.append(" AND displayeName like '" + bean.getdisplayeName() + "%'");
			}

			if (bean.getDataType() != null && bean.getDataType().length() > 0) {
				sql.append(" AND dataType like '" + bean.getDataType() + "%'");
			}

			if (bean.getIsActive() != null && bean.getIsActive().length() > 0) {
				sql.append(" AND isActive like '" + bean.getIsActive() + "%'");
			}
			/*
			 * if (bean.getTransactionDate() != null && bean.getTransactionDate().getTime()
			 * > 0) { Date d = new Date(bean.getTransactionDate().getTime());
			 * sql.append(" AND TransactionDate = '" + d + "'"); System.out.println("done");
			 * }
			 */
		}
		if (bean.getId() > 0) {
			sql.append(" AND id = " + bean.getId());

		}

		if (bean.getDescription() != null && bean.getDescription().length() > 0) {
			sql.append(" AND description like '" + bean.getDescription() + "%'");
		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql query search >>= " + sql.toString());
		List list = new ArrayList();

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new AttributeBean();
			bean.setId(rs.getLong(1));
			bean.setdisplayeName(rs.getString(2));
			bean.setDataType(rs.getString(3));
			bean.setIsActive(rs.getString(4));
			bean.setDescription(rs.getString(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_attribute");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			AttributeBean bean = new AttributeBean();
			bean.setId(rs.getLong(1));
			bean.setdisplayeName(rs.getString(2));
			bean.setDataType(rs.getString(3));
			bean.setIsActive(rs.getString(4));
			bean.setDescription(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
