package com.icia.project.service;



import com.icia.project.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    List<BoardDetailDTO> findAll();

    BoardDetailDTO findById(Long boardId);

    Long update(BoardUpdateDTO boardUpdateDTO) throws IOException;

    Page<BoardPagingDTO> paging(Pageable pageable);

    void deleteById(Long boardId);

    List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO);


//
//    MemberDetailDTO findByEmail(String memberEmail);
//
}
