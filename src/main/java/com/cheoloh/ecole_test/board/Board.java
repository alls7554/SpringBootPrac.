package com.cheoloh.ecole_test.board;

import com.cheoloh.ecole_test.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity @Table
@Getter
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;

    public Board() {
        setCreatedAt(this.createdAt);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt.now();
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt.now();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "board")
    private Set<Member> members;

    public void update(Board board){
        this.content = board.getContent();
        this.modifiedAt = modifiedAt.now();
    }
}
