package com.cheoloh.ecole_test.board;

import com.cheoloh.ecole_test.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @PostMapping() // 게시글작성
    public ResponseEntity<?> saveBoard(@RequestBody Board board){
        Board postedBoard = boardRepository.save(board);
        return new ResponseEntity<>(postedBoard, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> loadAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> loadAllBoard(@PathVariable Long boardId){
        Board board = boardRepository.findById(boardId).get();
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard(@PathVariable Long boardId, @RequestBody Board board){
        Board findBoard = boardRepository.findById(boardId).get();
        findBoard.update(board);
        boardRepository.save(findBoard);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> removeBoard(@PathVariable Long boardId){
        boardRepository.deleteById(boardId);
        return new ResponseEntity<>(boardId+"번째 게시글을 삭제하였습니다.", HttpStatus.OK);
    }
}
