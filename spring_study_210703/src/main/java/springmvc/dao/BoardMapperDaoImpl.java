package springmvc.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import springmvc.model.Board;
import springmvc.model.BoardListInfo;

public class BoardMapperDaoImpl implements BoardMapperDao {
	private SqlSession sqlSession;

	// 마이바티스
//	@Inject
//	private SqlSession sqlSessionXml;

	public void setSqlSesstion(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Board findBySeq(long boardSeq) {
		Board board = (Board) sqlSession.selectOne("springmvc.dao.BoardMapperDao.findBySeq", boardSeq);

		return board;
	}

	@Override
	public List<BoardListInfo> getAll() {
		List<BoardListInfo> boardList = sqlSession.selectList("springmvc.dao.BoardMapperDao.getAll");

		return boardList;
	}

	@Override
	public int write(Board board) {
		int rowAffected = sqlSession.insert("springmvc.dao.BoardMapperDao.write", board);

		return rowAffected;
	}

	@Override
	public int modify(Board board) {
		int rowAffected = sqlSession.update("springmvc.dao.BoardMapperDao.modify", board);
		
		return rowAffected;
	}
}
