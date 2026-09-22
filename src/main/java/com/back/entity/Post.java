package com.back.entity;

import com.back.jap.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

// 저장될 때 자식 엔티티도 같이 변경
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
// 삭제될 때 자식 엔티티도 같이 변경
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    // orphanRemoval = 고아 객체(Orphan)를 자동으로 제거
    // CascadeType.REMOVE와 달리 부모 엔티티가 삭제되거나 연관 관계가 삭제되면 같이 삭제됨
    // = 가비지 컬렉터와 같은 개념
    @OneToMany(mappedBy = "post", cascade = { PERSIST, REMOVE }, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(Member author, String content) {
        PostComment postComment = new PostComment(this, author, content);

        comments.add(postComment);

        return postComment;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }
}
