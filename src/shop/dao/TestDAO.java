package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import shop.dao.impl.DAO;
import shop.db.Database;
import shop.spring.model.TestModel;

public class TestDAO implements DAO<TestModel> {
	
	Database db;
	
	public TestDAO(Database db) {
		this.db = db;
	}
	
	@Override
	public int Add(TestModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(TestModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Update(TestModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TestModel> FindAll() {
		String sql = "SELECT * FROM test ORDER BY ID ASC";
		ArrayList<HashMap<String, Object>> queryList = db.queryList(sql);
		ArrayList<TestModel> memberList = new ArrayList<TestModel>();
		for (Iterator<HashMap<String, Object>> iterator = queryList.iterator(); iterator.hasNext();) {
			HashMap<String, Object> next = iterator.next();
			TestModel member = MappingBeans(next);
			memberList.add(member);
		}
		return memberList;
	}

	@Override
	public TestModel FindByID(TestModel bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestModel FindByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestModel MappingBeans(HashMap<String, Object> map) {
		TestModel testModel = new TestModel();
		
		testModel.setId(Integer.parseInt(map.get("ID").toString()));
		testModel.setName(map.get("NAME").toString());
		testModel.setSurname(map.get("SURNAME").toString());
		return testModel;
	}
	
}
