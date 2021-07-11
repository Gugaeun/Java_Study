package springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import springmvc.model.Board;
import springmvc.model.BoardListInfo;

@Repository
public interface BoardMapperDao {
	// 게시글 단건조회
	Board findBySeq(long boardSeq);
	// 게시글 목록조회
	List<BoardListInfo> getAll();
	// 게시글 작성
	int write(Board board);
	// 게시글 수정
	int modify(Board board);
}
