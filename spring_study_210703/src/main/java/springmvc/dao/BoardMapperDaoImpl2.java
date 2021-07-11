package springmvc.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import springmvc.model.Board;
import springmvc.model.BoardListInfo;

public class BoardMapperDaoImpl2 extends SqlSessionDaoSupport implements BoardMapperDao {

	@Override
	public Board findBySeq(long boardSeq) {
		Board board = (Board) getSqlSession().selectOne("springmvc.dao.BoardMapperDao.findBySeq", boardSeq);
		return board;
	}

	@Override
	public List<BoardListInfo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int write(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Board board) {
		return 0;
	}
}
