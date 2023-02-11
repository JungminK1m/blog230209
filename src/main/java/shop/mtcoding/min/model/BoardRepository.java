package shop.mtcoding.min.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.min.dto.board.BoardResponse.BoardMainResponseDto;

@Mapper
public interface BoardRepository {

    public List<BoardMainResponseDto> findAllWithUser();

    public List<Board> findAll();

    public int insert(@Param("title") String title, @Param("content") String content, @Param("userId") int userId);

    public int findById(int id);

    public int updateById(@Param("id") int id, @Param("title") String title, @Param("content") String content);

    public int deleteById(int id);
}
