package com.cheoloh.ecole_test.member;


import com.cheoloh.ecole_test.board.Board;
import com.cheoloh.ecole_test.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @PostMapping()
    public ResponseEntity<?> postMember(@RequestBody Member member){
        memberRepository.save(member);
        return new ResponseEntity<>("Create Member", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getMembers(){
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId){
        Member member = memberRepository.findById(memberId).get();
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> putMember(@PathVariable Long memberId, @RequestBody Member member){
        Member findMember = memberRepository.findById(memberId).get();
        findMember.setName(member.getName());
        memberRepository.save(findMember);

        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId){
        memberRepository.deleteById(memberId);

        return new ResponseEntity<>(memberId+"번의 Member가 삭제되었습니다.", HttpStatus.OK);
    }

    @PutMapping("{memberId}/post/{boardId}")
    public ResponseEntity<?> writeMember(@PathVariable Long memberId, @PathVariable Long boardId){
        Member member = memberRepository.findById(memberId).get();
        Board board = boardRepository.findById(boardId).get();

        member.posting(board);

        Member writeMember = memberRepository.save(member);

        return new ResponseEntity<>(writeMember, HttpStatus.OK);
    }

}
