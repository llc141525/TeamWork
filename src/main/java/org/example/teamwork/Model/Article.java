package org.example.teamwork.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Article {
    /*
     * 一个 article 包含 id, title, content, classes(类别), watchNum(查看数).这些属性可以直接调用
     * 还有 comments(一对多), user(多对一). 这两个是实体类注意 comments 是列表
     * 使用 addComment, removeComment 来维护双向关系, 避免直接操作集合
     * */

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 文章的标题
    @Column(unique = true)
    @NotNull(message = "文章标题不能为空")
    private String title;

    //文章的文本内容
    @NotNull(message = "文章内容不能为空")
    private String content;

    // 文章的分类, 多个分类使用逗号分隔
    @NotNull(message = "文章分类不能为空")
    private String category;

    //文章的观看数量
    @NotNull(message = "文章观看数量不能为空")
    private Integer watchNum;

    //一对多, 一篇文章对应多个评论
    @OneToMany(cascade = CascadeType.ALL,    // 设置级联删除, 删除一篇文章会一次性把所有评论删除.
            mappedBy = "article",
            orphanRemoval = true) // 孤儿删除
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    //一对一, 一篇文章只有一个作者.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private User user;


    // 维护双向关系
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setArticle(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setArticle(null);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Article article = (Article) o;
        return getId() != null && Objects.equals(getId(), article.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
