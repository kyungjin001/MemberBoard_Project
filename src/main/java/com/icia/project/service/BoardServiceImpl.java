package com.icia.project.service;



import com.icia.project.common.PagingConst;
import com.icia.project.dto.*;
import com.icia.project.entity.BoardEntity;
import com.icia.project.entity.MemberEntity;
import com.icia.project.repository.BoardRepository;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;

    private final MemberRepository mr;



    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {

        MultipartFile boardImage = boardSaveDTO.getBoardImage();
        String boardImageName = boardImage.getOriginalFilename();
        boardImageName = System.currentTimeMillis() + "-" + boardImageName;
        String savePath = "C:\\development\\source\\springboot\\MemberBoard\\src\\main\\resources\\static\\upload\\"+boardImageName;
        if(!boardImage.isEmpty()) {
            boardImage.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardImageName(boardImageName);


        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO,memberEntity);
//        Long boardId= br.save(boardEntity).getId();
        Long result =  br.save(boardEntity).getId();

        return result;



    }



    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardList.add(BoardDetailDTO.toBoardDetailDTO(boardEntity));
        }
        return boardList;
    }


    @Override
    @Transactional
    public BoardDetailDTO findById(Long boardId) {
        int boardHits = br.boardHits(boardId);
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
        return boardDetailDTO;
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) throws IOException {
        MultipartFile boardImage = boardUpdateDTO.getBoardImage();
        String boardImageName = boardImage.getOriginalFilename();
        boardImageName = System.currentTimeMillis() + "-" + boardImageName;
        String savePath = "C:\\development\\source\\springboot\\MemberBoard\\src\\main\\resources\\static\\upload\\"+boardImageName;
        if(!boardImage.isEmpty()) {
            boardImage.transferTo(new File(savePath));
        }
        boardUpdateDTO.setBoardImageName(boardImageName);
        BoardEntity boardEntity = BoardEntity.toUpdateBoard(boardUpdateDTO);
//        Long boardId = br.save(boardEntity).getId();
        return br.save(boardEntity).getId();
    }

    @Override
//    @Transactional
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "id")));
        //Page<BoardEntity> => Page<BoardPagingDTO>
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits()
                )

        );
        return boardList;

    }

    @Override
    public void deleteById(Long boardId) {
        br.deleteById(boardId);

    }

    @Override
    public List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO) {
        if(boardSearchDTO.getSelect().equals("boardTitle")){
           List<BoardEntity> boardEntityList = br.findByBoardTitleContaining(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardList = new ArrayList<>();
            for (BoardEntity boardEntity : boardEntityList) {
                boardList.add(BoardDetailDTO.toBoardDetailDTO(boardEntity));
            }
            return boardList;
        }else {
            List<BoardEntity> boardEntityList = br.findByBoardWriterContaining(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardList = new ArrayList<>();
            for (BoardEntity boardEntity : boardEntityList) {
                boardList.add(BoardDetailDTO.toBoardDetailDTO(boardEntity));
            }
        return boardList;
        }
    }}













//
//    @Override
//    public MemberDetailDTO findByEmail(String memberEmail) {
//        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
//        MemberDetailDTO memberDetailDTO =MemberDetailDTO.toMemberDetailDTO(memberEntity);
//        return memberDetailDTO;
//    }





