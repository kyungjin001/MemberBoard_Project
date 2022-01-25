package com.icia.project.dto;


import com.icia.project.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private MultipartFile boardImage;
    private String boardImageName;
    private LocalDateTime boardTime;
    private int boardHits;

    public BoardDetailDTO(Long id, Long id1, String boardTitle, String boardWriter, String boardContents, LocalDateTime createTime) {
    }


    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardImageName(boardEntity.getBoardImageName());
        boardDetailDTO.setBoardHits(boardEntity.getBoardHits());
        if(boardEntity.getUpdateTime()==null){
            boardDetailDTO.setBoardTime(boardEntity.getCreateTime());
        }else {
            boardDetailDTO.setBoardTime(boardEntity.getUpdateTime());
        }

        return boardDetailDTO;
    }



}
